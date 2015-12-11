package com.codetest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.codetest.entity.Stop;

import java.util.List;


/**
 * AsyncTask with a a progress dialog
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 */
public abstract class ProgressAsyncTask<Params, Progress, Result> extends BasicAsyncTask<Params, Progress, Result> {

    private ProgressDialog progressDialog;

    private static final String PROGRESS_MESSAGE =  "Loading...";

    public ProgressAsyncTask(Context context){
       super(context);
    }

    /**
     * show the progress dialog before execution
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog  = ProgressDialog.show(getContext(), "", PROGRESS_MESSAGE, true);
        progressDialog.setOwnerActivity((Activity) getContext());
    }

    /**
     * closes the progress dialog after execution
     * @param result
     */
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        closeDialog();
    }

    protected void closeDialog() {
       progressDialog.cancel();
    }



}
