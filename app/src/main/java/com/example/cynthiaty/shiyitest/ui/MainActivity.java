package com.example.cynthiaty.shiyitest.ui;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.cynthiaty.shiyitest.R;
import com.example.cynthiaty.shiyitest.model.adpter.MyAdapter;
import com.example.cynthiaty.shiyitest.model.db.DataService;
import com.example.cynthiaty.shiyitest.model.entity.Magazine;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 功能：MainActivity
 * 时间：2017-1-5
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SwipyRefreshLayout swipeFresh;
    private MyAdapter adapter;

    private List<Magazine> data = new ArrayList<>();
    private DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        registerAdapter();
        getData();
        setOnRefresh();
    }

    /**
     * 注册数据适配器
     */
    private void registerAdapter() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(MainActivity.this, data);
        listView.setAdapter(adapter);
    }

    /**
     * 启用子Thread获取网络数据
     */
    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataService.run();
                    while (DataService.magazines.size() == 0) {
                        Thread.sleep(100);
                    }
                    handler.sendMessage(handler.obtainMessage(11, DataService.magazines));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 下拉刷新与上拉加载more
     */
    private void setOnRefresh() {
        swipeFresh = (SwipyRefreshLayout) findViewById(R.id.swipe_fresh_follow);
        swipeFresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
        swipeFresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getData();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            data.addAll((List<Magazine>) msg.obj);
            adapter.notifyDataSetChanged();     //通知listView数据已发生改变，要求更新UI
            swipeFresh.setRefreshing(false);
            DataService.magazines.clear();
        }
    };
}