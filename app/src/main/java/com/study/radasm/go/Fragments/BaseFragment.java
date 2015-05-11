package com.study.radasm.go.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.study.radasm.go.R;
import com.study.radasm.go.Utils.ViewUtils;

/**
 * Created by RadAsm on 15/5/11.
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;

    private FrameLayout mFrameLayout;
    private View successView;
    private View failureView;
    private View loadingView;

    public BaseFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext=getActivity();
        mFrameLayout=new FrameLayout(mContext);

        loadingView= ViewUtils.inflate(mContext,R.layout.view_laoding);
        failureView=ViewUtils.inflate(mContext, R.layout.view_failure);
        super.onCreate(savedInstanceState);

    }

    protected  abstract View loadingSuccessView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        successView=loadingSuccessView();
        mFrameLayout.addView(loadingView);
        mFrameLayout.addView(successView);
        return mFrameLayout;
    }

}
