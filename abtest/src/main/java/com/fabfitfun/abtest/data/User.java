package com.fabfitfun.abtest.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * User object to carry data from the database. DTOs and other POJOs should make heavy use of
 * Lombok.
 */
public class User {
  private Integer id;
  private String email;
  private String accountCode;
}
