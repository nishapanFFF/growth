package com.fabfitfun.abtest.biz;

import com.fabfitfun.auth.PermissionChecker;
import com.fabfitfun.auth.shared.AuthenticatedUser;
import com.fabfitfun.auth.shared.PermissionLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthChecker {
  public final static String GROWTH_USER_OPERATION = "growth_user_operation";
  public final static String GROWTH_ADMIN_OPERATION = "growth_admin_operation";

  private final PermissionChecker permissionChecker;

  public boolean isAuthorizedAdmin(AuthenticatedUser authenticatedUser) {
    return permissionChecker.isPermitted(authenticatedUser, GROWTH_ADMIN_OPERATION, PermissionLevel.READ.getIntValue());
  }

  public boolean isAuthorizedUser(AuthenticatedUser authenticatedUser) {
    return permissionChecker.isPermitted(authenticatedUser, GROWTH_USER_OPERATION, PermissionLevel.READ.getIntValue());
  }
}
