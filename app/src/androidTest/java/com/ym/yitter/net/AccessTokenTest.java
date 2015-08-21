package com.ym.yitter.net;

import android.content.Context;
import junit.framework.TestCase;
import org.mockito.Matchers;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class AccessTokenTest extends TestCase {

    public void testCreateClient() throws Exception {
        Context ctx = mock(Context.class);
        OAuthService service = mock(OAuthService.class);
        Token requestToken = mock(Token.class);
        Token accessToken = mock(Token.class);
        when(service.getAccessToken(Matchers.eq(requestToken), Matchers.<Verifier>anyObject())).thenReturn(accessToken);

        AccessToken token = new AccessToken(ctx, service, "url", requestToken);
        TwitterClient client = token.createClient("verifier");
        assertEquals(accessToken, client.accessToken);
    }
}