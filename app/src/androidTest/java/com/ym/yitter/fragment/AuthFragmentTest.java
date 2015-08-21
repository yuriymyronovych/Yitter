package com.ym.yitter.fragment;

import com.ym.yitter.BuildConfig;
import com.ym.yitter.MainActivity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Yuriy Myronovych on 21/08/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AuthFragmentTest extends TestCase {

    @Test
    public void testAuth() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    }
}