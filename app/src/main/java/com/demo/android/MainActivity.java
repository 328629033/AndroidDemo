package com.demo.android;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.demo.android.activity.LaunchActivity;
import com.demo.android.animation.AnimationActivity;
import com.demo.android.apt.AptActivity;
import com.demo.android.brightness.BrightnessActivity;
import com.demo.android.concurrent.ConcurrentActivity;
import com.demo.android.databinding.DataBindingActivity;
import com.demo.android.event.EventActivity;
import com.demo.android.fragment.FragmentActivity;
import com.demo.android.hierachy.ViewServer;
import com.demo.android.jar.DynamicLoadJarActivity;
import com.demo.android.mulitprocess.MultiProcessActivity;
import com.demo.android.mulitprocess.Printer;
import com.demo.android.proxy.ProxyActivity;
import com.demo.android.recyclerview.RecyclerViewActivity;
import com.demo.android.selfview.ViewActivity;
import com.demo.android.service.ServiceActivity;
import com.demo.android.spring_animation.SpringAnimationActivity;
import com.demo.android.support.SupportDesignActivity;
import com.demo.android.util.WindowUtil;
import com.demo.android.viewpager.ViewPagerActivity;
import com.demo.android.vm.VMActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context;
    private RecyclerView rvDemoList;
    private List<DemoBean> demos;
    private Handler handler;
    MediaPlayer mediaPlayer;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Debug.startMethodTracing();
        setContentView(R.layout.activity_main);
        rvDemoList = (RecyclerView) findViewById(R.id.rv_demo_list);
        initDemo();
        handler = new Handler();
//        handler.post(playTask);
        ViewServer.get(this).addWindow(this);
        Debug.stopMethodTracing();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("warn");
//        builder.setMessage("what do you want");
//        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
        Intent it = new Intent(this, NotificationService.class);
        startService(it);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(context.getApplicationContext(), LaunchActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.getApplicationContext().startActivity(it);
//                handler.postDelayed(this, 5000);
            }
        }, 5000);

//        View decorView = getWindow().getDecorView().findViewById(android.R.id.content);
//
//        if(decorView instanceof FrameLayout){
//            Printer.print("1111111111");
//            TextView textView = new TextView(context);
//            textView.setText("1111111111111111");
//            ((FrameLayout)decorView).addView(textView);
//        }
    }

    Runnable playTask = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, R.raw.ringtone);
            } else {
//                mediaPlayer.reset();
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    handler.postDelayed(playTask, 3000);
                }
            });
            mediaPlayer.start();
        }
    };


    private void showTimePicker(){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Printer.print("hour is " + hourOfDay + ", minute is " + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
        timePickerDialog.setTitle("选择时间");
    }


    private void initDemo() {
        demos = new ArrayList<>();
        demos.add(getDemoBean("data-binding demo", DataBindingActivity.class));
        demos.add(getDemoBean("self-view demo", ViewActivity.class));
        demos.add(getDemoBean("animation demo", AnimationActivity.class));
        demos.add(getDemoBean("event demo", EventActivity.class));
        demos.add(getDemoBean("apt demo", AptActivity.class));
        demos.add(getDemoBean("concurrent demo", ConcurrentActivity.class));
        demos.add(getDemoBean("support-design demo", SupportDesignActivity.class));
        demos.add(getDemoBean("dynamic-load-jar demo", DynamicLoadJarActivity.class));
        demos.add(getDemoBean("spring-animation demo", SpringAnimationActivity.class));
        demos.add(getDemoBean("fragment demo", FragmentActivity.class));
        demos.add(getDemoBean("recyclerview demo", RecyclerViewActivity.class));
        demos.add(getDemoBean("viewpager demo", ViewPagerActivity.class));
        demos.add(getDemoBean("VM demo", VMActivity.class));
        demos.add(getDemoBean("mulitprocess demo", MultiProcessActivity.class));
        demos.add(getDemoBean("Service demo", ServiceActivity.class));
        demos.add(getDemoBean("Brightness demo", BrightnessActivity.class));
        demos.add(getDemoBean("LaunchMode demo", LaunchActivity.class));
        demos.add(getDemoBean("Proxy demo", ProxyActivity.class));
        rvDemoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvDemoList.setAdapter(adapter);
    }

    private DemoBean getDemoBean(String demoName, Class demoClass) {
        DemoBean demoBean = new DemoBean();
        demoBean.demoName = demoName;
        demoBean.demoClass = demoClass;
        return demoBean;
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextViewHolder holder = new TextViewHolder(new TextView(context));
            holder.itemView.setPadding(0, 0, 0, WindowUtil.dip2px(context, 8));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class clazz = ((DemoBean) v.getTag()).demoClass;
                    Intent it = new Intent(context, clazz);
                    startActivity(it);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setTag(demos.get(position));
            ((TextView) ((TextViewHolder) holder).itemView).setText(demos.get(position).demoName);
        }

        @Override
        public int getItemCount() {
            return demos.size();
        }

        class TextViewHolder extends RecyclerView.ViewHolder {
            public TextViewHolder(View contentView) {
                super(contentView);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Printer.print("onPause...");
        handler.removeCallbacks(playTask);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Printer.print("onRestart...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Printer.print("onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, NotificationService.class));
        Printer.print("onDestroy...");
//        stopService(it);
        handler.removeCallbacksAndMessages(null);
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
        Printer.print("onResume...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Printer.print("onStart...");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        showTimePicker();
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
