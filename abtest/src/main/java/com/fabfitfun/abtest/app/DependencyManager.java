package com.fabfitfun.abtest.app;


import com.fabfitfun.abtest.api.AbtestResource;
import com.fabfitfun.sharedapi.user.UserClient;
import com.fabfitfun.sharedutils.api.CredentialUpdater;
import com.fabfitfun.sharedutils.api.SoteriaHoneycombClient;
import com.fabfitfun.sharedutils.http.HttpClient;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import com.fabfitfun.abtest.data.UserDao;

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

  UserClient userClient;
  public final AbtestResource abtestResource;

  DependencyManager(AbtestConfiguration config, Environment env) {
    log.info("Initializing read database pool...");
    // This creates a managed database and creates a JdbiHealthCheck
    final JdbiFactory factory = new JdbiFactory();
    Jdbi pushReadDb = newDatabase(factory, env, config.getPushReadDatabase(), "pushDbRead");
    //Jdbi pushReadDb = null;
    Jdbi shopWriteDb = newDatabase(factory, env, config.getShopWriteDatabase(), "shopDbWrite");

    final UserDao userDao = pushReadDb.onDemand(UserDao.class);
    //final UserDao userDao = null; // temp

    AppConfig appConfig = config.getApp();

    // Setup clients
      val httpClient = new HttpClient();
      val soteriaClient = new SoteriaHoneycombClient(httpClient,
      new CredentialUpdater(appConfig.getV1BackendKey(), appConfig.getV1BackendJwt()));
      userClient = new UserClient(appConfig.getUserAddress(), soteriaClient);

      com.fabfitfun.abtest.biz.AbtestService abtestService = new com.fabfitfun.abtest.biz.AbtestService(userClient);
      abtestResource = new AbtestResource(abtestService);
  }

  /** Generates a new database pool. */
  private static Jdbi newDatabase(JdbiFactory jdbiFactory, Environment env,
                                  DataSourceFactory dataSourceFactory, String name) {
    val db = jdbiFactory.build(env, dataSourceFactory, name);
    db.installPlugin(new SqlObjectPlugin());
    return db;
  }
}
