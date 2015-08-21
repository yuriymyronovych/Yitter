package com.ym.yitter;

import com.ym.yitter.data.Tweet;
import com.ym.yitter.data.User;
import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class UtilsTest extends TestCase {

    public void testExtractVerifier() throws Exception {
        String extracted = Utils.extractVerifier("http://www.yitter.com/?oauth_verifier=token");
        assertEquals("token", extracted);
    }

    public void testGetTimeAgo() throws Exception {
        long ms = System.currentTimeMillis() - 1001;
        assertEquals("1s ago", Utils.getTimeAgo(new Date(ms)));

        ms -= 1000 * 60;
        assertEquals("1m ago", Utils.getTimeAgo(new Date(ms)));

        ms -= 1000 * 60 * 60;
        assertEquals("1h ago", Utils.getTimeAgo(new Date(ms)));

        ms -= 1000 * 60 * 60 * 24;
        assertEquals("1d ago", Utils.getTimeAgo(new Date(ms)));
    }

    public void testGenerateHeader() throws Exception {
        Tweet tweet = new Tweet();
        User user = new User();
        user.setName("name");
        user.setScreenName("screenn");
        tweet.setUser(user);
        tweet.setCreatedAt(new Date());


        assertEquals("name @screenn 0s ago", Utils.generateHeader(tweet));
    }
}