package com.chatapp.zayz.talktalk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zayz on 1/21/17.
 */

public class ChatroomListAdapter extends BaseListAdapter {

    private static final int id = R.layout.item_message;

    /**
     * Constructor
     *
     * @param context Activity context
     */
    public ChatroomListAdapter(Context context) {
        super(context, id);
    }

    /**
     * Gets view of item showing on screen
     *
     * @param position    Position of object on list
     * @param convertView View of list item
     * @param parent      Parent of list item view
     * @return Inflated view with contents
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(id, null);
        }

        Message message = this.getItem(position);

        ViewHolder holder = new ViewHolder();
        holder.message = (TextView) convertView.findViewById(R.id.message);
        convertView.setTag(holder);

        holder.message.setText(message.message);

        return convertView;
    }

    /**
     * Class containing views in list item
     */
    private static class ViewHolder {
        public TextView message;
    }
}
