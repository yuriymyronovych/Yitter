package com.ym.yitter.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.ym.yitter.INavigation;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class NavigationFragment extends Fragment {
    private INavigation navigation;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        navigation = (INavigation) activity;
    }

    public INavigation getNavigation() {
        return navigation;
    }
}
