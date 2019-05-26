package com.demo.android.concurrent;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;
import com.demo.android.databinding.ActivityConcurrentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by herr.wang on 2017/4/11.
 */
public class ConcurrentActivity extends AppCompatActivity {
    SparseArray<Product> messageQueue;
    RecyclerView rvProducer, rvConsumer;
    CommonRecyclerViewAdapter producerAdapter, consumerAdapter;
    List<CommonRecyclerViewAdapter.IItem> productList, consumeList;
    Thread produceThread, consumeThread;
    private Timer checkTime;
    boolean isDestroyed;
    private H h;
    int pIndex = 1;

    static class H extends Handler {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConcurrentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_concurrent);
        messageQueue = new SparseArray<>();
        rvProducer = binding.rvProducer;
        rvConsumer = binding.rvConsumer;
        productList = new ArrayList<>();
        consumeList = new ArrayList<>();
        initSomeProduct();
        producerAdapter = new CommonRecyclerViewAdapter(productList, this);
        consumerAdapter = new CommonRecyclerViewAdapter(consumeList, this);
        rvProducer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvConsumer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProducer.setAdapter(producerAdapter);
        rvConsumer.setAdapter(consumerAdapter);
        h = new H();
        doProduce();
        doConsume();
    }


    private void initSomeProduct() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.content = "" + i + "";
            messageQueue.put(Product.seq.get(), product);
        }
    }

    private void doProduce() {

        produceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDestroyed) {
                    Product product = new Product();
                    product.content = "第" + pIndex + "个商品";
                    product.time = System.currentTimeMillis();
                    synchronized (messageQueue) {
                        messageQueue.put(Product.seq.get(), product);
                    }
                    h.post(new ProduceTask(product));
                    try {
                        Thread.currentThread().sleep((pIndex / 10 + 1) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ++pIndex;
                }
            }
        });
        produceThread.start();
    }

    private void doConsume() {
        consumeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDestroyed) {
                    if (messageQueue.size() > 0) {
                        synchronized (messageQueue) {
                            Product product = messageQueue.valueAt(0);
                            messageQueue.removeAt(0);
                            if (product != null) {
                                h.post(new ConsumeTask(product));
                            }
                        }

                    }
                }
            }
        });
        consumeThread.start();
    }

    class ConsumeTask implements Runnable {

        Product product;

        public ConsumeTask(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            consumerAdapter.addData(product);
            rvConsumer.scrollBy(0, 100);
        }
    }

    class ProduceTask implements Runnable {

        Product product;

        public ProduceTask(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            producerAdapter.addData(product);
            rvProducer.scrollBy(0, 100);
//            rvProducer.scrollToPosition(producerAdapter.getItemCount());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        clearMessageQueue();
    }


    private void clearMessageQueue() {
        for (int i = messageQueue.size()-1; i >=0 ; --i) {
            Product product = messageQueue.valueAt(i);
            if(Integer.parseInt(product.content) % 2 == 0) {
                messageQueue.removeAt(i);
            }
        }

    }
}
