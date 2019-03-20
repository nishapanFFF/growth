package com.fabfitfun.abtest.biz;

import com.fabfitfun.sharedapi.user.UserClient;

import java.text.ParseException;

public class AbtestService {

    private UserClient userClient;

  public AbtestService(UserClient userClient) {
    this.userClient = userClient;
  }
  
  public boolean addChannel(String channelName) {
      // add channel here
      return false;
  }
}
