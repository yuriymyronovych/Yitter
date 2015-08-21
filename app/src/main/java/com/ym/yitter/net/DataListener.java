package com.ym.yitter.net;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public interface DataListener<Result> {
    void onResult(Result result);
}
