package com.fabfitfun.shop.data;

import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

/**
 * A data access object backed by a relational database. JDBI implements this interface by reading
 * the annotations. This class is provided as an example of how JDBI works. This particular file
 * should not be used as is, because the User microservice already provides a interface to the users
 * table in the push database.
 *
 * <p>Each database table should only have a max of one microservice reading and writing to it, with
 * some exceptions. If you need access to the users table, make a call to an endpoint on the User
 * microservice.
 */
public interface UserDao extends SqlObject {

  @SqlQuery("SELECT account_code FROM users WHERE email = ?")
  String getAccountCode(String email);

  @SqlQuery("SELECT id, email, account_code FROM users WHERE id = ?")
  @RegisterBeanMapper(User.class)
  User getUserById(Integer userId);
}
