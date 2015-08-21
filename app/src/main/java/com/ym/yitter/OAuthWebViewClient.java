package com.ym.yitter;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class OAuthWebViewClient extends WebViewClient {
    private Listener listener;
    private String callbackUrl;

    public OAuthWebViewClient(String callbackUrl, Listener listener) {
        this.listener = listener;
        this.callbackUrl = callbackUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(callbackUrl)) {
            listener.onTokenReceived(Utils.extractVerifier(url));
            return true;
        } else {
            return false;
        }
    }

    public interface Listener {
        void onTokenReceived(String token);
    }
}
