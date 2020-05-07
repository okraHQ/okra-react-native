package com.reactlibrary.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.reactlibrary.Okra;
import com.reactlibrary.R;
import com.reactlibrary.handlers.OkraHandler;
import com.reactlibrary.utils.OkraOptions;
import com.reactlibrary.utils.WebInterface;

import java.util.HashMap;


public class OkraWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        final OkraOptions okraOptions = (OkraOptions) getIntent().getSerializableExtra("okraOptions");
        okraOptions.setImei(getIMEI(this));

        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this, okraOptions), "Android");


        okraLinkWebview.loadUrl("https://app.okra.ng/mobile.html"); //http://e630adb5.ngrok.io/mobile.html

        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                Boolean shouldClose = Boolean.valueOf(linkData.get("shouldClose"));
                if (shouldClose) {
                    Intent intent = new Intent(OkraWebActivity.this, Okra.baseContext.getClass());
                    intent.putExtra("okraHandler", new OkraHandler());
                    startActivity(intent);
                } else {
                    return false;
                }
                return true;
            }

            public void onPageFinished(WebView view, String weburl){
                System.out.println(new Gson().toJson(okraOptions));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");");
                }
            }
        });
    }


    public HashMap<String, String> parseLinkUriData(Uri linkUri) {
        HashMap<String, String> linkData = new HashMap<String, String>();
        for (String key : linkUri.getQueryParameterNames()) {
            linkData.put(key, linkUri.getQueryParameter(key));
        }
        return linkData;
    }

    public String getIMEI(Activity activity) {
        try{
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "null";
            }else {
                if (telephonyManager == null) return "null";
                return telephonyManager.getDeviceId() == "null" ? "" : telephonyManager.getDeviceId();
            }
        }catch (Exception exception){
            return "";
        }
    }
}
