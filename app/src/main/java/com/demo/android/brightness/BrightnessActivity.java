package com.demo.android.brightness;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by herr.wang on 2017/9/8.
 */

public class BrightnessActivity extends AppCompatActivity {
    Context context;
    int level;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        try {
            level = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        android.provider.Settings.System.putInt(
                context.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS, 80);


        android.provider.Settings.System.putInt(context.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.provider.Settings.System.putInt(
                context.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS, level);
    }
}
