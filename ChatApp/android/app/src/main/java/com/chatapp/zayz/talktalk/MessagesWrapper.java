package com.chatapp.zayz.talktalk;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by zayz on 1/21/17.
 */

public class MessagesWrapper extends ArrayList<Message> {
    public MessagesWrapper(JSONArray jsonArray) throws JSONException {
        super(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                this.add(new MessageWrapper(jsonArray.getJSONObject(i)));
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
    }
}
