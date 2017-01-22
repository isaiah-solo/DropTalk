package com.chatapp.zayz.talktalk;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.chatapp.zayz.talktalk.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ActivityCallback {
    private ActivityMainBinding mBinding;
    private TextView mTitle;
    private Timer mTimer;
    private boolean init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.init = true;

        this.setFields();
        this.setToolbar();
        this.setFragment(ChatroomFragment.newInstance());
    }

    /**
     * Initializes the top toolbar
     */
    private void setToolbar() {
        this.setSupportActionBar(this.mBinding.toolbar);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayShowTitleEnabled(false);
            this.getSupportActionBar().setHomeButtonEnabled(true);
        }
        this.mTitle = this.mBinding.toolbarTitle;
    }

    /**
     * Initializes the activity's fields
     */
    private void setFields() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /**
     * Sets the given fragment
     *
     * @param fragment Fragment to set
     */
    @Override
    public void setFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.view_fragment, fragment);
        if (!this.init) {
            ft.addToBackStack(null);
        }
        this.init = false;
        ft.commit();
    }

    /**
     * Hides soft keyboard
     */
    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view == null) return;
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Shows soft keyboard to focus on view
     *
     * @param view View for keyboard to focus
     */
    @Override
    public void showKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Displays snack bar with text
     *
     * @param text Text to display
     */
    @Override
    public void showSnackBar(String text) {
        /*
        final Snackbar snackbar = Snackbar.make(this.mBinding.coordinatorLayout, text,
                Snackbar.LENGTH_INDEFINITE);

        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.ucsc_yellow));
        snackbar.setAction(this.getString(R.string.snackbar_dismiss), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
        */
    }

    /**
     * Sets new toolbar title
     *
     * @param text New toolbar title
     */
    @Override
    public void setToolbarTitle(String text) {
        this.mTitle.setText(text);
    }

    /**
     * Sets toolbar's home up enabled
     *
     * @param enabled If toolbar's home up is enabled
     */
    @Override
    public void setUpEnabled(boolean enabled) {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    /**
     * Schedules timer given a handler, a runnable, a delay time, and a time period
     *
     * @param runnable Runnable that runs process
     * @param delay    Delay time
     * @param period   Period of time
     */
    @Override
    public void scheduleTimer(final Runnable runnable, long delay,
                              int period) {
        final Handler handler = new Handler();
        this.mTimer = new Timer();
        this.mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, delay, period);
    }

    /**
     * Cancels timer if active
     */
    @Override
    public void cancelTimer() {
        if (this.mTimer != null) this.mTimer.cancel();
    }
}
