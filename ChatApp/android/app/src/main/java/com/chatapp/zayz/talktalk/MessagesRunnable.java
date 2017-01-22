package com.chatapp.zayz.talktalk;

import android.content.Context;

import java.util.List;

/**
 * Created by zayz on 1/21/17.
 */

public class MessagesRunnable implements Runnable {
    private Context mContext;
    private boolean running;
    private BaseFragment

    public MessagesRunnable(Context context, BaseListAdapter adapter) {
        this.mContext = context;
        this.running = true;
    }

    @Override
    public void run() {
        if (!this.running) return;
        new MessageListHttpRequest(this.mContext).execute(new HttpCallback<List<Message>>() {

            /**
             * On request success
             *
             * @param values List of values from request
             */
            @Override
            public void onSuccess(List<Message> values) {
                ((BaseListAdapter) mAdapter).setData(values);
                mCallback.scheduleTimer(new MessagesRunnable(), 0, 500);
            }

            /**
             * On request error
             *
             * @param e Exception
             */
            @Override
            public void onError(Exception e) {
            }
        });
    }

    /**
     * Starts runnable
     */
    public void start() {
        this.running = true;
    }

    /**
     * Stops runnable
     */
    public void stop() {
        this.running = false;
    }
}
