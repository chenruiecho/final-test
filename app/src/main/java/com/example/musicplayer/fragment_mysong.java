package com.example.musicplayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_mysong extends Fragment{
    private View view;
    private Myadapter myadapter;
    private RecyclerView recyclerView;
    private List<String> list1;
    List <Map<String,Object>> data;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2, container, false);
        recyclerView = view.findViewById(R.id.mysong_recyclerview);

        data=new ArrayList<Map<String,Object>>();
        String[] names={"挚友","再回首","Normal No More"};
        String[] singer={"歌手：黄丽玲","歌手：姜育恒","歌手：TYSM"};
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
                "才知道平平淡淡从从容容才是真。\n","Normal No More的歌词"};
        int[] image={R.drawable.picture1,R.drawable.picture2,R.drawable.picture4};
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
        return view;
    }
}