package com.ym.yitter.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class User {
    @SerializedName("name") private String name;
    @SerializedName("profile_image_url") private String imageUrl;
    @SerializedName("screen_name") private String screenName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
