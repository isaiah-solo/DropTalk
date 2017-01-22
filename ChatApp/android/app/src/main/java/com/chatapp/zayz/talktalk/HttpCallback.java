package com.chatapp.zayz.talktalk;

/**
 * Created by zayz on 1/21/17.
 */

public interface HttpCallback<T> {

    /**
     * On http request success
     *
     * @param val Retrieved value
     */
    void onSuccess(T val);

    /**
     * On http request error
     *
     * @param e Exception
     */
    void onError(Exception e);
}
