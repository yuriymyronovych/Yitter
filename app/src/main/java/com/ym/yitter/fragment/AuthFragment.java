package com.ym.yitter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.ym.yitter.OAuthWebViewClient;
import com.ym.yitter.ProgressAsyncTask;
import com.ym.yitter.net.DataAccess;
import com.ym.yitter.net.DataListener;
import com.ym.yitter.net.TwitterServiceClient;
import com.ym.yitter.net.TwitterServiceClientAsync;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class AuthFragment extends NavigationFragment {
    private WebView view;
    private TwitterServiceClientAsync client = DataAccess.getInstance().getClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new WebView(inflater.getContext());
        view.setWebViewClient(new OAuthWebViewClient(client.getCallbackUrl(), new OAuthWebViewClient.Listener() {
            @Override
            public void onTokenReceived(String token) {
                onVerify(token);
            }
        }));
        return view;
    }

    protected void onVerify(String verifier) {
        client.prepareAccessToken(verifier, new DataListener<Void>() {
            @Override
            public void onResult(Void aVoid) {
                getNavigation().showTimeline();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        client.prepareRequestAndGetUrl(new DataListener<String>() {
            @Override
            public void onResult(String url) {
                view.loadUrl(url);
            }
        });
    }
}
