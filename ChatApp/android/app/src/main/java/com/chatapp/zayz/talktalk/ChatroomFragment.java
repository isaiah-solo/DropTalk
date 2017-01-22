package com.chatapp.zayz.talktalk;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chatapp.zayz.talktalk.databinding.FragmentChatroomBinding;

import java.util.List;

/**
 * Created by zayz on 1/21/17.
 */

public class ChatroomFragment extends BaseListFragment {
    private FragmentChatroomBinding mBinding;

    /**
     * Gets a new instance of fragment
     *
     * @return New instance of fragment
     */
    public static ChatroomFragment newInstance() {
        return new ChatroomFragment();
    }

    /**
     * Fragment's onCreateView method
     *
     * @param inflater           Layout inflater
     * @param container          Container of fragment
     * @param savedInstanceState Saved instance state
     * @return Inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mBinding = DataBindingUtil.inflate(this.getActivity().getLayoutInflater(),
                R.layout.fragment_chatroom, container, false);

        this.setListFragment("TalkTalk", mBinding.messageList, new ChatroomListAdapter(this.mContext));
        this.setChatView();

        return this.mBinding.getRoot();
    }

    protected void setChatView() {
        new MessageListHttpRequest(this.mContext).execute(new HttpCallback<List<Message>>() {

            /**
             * On request success
             *
             * @param values List of values from request
             */
            @Override
            public void onSuccess(List<Message> values) {
                ((BaseListAdapter) mAdapter).setData(values);
                mCallback.scheduleTimer(new MessagesRunnable(), 0, 500);
            }

            /**
             * On request error
             *
             * @param e Exception
             */
            @Override
            public void onError(Exception e) {
            }
        });
    }

    @Override
    protected void setArgumentFields(Bundle b) {
    }

    @Override
    protected void setFields() {
    }

    @Override
    protected void onClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
