package com.study.radasm.go.Fragments;

import android.support.v7.widget.CardView;
import android.view.View;

import com.study.radasm.go.R;
import com.study.radasm.go.Utils.ViewUtils;

/**
 * Created by RadAsm on 15/5/11.
 */
public class IndividualFragment extends BaseFragment{
    private static IndividualFragment mIndividualFragment;
    private IndividualFragment(){

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

    @Override
    protected View loadingSuccessView() {
        indivualView = ViewUtils.inflate(mContext, R.layout.view_indivual);

        cv_myFriends= (CardView) indivualView.findViewById(R.id.cv_myFriends);
        cv_goManual= (CardView) indivualView.findViewById(R.id.cv_goManual);

        cv_myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enter the model of my_friends


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
