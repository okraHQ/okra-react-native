package com.reactlibrary;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.reactlibrary.activity.OkraWebActivity;
import com.reactlibrary.utils.OkraOptions;

public class Okra {

    public static Context baseContext;

    public static void create(Activity activity, OkraOptions okraOptions){
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_READ_PHONE_STATE = 1;
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }else {
            baseContext = activity;
            Intent intent = new Intent(activity, OkraWebActivity.class);
            intent.putExtra("okraOptions", okraOptions);
            activity.startActivity(intent);
        }
    }
}