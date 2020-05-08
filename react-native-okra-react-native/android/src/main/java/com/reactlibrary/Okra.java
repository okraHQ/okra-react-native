package com.reactlibrary;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.react.bridge.ReactApplicationContext;
import com.reactlibrary.activity.OkraWebActivity;
import com.reactlibrary.utils.OkraOptions;

public class Okra {

    public static Context baseContext;
    public static ReactApplicationContext reactApplicationContext;

    public static void create(ReactApplicationContext reactContext, Activity activity, OkraOptions okraOptions){
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_READ_PHONE_STATE = 1;
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }else {
            reactApplicationContext = reactContext;
            baseContext = activity;
            Intent intent = new Intent(activity, OkraWebActivity.class);
            intent.putExtra("okraOptions", okraOptions);
            activity.startActivity(intent);
        }
    }
}