package com.chatapp.zayz.talktalk;

/**
 * Created by zayz on 1/21/17.
 */

public class Message {
    public String userId;
    public String message;
    public double lat;
    public double lng;
    
    public Message() {
        this.userId = "";
        this.message = "";
        this.lat = 0.0;
        this.lng = 0.0;
    }

    public Message(String userId, String message, double lat, double lng) {
        this.userId = userId;
        this.message = message;
        this.lat = lat;
        this.lng = lng;
    }
}
