package com.demo.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.R;
import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2017/11/1.
 */

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Printer.print("launch onCreate..." + this);
        setContentView(R.layout.activity_lauch);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Printer.print("launch onNewIntent...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("warn");
        builder.setMessage("what do you want");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Printer.print("onWindowFocusChanged..." + hasFocus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Printer.print("onPause...");
    }

    @Override
    public void finish() {
        super.finish();
//        Intent it = new Intent(getApplicationContext(), LaunchActivity.class);
//        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        getApplicationContext().startActivity(it);
//        Printer.print("launch finish...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Printer.print("launch onDestroy...");
    }
}
