package com.fabfitfun.shop.data;

import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

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
public interface InviteUserSettingsDao extends SqlObject {

  @SqlQuery("SELECT * FROM shop_invite_user_settings WHERE id = ?")
  String getById(Long id);

  @SqlUpdate("INSERT INTO shop_invite_user_settings ("
            + "   season_id,"
            + "   user_id,"
            + "   max_invites,"
            + "   billing_days,"
            + "   expired_days,"
            + "   expired_days_member,"
            + "   date_to_add,"
            + "   date_modified"
            + " ) VALUES ("
            + "   :seasonId,"
            + "   :userId,"
            + "   :maxInvites,"
            + "   :billingDays,"
            + "   :expiredDays,"
            + "   :expiredDaysMember,"
            + "   :dateToAdd,"
            + "   :dateModified"
            + " )")
  void insert(@BindBean InviteUserSettings inviteUserSettings);
}
