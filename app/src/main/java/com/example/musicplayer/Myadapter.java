package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class Myadapter extends RecyclerView.Adapter <Myadapter.Myholder> {
    Context context1;
    List<Map<String,Object>> data1;
    public Myadapter(Context context,List<Map<String,Object>> data) {
        context1=context;
        data1=data;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context1).inflate(R.layout.item,parent,false);

        Myholder myholder=new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder,
                                 @SuppressLint("RecyclerView") int position) {
        holder.avatar.setImageResource((int)(data1.get(position).get("歌曲图片")));
        holder.name.setText(data1.get(position).get("歌名").toString());
        holder.content.setText(data1.get(position).get("歌手").toString());
        holder.tipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context1,SongActivity.class);
                intent.putExtra("歌词",data1.get(position).get("歌词").toString());
                context1.startActivity(intent);//跳转到歌词界面
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
    public class Myholder extends RecyclerView.ViewHolder{
        LinearLayout tipView;
        TextView name;
        TextView content;
        ImageView avatar;
        public Myholder(@NonNull View itemView){
            super(itemView);
            tipView=itemView.findViewById(R.id.song_layout);
            name=itemView.findViewById(R.id.textView_songname);
            content=itemView.findViewById(R.id.textView_singer);
            avatar=itemView.findViewById(R.id.imageViewS);
        }
    }
}