package com.ym.yitter.net;

import android.content.Context;
import com.ym.yitter.ProgressAsyncTask;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class AccessToken {
    protected Context ctx;
    protected OAuthService service;
    protected String authUrl;
    protected Token requestToken;
    protected Token accessToken;

    protected AccessToken(Context ctx, OAuthService service, String authUrl, Token requestToken) {
        this.ctx = ctx;
        this.service = service;
        this.requestToken = requestToken;
        this.authUrl = authUrl;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * Step 3. Obtain access token
     */
    public TwitterClient createClient(String verifier) {
        accessToken = service.getAccessToken(requestToken, new Verifier(verifier));
        return new TwitterClient(ctx, service, accessToken, new TwitterRequestFactory());
    }

    public void createClientAsync(final String verifier, DataListener<TwitterClient> listener) {
        new ProgressAsyncTask<TwitterClient>(ctx, listener) {
            @Override
            protected TwitterClient doInBackground(Void... params) {
                return createClient(verifier);
            }
        }.execute();
    }
}
