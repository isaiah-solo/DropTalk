package com.chatapp.zayz.talktalk;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by zayz on 1/21/17.
 */

public abstract class BaseListFragment extends BaseFragment {
    protected BaseAdapter mAdapter;

    /**
     * Sets list fragment
     *
     * @param name List name
     * @param listView     List view
     * @param adapter      List adapter
     */
    protected void setListFragment(String name, ListView listView,
                                   BaseAdapter adapter) {
        this.setFragment(name);
        this.setView(listView, adapter);
    }

    /**
     * Sets fragment view
     *
     * @param listView List view
     * @param adapter  List adapter
     */
    protected void setView(ListView listView, BaseAdapter adapter) {
        this.mAdapter = adapter;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListItemListener());
    }

    /**
     * Allows user to set what happens on list item click
     *
     * @param parent   Parent view of list item
     * @param view     List item view
     * @param position List item position
     * @param id       Id of list item
     */
    protected abstract void onClick(AdapterView<?> parent, View view, int position, long id);


    /**
     * Class that listens for list item click
     */
    protected class ListItemListener implements AdapterView.OnItemClickListener {

        /**
         * Listens for list item click
         *
         * @param parent   Parent view of list item
         * @param view     List item view
         * @param position List item position
         * @param id       Id of list item
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mCallback.hideKeyboard();
            onClick(parent, view, position, id);
        }
    }
}
