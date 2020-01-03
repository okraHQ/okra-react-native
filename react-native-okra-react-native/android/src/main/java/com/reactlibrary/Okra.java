package com.reactlibrary;


import android.content.Context;
import android.content.Intent;
import com.reactlibrary.activity.OkraWebActivity;
import com.reactlibrary.utils.OkraOptions;

public class Okra {

    public static Context baseContext;

    public static void create(Context context, OkraOptions okraOptions){
        baseContext = context;
        Intent intent = new Intent(context, OkraWebActivity.class);
        intent.putExtra("okraOptions", okraOptions);
        context.startActivity(intent);
    }

}

/**
 *
 * int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
 *
 *         if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
 *             int REQUEST_READ_PHONE_STATE = 1;
 *             ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
 *         } else {
 *             baseContext = context;
 *             Intent intent = new Intent(context, OkraWebActivity.class);
 *             intent.putExtra("okraOptions", okraOptions);
 *             context.startActivity(intent);
 *         }
 *
 * **/