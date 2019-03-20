package com.fabfitfun.shop.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookSubscribeEvent {
  private String email;
  private String subscriptionId;
}
