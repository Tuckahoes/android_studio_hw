package com.example.as_hw_7;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {
    public static TabFragment newInstance(String label) {
        Bundle args=new Bundle();
        args.putString("label",label);
        TabFragment fragment =new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle SaveInstanceState) {
        return inflater.inflate(R.layout.fragment_tab,container,false);
    }
    @Override
    public void onStart(){
        super.onStart();
        String label=getArguments().getString("label");
        TextView text =getView().findViewById(R.id.tv_bg);
        text.setText(label);
        text.setBackgroundColor(Color.rgb(150,150,200));

//        List<Work> workList = new ArrayList<>();
//        for(int i=0;i<8;i++){
//            Work one_work=new Work("one_work",R.drawable.avator);
//            workList.add(one_work);
//        }
//        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        MainActivity.WorkAdapter adapter=new MainActivity.WorkAdapter(workList);
//        registerForContextMenu(recyclerView);
//        recyclerView.setLongClickable(true);
//        recyclerView.setAdapter(adapter);
    }
}
