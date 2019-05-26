package com.demo.android.mulitprocess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class MultiProcessActivity extends AppCompatActivity {
    View btnMessenger, btnAIDL, btnNative, btnSendMessage, btnDisconnect;
    IInteract interaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_process);
        btnMessenger = findViewById(R.id.btn_messenger);
        btnAIDL = findViewById(R.id.btn_aidl);
        btnNative = findViewById(R.id.btn_native);
        btnSendMessage = findViewById(R.id.btn_send);
        btnDisconnect = findViewById(R.id.btn_disconnect);

        btnMessenger.setOnClickListener(onClickListener);
        btnSendMessage.setOnClickListener(onClickListener);
        btnNative.setOnClickListener(onClickListener);
        btnAIDL.setOnClickListener(onClickListener);
        btnDisconnect.setOnClickListener(onClickListener);
        Printer.print(new NativeInteract().getMessage());
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_messenger:
                    interaction = new MessengerInteract(MultiProcessActivity.this);
                    interaction.connect2Server();
                    break;
                case R.id.btn_aidl:
                    interaction = new AIDLInteract(MultiProcessActivity.this);
                    interaction.connect2Server();
                    break;
                case R.id.btn_native:
                    interaction = new NativeInteract();
                    interaction.connect2Server();
                    break;
                case R.id.btn_send:
                    if(interaction != null){
                        interaction.sendMessage();
                    }
                    break;
                case R.id.btn_disconnect:
                    if(interaction != null) {
                        interaction.disConnect();
                    }
                    break;
            }
        }
    };

}
