package com.ym.yitter;

import com.ym.yitter.data.Tweet;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class Utils {

    /**
     * android.net.uri does not work in tests therefore there should be custom impl or apache libraries
     */
    public static String extractVerifier(String url) {
        String params = url.split("\\?")[1];
        String parts[] = params.split("&");
        for (String part : parts) {
            if (part.startsWith(Constants.OAUTH_VERIFIER)) return part.split("=")[1];
        }
        return null;
    }

    public static String getTimeAgo(Date date) {
        long ms = System.currentTimeMillis() - date.getTime();
        if (TimeUnit.MILLISECONDS.toSeconds(ms) < 60) {
            return TimeUnit.MILLISECONDS.toSeconds(ms) + "s ago";
        } else if (TimeUnit.MILLISECONDS.toMinutes(ms) < 60) {
            return TimeUnit.MILLISECONDS.toMinutes(ms) + "m ago";
        } else if (TimeUnit.MILLISECONDS.toHours(ms) < 24) {
            return TimeUnit.MILLISECONDS.toHours(ms) + "h ago";
        } else {
            return TimeUnit.MILLISECONDS.toDays(ms) + "d ago";
        }
    }

    public static String generateHeader(Tweet tweet) {
        return tweet.getUser().getName() + " @" + tweet.getUser().getScreenName() + " " + Utils.getTimeAgo(tweet.getCreatedAt());
    }
}
