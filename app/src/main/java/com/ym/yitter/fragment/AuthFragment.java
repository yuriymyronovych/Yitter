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
    private AccessToken accessToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new WebView(getActivity());
        view.setWebViewClient(new OAuthWebViewClient(Constants.CALLBACK_URL, new OAuthWebViewClient.Listener() {
            @Override
            public void onTokenReceived(String token) {
                onVerify(token);
            }
        }));
        return view;
    }

    protected void onVerify(String verifier) {
        accessToken.createClientAsync(verifier, new DataListener<TwitterClient>() {
            @Override
            public void onResult(TwitterClient client) {
                DataAccess.getInstance().setTwitterClient(client);
                getNavigation().showTimeline();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        RequestToken authRequest = DataAccess.getInstance().startAuth(getActivity());
        authRequest.createAccessTokenAsync(new DataListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                if (isAdded()) {
                    AuthFragment.this.accessToken = accessToken;
                    view.loadUrl(accessToken.getAuthUrl());
                }
            }
        });
    }
}
