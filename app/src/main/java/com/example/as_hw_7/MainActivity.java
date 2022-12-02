package com.example.as_hw_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Work> workList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        for(int i=0;i<8;i++){
            Work one_work=new Work("one_work",R.drawable.avator);
            workList.add(one_work);
        }
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WorkAdapter adapter=new WorkAdapter(workList);
        registerForContextMenu(recyclerView);
        recyclerView.setLongClickable(true);
        recyclerView.setAdapter(adapter);
    }
    //contextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            //删除选择的item
            case R.id.remove:
                //int position =itemInfo.position;
                //workList.remove(itemInfo.position);
                WorkAdapter adapter=new WorkAdapter(workList);
                adapter.notifyDataSetChanged();
                break;
            default:
        }
        return super.onContextItemSelected(item);
    }
    //返回数据
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData=data.getStringExtra("new_work_name");
                    Work new_work=new Work(returnData,R.drawable.avator);
                    workList.add(new_work);
                    RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
                    WorkAdapter adapter=new WorkAdapter(workList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                break;
            default:
        }
    }
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
                    Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                    startActivityForResult(intent,1);
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


class Work {
    private String name;
    private int imageId;
    public Work(String name,int imageId) {
        this.name=name;
        this.imageId=imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
}
