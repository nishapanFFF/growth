package com.fabfitfun.shop.app;

import com.fabfitfun.shop.api.FacebookResource;
import com.fabfitfun.shop.api.SgtfResource;
import com.fabfitfun.shop.biz.SgtfService;
import com.fabfitfun.shop.data.InviteUserSettingsDao;
import com.fabfitfun.sharedapi.user.UserClient;
import com.fabfitfun.sharedutils.api.CredentialUpdater;
import com.fabfitfun.sharedutils.api.SoteriaHoneycombClient;
import com.fabfitfun.sharedutils.http.HttpClient;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import com.fabfitfun.shop.biz.FacebookService;
import com.fabfitfun.shop.data.UserDao;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import lombok.Getter;
import lombok.val;
import lombok.extern.jbosslog.JBossLog;


/**
 * Guice without Guice.
 *
 * <p>DependencyManager wires up the application. DAOs are connected to the database and
 * dependencies are handed to the objects that use them.
 */
@JBossLog
@Getter
class DependencyManager {
  // biz
  public final FacebookResource facebookResource;
  public final SgtfResource sgtfResource;
  UserClient userClient;

  DependencyManager(ShopConfiguration config, Environment env) {
    log.info("Initializing read database pool...");
    // This creates a managed database and creates a JdbiHealthCheck
    final JdbiFactory factory = new JdbiFactory();
    Jdbi pushReadDb = newDatabase(factory, env, config.getPushReadDatabase(), "pushDbRead");
    //Jdbi pushReadDb = null;
    Jdbi shopWriteDb = newDatabase(factory, env, config.getShopWriteDatabase(), "shopDbWrite");

    final UserDao userDao = pushReadDb.onDemand(UserDao.class);
    //final UserDao userDao = null; // temp
    final InviteUserSettingsDao inviteUserSettingsDao = shopWriteDb.onDemand(InviteUserSettingsDao.class);

    AppConfig appConfig = config.getApp();

    // Setup clients
      val httpClient = new HttpClient();
      val soteriaClient = new SoteriaHoneycombClient(httpClient,
      new CredentialUpdater(appConfig.getV1BackendKey(), appConfig.getV1BackendJwt()));
      userClient = new UserClient(appConfig.getUserAddress(), soteriaClient);

    FacebookService facebookService = new FacebookService(userDao, appConfig.getParty3ApiKey());
    facebookResource = new FacebookResource(facebookService, appConfig.getMaxThread());

      SgtfService sgtfService = new SgtfService(userClient, inviteUserSettingsDao);
      sgtfResource = new SgtfResource(sgtfService);
  }
  
  /** Generates a new database pool. */
  private static Jdbi newDatabase(JdbiFactory jdbiFactory, Environment env,
                                  DataSourceFactory dataSourceFactory, String name) {
    val db = jdbiFactory.build(env, dataSourceFactory, name);
    db.installPlugin(new SqlObjectPlugin());
    return db;
  }
}
