package com.fabfitfun.shop.api;

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
import com.fabfitfun.shop.biz.FacebookService;
import com.fabfitfun.shop.data.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class ResourcesTest {

  private static final String TEST_EMAIL = "so@hyperion360.com";
  private FacebookResource handler;
  @Mock
  private UserDao userDao;
  private FacebookService facebookMetrics;

  @Before
  public void setUp() throws Exception {
	FacebookService fbMetrics = new FacebookService(userDao, "");
	facebookMetrics = Mockito.spy(fbMetrics);
    handler = new FacebookResource(facebookMetrics, 50);
    //when(userDao.getAccountCode(Mockito.anyString())).thenReturn(TEST_EMAIL);
  }

  @Test
  public void testSubscribe() throws Exception {
    String subscriptionId = "abc";
    FacebookSubscribeEvent event =
    		FacebookSubscribeEvent.builder().email(TEST_EMAIL).subscriptionId(subscriptionId).build();
    FacebookStatus result = handler.subscribe(event);
    // Verify call and arguments
    ArgumentCaptor<FacebookSubscribeEvent> subscribeEventCaptor =
        ArgumentCaptor.forClass(FacebookSubscribeEvent.class);
    Mockito.verify(facebookMetrics).subscribe(subscribeEventCaptor.capture());
    FacebookSubscribeEvent se = subscribeEventCaptor.getValue();
    assertEquals(subscriptionId, se.getSubscriptionId());
    assertEquals(TEST_EMAIL, se.getEmail());
    assertTrue(result.isResult());
  }
}
