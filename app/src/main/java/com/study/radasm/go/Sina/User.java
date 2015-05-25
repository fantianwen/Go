package com.study.radasm.go.Sina;

/**
 * Created by RadAsm on 15/5/25.
 */
public class User {
    public long id;
    public String idstr;
    public String screen_name;
    public String name;
    public String description;
    public String profile_image_url;
    public String gender;
    public String avatar_large;

    @Override
    public String toString() {
        return "User{" +
                "avatar_large='" + avatar_large + '\'' +
                ", id=" + id +
                ", idstr='" + idstr + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", profile_name_url='" + profile_image_url + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
