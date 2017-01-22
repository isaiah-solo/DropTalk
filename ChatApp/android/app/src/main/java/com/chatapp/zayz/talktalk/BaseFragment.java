package com.chatapp.zayz.talktalk;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by zayz on 1/21/17.
 */

public abstract class BaseFragment extends Fragment {
    protected ActivityCallback mCallback;
    protected Context mContext;

    private String title;

    /**
     * Fragment's onAttach method
     *
     * @param context Activity context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mCallback = (ActivityCallback) context;
        this.mContext = context;
    }

    /**
     * Fragment's onCreate method
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);

        this.setArgumentFields(this.getArguments());
    }

    /**
     * Fragment's onStart method
     */
    @Override
    public void onStart() {
        super.onStart();

        this.mCallback.setToolbarTitle(this.title);
    }

    /**
     * Fragment's onDestroy method
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        this.mCallback.setUpEnabled(false);
    }

    /**
     * Sets fragment
     *
     * @param title Name of fragment
     */
    protected void setFragment(String title) {
        this.setFields();
        this.setLayout(title);
    }

    /**
     * Sets fragment layout
     *
     * @param title Name of fragment
     */
    protected void setLayout(int title) {
        this.title = this.mContext.getString(title);
    }

    /**
     * Sets fragment layout
     *
     * @param title Name of fragment
     */
    protected void setLayout(String title) {
        this.title = title;
    }

    /**
     * Sets child fragment given a resource id and fragment
     *
     * @param containerId Resource id of container
     * @param fragment    Fragment to set
     */
    protected void setChildFragment(int containerId, Fragment fragment) {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(containerId, fragment);
        ft.commit();
    }

    /**
     * Hides multiple views
     *
     * @param views Views to hide
     */
    protected void hideViews(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Show multiple views
     *
     * @param views Views to show
     */
    protected void showViews(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Allows user to set fields from fragment arguments
     *
     * @param b Bundle from fragment arguments
     */
    protected abstract void setArgumentFields(Bundle b);

    /**
     * Allows user to set fields
     */
    protected abstract void setFields();
}