package com.ym.yitter;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class OAuthWebViewClientTest extends TestCase {
    
    public void testShouldOverrideUrlLoading() throws Exception {
        TestDataHelper.OauthUrlData data = TestDataHelper.buildValidUrlData();
        OAuthWebViewClient.Listener listener = mock(OAuthWebViewClient.Listener.class);
        OAuthWebViewClient client = new OAuthWebViewClient(data.callback, listener);
        client.shouldOverrideUrlLoading(null, data.url);

        verify(listener).onTokenReceived(data.token);
    }

    public void testShouldOverrideUrlLoadingFailOnWrongCallback() throws Exception {
        TestDataHelper.OauthUrlData data = TestDataHelper.buildNotValidUrlData();

        OAuthWebViewClient.Listener listener = mock(OAuthWebViewClient.Listener.class);
        OAuthWebViewClient client = new OAuthWebViewClient(Constants.CALLBACK_URL, listener);
        client.shouldOverrideUrlLoading(null, data.url);

        verify(listener, never()).onTokenReceived(data.token);
    }
}