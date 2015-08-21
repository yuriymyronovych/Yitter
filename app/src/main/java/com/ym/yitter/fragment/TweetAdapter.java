package com.ym.yitter.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ym.yitter.R;
import com.ym.yitter.Utils;
import com.ym.yitter.data.Tweet;

import java.util.List;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Tweet> tweets;

    public TweetAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setData(List<Tweet> data) {
        this.tweets = data;
    }

    public List<Tweet> getData() {
        return tweets;
    }

    public void insert(Tweet result) {
        tweets.add(0, result);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView userImage;
        public TextView headerText;
        public TextView message;

        public ViewHolder(View tweetView) {
            super(tweetView);
            userImage = (ImageView) tweetView.findViewById(R.id.user_image);
            headerText = (TextView) tweetView.findViewById(R.id.header);
            message = (TextView) tweetView.findViewById(R.id.message);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tweet, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Tweet tweet = tweets.get(i);
        Picasso.with(viewHolder.userImage.getContext())
                .load(tweet.getUser().getImageUrl())
                .into(viewHolder.userImage);

        viewHolder.headerText.setText(Utils.generateHeader(tweet));
        viewHolder.message.setText(tweet.getText());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
}
