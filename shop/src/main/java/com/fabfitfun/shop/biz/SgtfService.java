package com.fabfitfun.shop.biz;

import com.fabfitfun.shop.data.InviteUserSettings;
import com.fabfitfun.shop.data.InviteUserSettingsDao;
import com.fabfitfun.sharedapi.user.UserClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SgtfService {

    private UserClient userClient;
    private InviteUserSettingsDao inviteUserSettingsDao;

  public SgtfService(UserClient userClient, InviteUserSettingsDao inviteUserSettingsDao) {
    this.userClient = userClient;
    this.inviteUserSettingsDao = inviteUserSettingsDao;
  }
  
  public boolean addInvite(long userId) throws ParseException {
      String email = userClient.getEmailByWoo(userId);
      if(email != null) {
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          InviteUserSettings inviteUserSettings =
                  InviteUserSettings.builder().seasonId(1).userId((int)userId).maxInvites(3)
                          .billingDays(4).expiredDays(5).expiredDaysMember(6)
                          .dateToAdd(format.parse ("2019-05-01"))
                          .dateModified(format.parse ("2019-12-31")).build();
          inviteUserSettingsDao.insert(inviteUserSettings);

      }
      return false;
  }
}
