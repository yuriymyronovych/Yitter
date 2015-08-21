package com.ym.yitter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ym.yitter.R;
import com.ym.yitter.data.Tweet;
import com.ym.yitter.net.DataAccess;
import com.ym.yitter.net.DataListener;
import com.ym.yitter.net.TwitterClient;

import java.util.Collections;
import java.util.List;

/**
 * Created by Yuriy Myronovych on 19/08/2015.
 */
public class TimelineFragment extends NavigationFragment {
    private RecyclerView listView;
    private TweetAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private EditText newTweet;
    private TwitterClient client = DataAccess.getInstance().getTwitterClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.timeline, container, false);
        listView = (RecyclerView) parent.findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new TweetAdapter(Collections.EMPTY_LIST);
        listView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) parent.findViewById(R.id.swipe_refresh_layout);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });

        newTweet = (EditText) parent.findViewById(R.id.newTweet);
        parent.findViewById(R.id.postTweet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.postTweetAsync(newTweet.getText().toString(), new DataListener<Tweet>() {
                    @Override
                    public void onResult(Tweet tweet) {
                        if (tweet != null) {
                            adapter.insert(tweet);
                            adapter.notifyDataSetChanged();

                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(newTweet.getWindowToken(), 0);
                            newTweet.setText("");
                        }
                    }
                });

            }
        });

        return parent;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchData();
    }

    private void fetchData() {
        swipeRefresh.setRefreshing(true);
        client.getTimelineAsync(new DataListener<List<Tweet>>() {
            @Override
            public void onResult(List<Tweet> tweets) {
                swipeRefresh.setRefreshing(false);
                adapter.setData(tweets);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
