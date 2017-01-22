package com.chatapp.zayz.talktalk;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by zayz on 1/21/17.
 */

public abstract class BaseListAdapter extends ArrayAdapter<Message> {
    protected Context mContext;

    /**
     * Constructor
     *
     * @param context Activity context
     * @param id Resource id
     */
    public BaseListAdapter(Context context, int id) {
        super(context, id);
        this.mContext = context;
    }

    /**
     * Sets the list adapter data given a list of objects
     *
     * @param items List of objects
     */
    public void setData(List<Message> items) {
        this.clear();
        for (Message item : items) this.add(item);
        this.notifyDataSetChanged();
    }
}
