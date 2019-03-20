package com.fabfitfun.shop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InviteUserSettings {
  private Integer id;
  private Integer seasonId;
  private Integer userId;
  private Integer maxInvites;
  private Integer billingDays;
  private Integer expiredDays;
  private Integer expiredDaysMember;
  private Date dateToAdd;
  private Date dateModified;
}
