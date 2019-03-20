package com.fabfitfun.shop.biz;

import com.fabfitfun.shop.api.FacebookSubscribeEvent;
import com.fabfitfun.shop.data.UserDao;

public class FacebookService {

  private UserDao userDao;
  private String env;

  public FacebookService(UserDao userDao, String env) {
    this.userDao = userDao;
    this.env = env;
  }
  
  public boolean subscribe(FacebookSubscribeEvent event) {
    return true;
  }
}
