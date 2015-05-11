package com.study.radasm.go.WebActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.study.radasm.go.R;
import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/6.
 */
public class WebActivity extends Activity {
    private WebView webView;
    private static final String anchor="http://blog.sina.com.cn/s/blog_593a18380102vtjy.html?tj=tiyu";

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // 进行全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mUrl=getIntent().getBundleExtra(Constants.BUNDLE).getString(Constants.BUNDLE_URL,anchor);
        initView();
        initData();
    }

    private void initView() {
        webView= (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setJavaScriptEnabled(true);

        webSettings.setAppCacheEnabled(true);

        webSettings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });


    }

    private void initData() {
        webView.loadUrl(mUrl);
    }


}
