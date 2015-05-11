package com.study.radasm.go.Fragments;

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

    @Override
    protected View loadingSuccessView() {
        indivualView = ViewUtils.inflate(mContext, R.layout.view_indivual);

        return indivualView;
    }
}
