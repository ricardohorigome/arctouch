package com.codetest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Basic AsyncTask to hold the Context and handle messages
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 */
public abstract class BasicAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private Context context;
    private String message = null;

    public BasicAsyncTask(Context context){
        super();
        this.context = context;
    }

    /**
     * It shows the message if the same is valid
     * @param result
     */
    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (getMessage() != null && !"".equals(getMessage())) {
            Toast.makeText(getContext(), getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Context getContext() {
        return context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
