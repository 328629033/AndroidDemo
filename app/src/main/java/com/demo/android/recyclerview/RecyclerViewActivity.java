package com.demo.android.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;
import com.demo.android.concurrent.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/7/12.
 */

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvTop, tvSecond, stable;
    int mCurrentPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        tvTop = (TextView) findViewById(R.id.tv_top);
        tvSecond = (TextView) findViewById(R.id.tv_second);
        stable = (TextView) findViewById(R.id.top_stable);
        final List<CommonRecyclerViewAdapter.IItem> list = getList();
        final CommonRecyclerViewAdapter adapter = new CommonRecyclerViewAdapter(list, this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View firstView = linearLayoutManager.findViewByPosition(0);

                if(firstView != null) {
                    int y = firstView.getTop();
                    System.out.println("y is " + y);
                    if(y < - tvTop.getHeight()){
                        y = -tvTop.getHeight();
                    }
                    tvTop.setY(y);

//                    if(tvSecond.getY() > 0 ) {
//                        float y = firstView.getY() + tvTop.getHeight();
//                        if(y < stable.getHeight()){
//                            y = stable.getHeight();
//                        }
//                        tvSecond.setY(y);
//                    }
                }else{
                    tvTop.setY(-tvTop.getHeight());
//                    tvSecond.setY(0);
                }

                tvSecond.setY(tvTop.getY() + tvTop.getHeight());

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                System.out.println("new state is " + newState);
                if(newState == 0){
                    tvSecond.setY(tvTop.getY() + tvTop.getHeight());
                }
            }
        });
        recyclerView.setAdapter(adapter);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(list.size() > 2) {
                    list.remove(2);
                    adapter.notifyItemRemoved(1);
                    handler.postDelayed(this, 500);
                }
            }
        }, 3000);


    }

    private List<CommonRecyclerViewAdapter.IItem> getList(){
        List<CommonRecyclerViewAdapter.IItem> list = new ArrayList<>();
        list.add(new EmptyPlaceItem(130));
        for(int i=0; i<100; i++){
            if( i % 9 == 0){
                list.add(new EmptyPlaceItem(30));
            }
            list.add(new Product("这是第" + i + "个商品"));
        }
        return  list;
    }
}
