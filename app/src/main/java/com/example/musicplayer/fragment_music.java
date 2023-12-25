package com.example.musicplayer;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_music extends Fragment{
    private View view;
    private Myadapter myadapter;
    private RecyclerView recyclerView;
    List <Map<String,Object>> data;
    ImageButton button1,button2,button3,button4;
    MyService.MyBinder binder;
    public fragment_music() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab1, container, false);
        recyclerView = view.findViewById(R.id.music_recyclerview);
        button1=view.findViewById(R.id.previous_Button);
        button2=view.findViewById(R.id.play_Button);
        button3=view.findViewById(R.id.stop_Button);
        button4=view.findViewById(R.id.next_Button);
        data=new ArrayList<Map<String,Object>>();
        String[] names={"挚友","再回首","春风十里报新年"};
        String[] singer={"歌手：黄丽玲","歌手：姜育恒","歌手：一吃就胖大狸花"};
        String[] lyrics={"挚友的歌词","再回首，\n" +
                "云遮断归途，\n" +
                "再回首，\n" +
                "荆棘密布，\n" +
                "今夜不会再有难舍的旧梦，\n" +
                "曾经与你有的梦，\n" +
                "今后要向谁诉说，\n" +
                "再回首，\n" +
                "背影已远走，\n" +
                "再回首，\n" +
                "泪眼朦胧，\n" +
                "留下你的祝福，\n" +
                "寒夜温暖我，\n" +
                "不管明天要面对多少伤痛和迷惑，\n" +
                "曾经在幽幽暗暗反反复复中追问，\n" +
                "才知道平平淡淡从从容容才是真。\n","早上光照在房间\n" +
                "穿上新衬衫\n" +
                "镜子里我又长高了些\n" +
                "和同学吃火锅\n" +
                "然后开黑带妹\n" +
                "一起狼人杀不用上学\n" +
                "就不累\n" +
                "发条祝福传给你\n" +
                "请你别忘记\n" +
                "今年我们还要看流星\n" +
                "年初的愿望你都实现了吗\n" +
                "喜欢的男孩你都追到了吗\n" +
                "回家陪妈妈一块包个饺子\n" +
                "包个饺子\n" +
                "回家陪奶奶一块看个电视\n" +
                "看个电视\n" +
                "和弟弟放个烟花\n" +
                "让城市回忆留下\n" +
                "七大姑八大姨\n" +
                "你们的红包在哪\n" +
                "春风吹十里 莺啼报新年\n" +
                "爆竹声声起 好运又一年\n" +
                "走过一片时间海\n" +
                "只为遇见对的爱\n" +
                "烟花聚又散 今夜共团圆\n" +
                "莺啼报新年 春风吹十里\n" +
                "今夜共团圆"};
        int[] image={R.drawable.picture1,R.drawable.picture2,R.drawable.picture3};
        for(int i=0;i<names.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("歌名",names[i]);
            map.put("歌手",singer[i]);
            map.put("歌曲图片",image[i]);
            map.put("歌词",lyrics[i]);
            data.add(map);
        }
        myadapter = new Myadapter(view.getContext(), data);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myadapter);
        Intent intent=new Intent(requireContext(), MyService.class);
        ServiceConnection connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                binder=(MyService.MyBinder) iBinder;
                binder.Todo();
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                binder=null;
            }
        };
        requireContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binder.playpremusic();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                requireContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                requireContext().unbindService(connection);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                binder.playnextmusic();
            }
        });
        return view;
    }
}