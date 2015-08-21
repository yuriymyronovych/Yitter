package com.ym.yitter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.ym.yitter.Constants;
import com.ym.yitter.OAuthWebViewClient;
import com.ym.yitter.net.*;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class AuthFragment extends NavigationFragment {
    private WebView view;
    private AuthAccessToken authAccessToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new WebView(inflater.getContext());
        view.setWebViewClient(new OAuthWebViewClient(Constants.CALLBACK_URL, new OAuthWebViewClient.Listener() {
            @Override
            public void onTokenReceived(String token) {
                onVerify(token);
            }
        }));
        return view;
    }

    protected void onVerify(String verifier) {
        authAccessToken.createClientAsync(verifier, new DataListener<TwitterClient>() {
            @Override
            public void onResult(TwitterClient client) {
                getNavigation().showTimeline();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        AuthRequestToken authRequest = DataAccess.getInstance().startAuth(getActivity());
        authRequest.createAccessTokenAsync(new DataListener<AuthAccessToken>() {
            @Override
            public void onResult(AuthAccessToken authAccessToken) {
                AuthFragment.this.authAccessToken = authAccessToken;
                view.loadUrl(authAccessToken.getAuthUrl());
            }
        });
    }
}
