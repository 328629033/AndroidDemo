package com.demo.android.spring_animation;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.demo.android.R;
import com.demo.android.databinding.ActivitySpringAnimationBinding;

/**
 * Created by herr.wang on 2017/5/25.
 */

public class SpringAnimationActivity extends AppCompatActivity {
    SpringScrollView springScrollView;
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpringAnimationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_spring_animation);
        springScrollView = binding.ssvContainer;
        webView = binding.wvTest;
        progressBar = binding.pbWeb;
        springScrollView.setTargetView(webView);
        webView.setWebViewClient(new WebClient());
        webView.loadUrl("http://www.baidu.com");
    }

    class WebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

}
