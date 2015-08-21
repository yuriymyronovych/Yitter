package com.ym.yitter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.ym.yitter.fragment.AuthFragment;
import com.ym.yitter.fragment.TimelineFragment;


public class MainActivity extends FragmentActivity implements INavigation {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAuth();
    }

    @Override
    public void showAuth() {
        replaceFragment(new AuthFragment());
    }

    @Override
    public void showTimeline() {
        replaceFragment(new TimelineFragment());
    }

    private void replaceFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, f);
        transaction.commit();
    }
}
