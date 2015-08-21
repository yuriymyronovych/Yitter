package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.data.Tweet;
import junit.framework.TestCase;
import org.junit.Before;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class TwitterClientTest extends TestCase {
    TwitterClient client;
    @Before
    public void setUp() {
        Context ctx = mock(Context.class);
        OAuthService service = mock(OAuthService.class);
        Token requestToken = mock(Token.class);
        TwitterRequestFactory factory = mock(TwitterRequestFactory.class);
        client = new TwitterClient(ctx, service, requestToken, factory);
    }

    public void testGetTimeline() throws Exception {
        OAuthRequest request = mock(OAuthRequest.class);
        Response response = mock(Response.class);
        when(client.factory.getTimelineRequest()).thenReturn(request);
        when(request.send()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.getBody()).thenReturn("[{\"text\":\"message\"}]");

        List<Tweet> timeline = client.getTimeline();
        assertEquals("message", timeline.get(0).getText());
    }

    public void testPostTweet() throws Exception {
        OAuthRequest request = mock(OAuthRequest.class);
        Response response = mock(Response.class);
        when(client.factory.getPostTweetRequest("message")).thenReturn(request);
        when(request.send()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.getBody()).thenReturn("{\"text\":\"message\"}");

        Tweet tweet = client.postTweet("message");
        assertEquals("message", tweet.getText());
    }
}