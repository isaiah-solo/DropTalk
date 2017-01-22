package com.chatapp.zayz.talktalk;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zayz on 1/21/17.
 */

public interface ActivityCallback {

    /**
     * Sets the current fragment
     *
     * @param fragment Fragment to set
     */
    void setFragment(Fragment fragment);

    /**
     * Hides soft keyboard
     */
    void hideKeyboard();

    /**
     * Shows soft keyboard to focus on view
     *
     * @param view View for keyboard to focus
     */
    void showKeyboard(View view);

    /**
     * Displays snack bar with text
     *
     * @param text Text to display
     */
    void showSnackBar(String text);

    /**
     * Sets new toolbar title
     *
     * @param text New toolbar title
     */
    void setToolbarTitle(String text);

    /**
     * Sets toolbar's home up enabled
     *
     * @param enabled If toolbar's home up is enabled
     */
    void setUpEnabled(boolean enabled);

    /**
     * Schedules timer given a handler, a runnable, a delay time, and a time period
     *
     * @param runnable Runnable that runs process
     * @param delay    Delay time
     * @param period   Period of time
     */
    void scheduleTimer(final Runnable runnable, long delay, int period);

    /**
     * Cancels timer if active
     */
    void cancelTimer();
}