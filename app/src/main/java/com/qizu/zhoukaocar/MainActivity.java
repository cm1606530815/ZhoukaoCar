package com.qizu.zhoukaocar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0 ; i < ImageUtil.imageUrls.length ; i++){
            String data = ImageUtil.imageUrls[i];
            mDatas.add(data);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //添加布局管理
        StaggeredGridLayoutManager manager = new  StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置适配器
        recyclerView.setAdapter(new RecycleAdapter(this,mDatas));
        //设置item添加和删除的动画
//        recyclerView.setItemAnimator();
        //设置分隔线
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

    }

}
