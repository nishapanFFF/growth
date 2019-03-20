package com.fabfitfun.abtest.app;

import com.fabfitfun.auth.shared.FabAppConfig;

import lombok.Data;

/**
 * This class provides specific properties in the app.yml that aren't handled by the default.
 */
@Data
class AppConfig implements FabAppConfig {
  private boolean authBypassed;
  private String party3ApiKey;
  private String nonSecretProperty;
  private int maxThread;
  private String v1BackendKey;
  private String v1BackendJwt;
  private String userAddress;
}
