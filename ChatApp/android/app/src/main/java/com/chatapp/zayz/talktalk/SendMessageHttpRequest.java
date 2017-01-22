package com.chatapp.zayz.talktalk;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by simba on 7/31/15
 * Edited by isaiah on 9/1/2015
 * <p/>
 * This file contains an http request that gathers event information.
 */
public class SendMessageHttpRequest extends BaseHttpRequest {
    protected Context mContext;

    /**
     * Constructor
     *
     * @param context Activity context
     */
    public SendMessageHttpRequest(Context context) {
        super(Method.POST);

        this.mContext = context;

        String protocol = "https://";
        String api = "hackucsc2017-156309.appspot.com";
        String port = "";
        String path = "/post";

        this.createUrl(protocol, api, port, path, null);
    }

    /**
     * Executes http request
     *
     * @param callback Http callback
     */
    public void execute(final HttpCallback<List<Message>> callback) {
        rawExecute(new HttpCallback<String>() {

            /**
             * On http request success
             *
             * @param value Retrieved value
             */
            @Override
            public void onSuccess(String value) {
                try {
                    JSONArray jsonArray = new JSONArray(value);
                    callback.onSuccess(new MessagesWrapper(jsonArray));
                } catch (JSONException je) {
                    callback.onError(je);
                }
            }

            /**
             * On http request error
             *
             * @param e Exception
             */
            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }

}
