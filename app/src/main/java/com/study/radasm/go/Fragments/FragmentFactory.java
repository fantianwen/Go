package com.study.radasm.go.Fragments;

/**
 * Created by RadAsm on 15/5/11.
 */
public class FragmentFactory implements AbstractFragmentFactory {

    private static FragmentFactory mFragmentFactory=new FragmentFactory();
    private FragmentFactory(){

    }
    public static FragmentFactory getInstance(){
        if(mFragmentFactory==null){
            synchronized (FragmentFactory.class){
                if(mFragmentFactory==null){
                    mFragmentFactory=new FragmentFactory();
                }
            }
        }
        return mFragmentFactory;
    }


    @Override
    public BaseFragment createFragment(int position) {
        BaseFragment fragment=null;
        switch (position){
            case 0://新闻中心
                fragment=NewsFragment.getInstance();
                break;
            case 1://用户中心
                fragment=IndividualFragment.getInstance();
                break;
        }
        return fragment;
    }
}
