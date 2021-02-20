package com.reactlibrary.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.reactlibrary.Okra;
import com.reactlibrary.R;
import com.reactlibrary.utils.OkraOptions;
import com.reactlibrary.utils.WebInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class OkraWebActivity extends AppCompatActivity {

    Map<String, Object> generalmapOkraOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        final Map<String, Object> mapOkraOptions = (Map<String, Object>) getIntent().getSerializableExtra("okraOptions");
        generalmapOkraOptions = mapOkraOptions;
        if(getIntent().hasExtra("okraOptions")){
            Map<String, Object> deviceInfo = new HashMap<>();
            deviceInfo.put("deviceName", Build.BRAND);
            deviceInfo.put("deviceModel", Build.MODEL);
            deviceInfo.put("longitude", 0.0);
            deviceInfo.put("latitude", 0.0);
            deviceInfo.put("platform", "android");
            mapOkraOptions.put("uuid", Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
            mapOkraOptions.put("deviceInfo", deviceInfo);
            mapOkraOptions.put("isWebview", true);
            mapOkraOptions.put("source", "rn-android");
        }
        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
      //  final ProgressBar progressBar = findViewById(R.id.progressBar);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this), "Android");


        okraLinkWebview.loadUrl("https://mobile.okra.ng/");

        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                Boolean shouldClose = Boolean.valueOf(linkData.get("shouldClose"));
                if (shouldClose) {
                    Intent intent = new Intent(OkraWebActivity.this, Okra.baseContext.getClass());
                    startActivity(intent);
                } else {
                    return false;
                }
                return true;
            }

            public void onPageFinished(WebView view, String weburl){
               // progressBar.setVisibility(View.GONE);
                String rr = new JSONObject(mapOkraOptions).toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");");
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
                return telephonyManager.getDeviceId() == null ? "" : telephonyManager.getDeviceId();
            }
        }catch (Exception exception){
            return "";
        }
    }
}
