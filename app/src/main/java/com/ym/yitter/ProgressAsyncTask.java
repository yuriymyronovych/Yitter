package com.ym.yitter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.ym.yitter.net.DataListener;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public abstract class ProgressAsyncTask<Result> extends AsyncTask<Void, Void, Result> {
    private ProgressDialog dialog;
    private DataListener<Result> listener;
    private Context ctx;
    private boolean enableDialog;

    public ProgressAsyncTask(Context ctx, DataListener<Result> listener, boolean enableDialog) {
        this.ctx = ctx;
        this.listener = listener;
        this.enableDialog = enableDialog;
    }

    public ProgressAsyncTask(Context ctx, DataListener<Result> listener) {
        this(ctx, listener, true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (enableDialog) {
            dialog = ProgressDialog.show(ctx, ctx.getString(R.string.loading), ctx.getString(R.string.loading));
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        if (enableDialog) {
            dialog.dismiss();
        }
        listener.onResult(result);
    }
}
