package com.study.radasm.go.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.study.radasm.go.R;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/19.
 */
public class FriendsRecycleAdapter extends RecyclerView.Adapter {
    /**
     * 分组的名称
     */
    private static final int TYPE_GROUP = 1;
    /**
     * 组员
     */
    private static final int TYPE_ZUYUAN = 2;


    private Context context;
    private ArrayList<com.study.radasm.go.Sina.User> friendsList;

    public FriendsRecycleAdapter(Context context, ArrayList<com.study.radasm.go.Sina.User> friendsList) {
        this.context = context;
        this.friendsList = friendsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, parent, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -2);
        v.setLayoutParams(lp);
        FriendsViewHolder friendsViewHolder = new FriendsViewHolder(v);
        return friendsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendsViewHolder holder1 = (FriendsViewHolder) holder;
        final com.study.radasm.go.Sina.User user = friendsList.get(position);
        String userImageUri = user.profile_image_url;
        String user_name = user.screen_name;
        String user_desc = user.description;
        /**
         * 使用Fresco加载图片
         */
        Uri uri = Uri.parse(userImageUri);
        holder1.userImage.setImageURI(uri);

        holder1.userName.setText(user_name);
        holder1.user_des.setText(user_desc);

        /**
         * container的点击事件，进入具体的聊天界面
         */
        holder1.user_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return friendsList.size();
    }


    private static class FriendsViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout user_container;
        public SimpleDraweeView userImage;
        public TextView userName;
        public TextView user_des;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            this.user_container = (RelativeLayout) itemView.findViewById(R.id.user_container);
            this.userImage = (SimpleDraweeView) itemView.findViewById(R.id.userImage);
            this.userName = (TextView) itemView.findViewById(R.id.username);
            this.user_des = (TextView) itemView.findViewById(R.id.user_des);
        }
    }
}
