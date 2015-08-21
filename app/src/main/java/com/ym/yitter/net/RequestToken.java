package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.ProgressAsyncTask;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class RequestToken {
    protected Context ctx;
    protected OAuthService service;
    protected Token requestToken;

    protected RequestToken(Context ctx, OAuthService service) {
        this.service = service;
        this.ctx = ctx;
    }

    /**
     * Step 1. Obtain requestToken
     * Step 2. Get an external url to authorise
     */
    protected AccessToken createAccessToken() {
        requestToken = service.getRequestToken();
        String url = service.getAuthorizationUrl(requestToken);
        return new AccessToken(ctx, service, url, requestToken);
    }

    public void createAccessTokenAsync(DataListener<AccessToken> listener) {
        new ProgressAsyncTask<AccessToken>(ctx, listener) {
            @Override
            protected AccessToken doInBackground(Void... params) {
                 return createAccessToken();
            }
        }.execute();
    }
}
