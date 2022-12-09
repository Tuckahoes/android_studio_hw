package com.example.android_experiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Work> workList = new ArrayList<>();

    public class PageViewFragmentAdapter extends FragmentStateAdapter {
        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position)
            {
                case 0:
                    return BookFragment.newInstance();
                case 1:
                    return WebViewFragment.newInstance();
                case 2:
                    return MapViewFragment.newInstance();
                case 3:
                    return GameViewFragment.newInstance();
            }
            return BookFragment.newInstance();
        }
        @Override
        public int getItemCount() {
            return 4;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new PageViewFragmentAdapter(getSupportFragmentManager(),getLifecycle()));

        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0:
                        tab.setText("图书");
                        break;
                    case 1:
                        tab.setText("新闻");
                        break;
                    case 2:
                        tab.setText("卖家");
                        break;
                    case 3:
                        tab.setText("游戏");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
    //bar菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barmenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //bar菜单新建书本事件
            case R.id.create_book:
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);
                break;
            default:
        }
        return true;
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
                int position =itemInfo.position;
                //workList.remove(itemInfo.position);
                //WorkAdapter adapter=new WorkAdapter(workList);
                //adapter.notifyDataSetChanged();
                break;
            //修改书本信息
            case R.id.edit:
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                View targetView=itemInfo.targetView;
                View book_name_v=targetView.findViewById(R.id.work_name);
                String book_name= ((TextView) book_name_v).getText().toString();
                intent.putExtra("book_name",book_name);
                startActivityForResult(intent,1);
            default:
        }
        return super.onContextItemSelected(item);
    }
//    //返回数据
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1:
//                if (resultCode == RESULT_OK) {
//                    String returnData=data.getStringExtra("new_work_name");
//                    Work new_work=new Work(returnData,R.drawable.avator);
//                    workList.add(new_work);
//                    RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
//                    WorkAdapter adapter=new WorkAdapter(workList);
//                    adapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(adapter);
//                }
//                break;
//            default:
//        }
//    }
}