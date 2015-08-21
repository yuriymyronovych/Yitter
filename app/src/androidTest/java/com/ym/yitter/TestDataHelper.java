package com.ym.yitter;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class TestDataHelper {

    public static OauthUrlData buildValidUrlData() {
        OauthUrlData data = new OauthUrlData();
        data.token = "token";
        data.callback = Constants.CALLBACK_URL;
        data.url = data.callback + "?" + Constants.OAUTH_VERIFIER + "=" + data.token;
        return data;
    }

    public static OauthUrlData buildNotValidUrlData() {
        OauthUrlData data = new OauthUrlData();
        data.token = "token";
        data.url = "anything" + "?" + Constants.OAUTH_VERIFIER + "=" + data.token;
        return data;
    }

    public static class OauthUrlData {
        public String url;
        public String callback;
        public String token;
    }
}
