package com.ym.yitter.fragment;

import com.ym.yitter.data.Tweet;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
public class TweetAdapterTest extends TestCase {

    public void testInsert() throws Exception {
        Tweet t1 = new Tweet();
        Tweet t2 = new Tweet();
        TweetAdapter adapter = new TweetAdapter(new ArrayList<Tweet>());

        adapter.insert(t1);
        assertEquals(t1, adapter.getData().get(0));

        adapter.insert(t2);
        assertEquals(t2, adapter.getData().get(0));
    }

    public void testCount() {
        TweetAdapter adapter = new TweetAdapter(new ArrayList<Tweet>());
        assertEquals(0, adapter.getItemCount());

        Tweet t1 = new Tweet();
        adapter.insert(t1);
        assertEquals(1, adapter.getItemCount());
    }


}