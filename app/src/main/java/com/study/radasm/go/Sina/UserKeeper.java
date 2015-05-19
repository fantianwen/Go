package com.study.radasm.go.Sina;

import android.os.Bundle;

import com.study.radasm.go.Sina.openapi.models.User;

/**
 * sina微博用户数据持久化
 *
 * Created by RadAsm on 15/5/19.
 */
public class UserKeeper {

    /**
     * 将从新浪服务器返回的user信息保存在Bundle中
     *      具体会保存以下的字段
     *
     *
     *
     *        /** 用户UID（int64）
        //    public String id;
        //    /** 用户昵称
        //    public String screen_name;
        //    /** 用户所在地
        //    public String location;
        //    /** 用户个人描述
        //    public String description;
        //    /** 用户头像地址，50×50像素
        //    public String profile_image_url;
        //    public String weihao;
        //    /** 性别，m：男、f：女、n：未知
        //    /** 用户的在线状态，0：不在线、1：在线
        //    public int online_status;
     *
     * @return  保存了用户数据的Bundle对象
     */
    public static Bundle write2Bundle(User user){
        Bundle bundle = new Bundle();
        bundle.putString("id",user.id);
        bundle.putString("screen_name",user.screen_name);
        bundle.putString("location",user.location);
        bundle.putString("description",user.description);
        bundle.putString("profile_image_url",user.profile_image_url);
        bundle.putString("gender",user.gender);
        bundle.putInt("online_status",user.online_status);
        return bundle;
    }
}
