package com.rmyh.timelinedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //存储列表数据
    List<TimeData> list = new ArrayList<>();
    TimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rlView = (RecyclerView) findViewById(R.id.activity_rlview);

        //初始化数据
        initData();
        // 将数据按照时间排序
        TimeComparator comparator = new TimeComparator();
        Collections.sort(list, comparator);
        // recyclerview绑定适配器
        rlView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TimeAdapter(this, list);
        rlView.setAdapter(adapter);


    }

    private void initData() {
        list.add(new TimeData("20170710", "我是第一个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第一个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第二个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第三个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第一个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第二个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第三个数据"));
        list.add(new TimeData("20140706", "我是最后一个数据"));
    }
}
