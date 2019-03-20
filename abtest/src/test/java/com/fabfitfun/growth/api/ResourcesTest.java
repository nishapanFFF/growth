package com.fabfitfun.abtest.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.fabfitfun.abtest.data.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class ResourcesTest {

  private static final String TEST_EMAIL = "so@hyperion360.com";
  @Mock
  private UserDao userDao;

  @Before
  public void setUp() throws Exception {
    when(userDao.getAccountCode(Mockito.anyString())).thenReturn(TEST_EMAIL);
  }
}
