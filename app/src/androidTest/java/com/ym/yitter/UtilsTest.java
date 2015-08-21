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
        TestDataHelper.OauthUrlData data = TestDataHelper.buildValidUrlData();

        String extracted = Utils.extractVerifier(data.url);
        assertEquals(data.token, extracted);
    }

    public void testGetTimeAgo() throws Exception {
        long ms = System.currentTimeMillis() - 1000;
        assertTrue(Utils.getTimeAgo(new Date(ms)).contains("s"));

        ms -= 1000 * 60;
        assertTrue(Utils.getTimeAgo(new Date(ms)).contains("m"));

        ms -= 1000 * 60 * 60;
        assertTrue(Utils.getTimeAgo(new Date(ms)).contains("h"));

        ms -= 1000 * 60 * 60 * 24;
        assertTrue(Utils.getTimeAgo(new Date(ms)).contains("d"));
    }

    public void testGenerateHeader() throws Exception {
        Tweet tweet = new Tweet();
        User user = new User();
        user.setName("name");
        user.setScreenName("screenn");
        tweet.setUser(user);
        tweet.setCreatedAt(new Date());


        String header = Utils.generateHeader(tweet);
        assertTrue(header.contains(tweet.getUser().getName()));
        assertTrue(header.contains(tweet.getUser().getScreenName()));

        String expectedTime = Utils.getTimeAgo(tweet.getCreatedAt());
        assertTrue(header.contains(expectedTime));
    }
}