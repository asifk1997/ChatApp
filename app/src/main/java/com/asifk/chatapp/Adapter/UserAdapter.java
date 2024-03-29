package com.asifk.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asifk.chatapp.MessageActivity;
import com.asifk.chatapp.Model.User;
import com.asifk.chatapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;
    public UserAdapter(Context mContext,List<User> mUsers,boolean ischat)
    {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat;
    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final User user = mUsers.get(position);

        holder.username.setText(user.getUsername());
        if (user.getImageURL().equals("default"))
        {
            Log.e("AK","set ic launcher");
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            Log.e("AK","used glide"+user.getImageURL());
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }

        if (ischat)
        {
            if (user.getStatus().equals("online"))
            {
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            }
            else
            {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;
        public ViewHolder(View itemView)
        {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
        }
    }
}
