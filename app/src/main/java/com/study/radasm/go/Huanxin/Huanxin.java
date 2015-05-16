package com.study.radasm.go.Huanxin;


import com.study.radasm.go.Huanxin.HXTasks.HXRegisterTask;

/**
 * Utils about Huanxin
 * <p/>
 * Created by RadAsm on 15/5/15.
 */
public class Huanxin {

    /**
     * huanxin register
     * @param username username
     * @param password password
     * @param hxCallback the callback of the result
     */
    public static void register(String username,String password,String nickname,HXCallback hxCallback){


        HXRegisterTask hxRegisterTask = new HXRegisterTask(username, password, nickname,hxCallback);

        new Thread(hxRegisterTask).start();

    }

    public static void isUserIn(String username){

    }



}
