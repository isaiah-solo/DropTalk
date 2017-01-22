package com.chatapp.zayz.talktalk;

import android.app.Application;

/**
 * Created by zayz on 1/21/17.
 */

public class TalkTalkApplication extends Application{

    /**
     * Application's onCreate method
     */
    @Override
    public void onCreate() {
        super.onCreate();
        BaseRequest.init(this);
    }
}
