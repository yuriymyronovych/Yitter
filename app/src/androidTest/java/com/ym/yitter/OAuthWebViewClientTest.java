package com.ym.yitter;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class OAuthWebViewClientTest extends TestCase {
    
    public void testShouldOverrideUrlLoading() throws Exception {
        OAuthWebViewClient.Listener listener = mock(OAuthWebViewClient.Listener.class);
        OAuthWebViewClient client = new OAuthWebViewClient("http://www.yitter.com", listener);
        client.shouldOverrideUrlLoading(null, "http://www.yitter.com/?oauth_verifier=token");

        verify(listener).onTokenReceived("token");
    }

    public void testShouldOverrideUrlLoadingFailOnWrongCallback() throws Exception {

        OAuthWebViewClient.Listener listener = mock(OAuthWebViewClient.Listener.class);
        OAuthWebViewClient client = new OAuthWebViewClient("http://www.google.com", listener);
        client.shouldOverrideUrlLoading(null, "http://www.yitter.com/?oauth_verifier=token");

        verify(listener, never()).onTokenReceived("token");
    }
}