package com.example.android_experiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {
    public static BookFragment newInstance() {
        Bundle args = new Bundle();
        //args.putString("label",label);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle SaveInstanceState) {
        View view= inflater.inflate(R.layout.book_fragment_tab, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        WorkAdapter adapter=new WorkAdapter(getWorks());
        registerForContextMenu(recyclerView);
        recyclerView.setLongClickable(true);
        recyclerView.setAdapter(adapter);
        return view;
    }
    public List<Work> getWorks() {
        List<Work> workList=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Work one_work = new Work("one_work", R.drawable.avator);
            workList.add(one_work);
        }
        return workList;
    }


    //图书（作品）适配器WorkAdapter
    class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {
        private List<Work> mWorkList;

        class ViewHolder extends RecyclerView.ViewHolder {
            View workView;
            ImageView workImage;
            TextView workName;

            public ViewHolder(View view) {
                super(view);
                workView=view;
                workImage=(ImageView)view.findViewById(R.id.work_image);
                workName=(TextView) view.findViewById(R.id.work_name);
            }
        }
        public WorkAdapter(List<Work> workList) {
            mWorkList=workList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_item,parent,false);
            final ViewHolder holder =new ViewHolder(view);
            holder.workView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAbsoluteAdapterPosition();
                    Work work=mWorkList.get(position);
                    //Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                    //startActivityForResult(intent,1);
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder,int position) {
            Work work =mWorkList.get(position);
            holder.workImage.setImageResource(work.getImageId());
            holder.workName.setText(work.getName());
        }
        @Override
        public int getItemCount() {
            return mWorkList.size();
        }

    }
}