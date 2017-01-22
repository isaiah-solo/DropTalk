package com.chatapp.zayz.talktalk;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zayz on 1/21/17.
 */

public class MessageWrapper extends Message {
    public MessageWrapper(JSONObject jsonObject) throws JSONException {
        super();
        try {
            this.userId = jsonObject.getString("user_id");
        } catch (JSONException je) {
            je.printStackTrace();
        }
        try {
            this.message = jsonObject.getString("message");
        } catch (JSONException je) {
            je.printStackTrace();
        }
        try {
            this.lat = jsonObject.getDouble("latitude");
        } catch (JSONException je) {
            je.printStackTrace();
        }
        try {
            this.lng = jsonObject.getDouble("longitude");
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
