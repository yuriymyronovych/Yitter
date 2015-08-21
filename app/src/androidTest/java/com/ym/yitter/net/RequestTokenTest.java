package com.ym.yitter.net;

import android.content.Context;
import junit.framework.TestCase;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class RequestTokenTest extends TestCase {

    public void testCreateAccessToken() {
        Context ctx = mock(Context.class);
        OAuthService service = mock(OAuthService.class);
        Token requestToken = mock(Token.class);
        when(service.getRequestToken()).thenReturn(requestToken);
        when(service.getAuthorizationUrl(requestToken)).thenReturn("url");
        RequestToken token = new RequestToken(ctx, service);

        AccessToken accessToken = token.createAccessToken();
        assertEquals("url", accessToken.getAuthUrl());
        assertEquals(requestToken, accessToken.requestToken);
    }
}