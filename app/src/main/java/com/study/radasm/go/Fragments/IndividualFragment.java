package com.study.radasm.go.Fragments;

import android.support.v7.widget.CardView;
import android.view.View;

import com.study.radasm.go.FriendsActivity;
import com.study.radasm.go.LoggingActivity;
import com.study.radasm.go.R;
import com.study.radasm.go.Utils.AppUtils;
import com.study.radasm.go.Utils.SharedPrefrenceUtils;
import com.study.radasm.go.Utils.ViewUtils;
import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/11.
 */
public class IndividualFragment extends BaseFragment{
    private static IndividualFragment mIndividualFragment;
    public IndividualFragment(){

    }
    public static IndividualFragment getInstance(){
        if(mIndividualFragment==null){
            synchronized (IndividualFragment.class){
                if(mIndividualFragment==null){
                    mIndividualFragment=new IndividualFragment();
                }
            }
        }
        return mIndividualFragment;
    }



    private View indivualView;
    //朋友圈模块
    private CardView cv_myFriends;
    //棋谱模块
    private CardView cv_goManual;
    private SharedPrefrenceUtils spUtils;
    private boolean isLogged;

    @Override
    protected View loadingSuccessView() {
        spUtils=new SharedPrefrenceUtils(mContext, Constants.CONFIG);
        indivualView = ViewUtils.inflate(mContext, R.layout.view_indivual);

        cv_myFriends= (CardView) indivualView.findViewById(R.id.cv_myFriends);
        cv_goManual= (CardView) indivualView.findViewById(R.id.cv_goManual);

        cv_myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * first to judge weather the user has logged in;
                 *    if not logged in,transfer to loggin page;
                 *    else transfer to friends page;
                 */
                isLogged=spUtils.getBoolean(Constants.IS_LOGGED);
                if(isLogged){
                    //hava logged in
                    AppUtils.transfer2Activity(null,mContext, FriendsActivity.class);
                }else{
                    AppUtils.transfer2Activity(null,mContext,LoggingActivity.class);
                }

            }
        });

        cv_goManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enter the model of chess_manual
            }
        });


        return indivualView;
    }
}
