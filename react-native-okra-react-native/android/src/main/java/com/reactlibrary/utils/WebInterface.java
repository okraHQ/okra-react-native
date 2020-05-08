package com.reactlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactlibrary.Okra;

public class WebInterface {
    private Context mContext;

    // Instantiate the interface and set the context
    public WebInterface(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void exitModal() {
        Intent intent = new Intent(mContext, Okra.baseContext.getClass());
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void onSuccess(String json) {
        try {
            Okra.reactApplicationContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onSuccess", json);
        } catch (Exception e){
            generalError(e.getMessage());
        }
    }

    @JavascriptInterface
    public void onError(String json) {
        try {
            Okra.reactApplicationContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onError", json);
        } catch (Exception e){
            generalError(e.getMessage());
        }
    }

    @JavascriptInterface
    public void onClose(String json) {
        try {
            Okra.reactApplicationContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onClose", json);
        } catch (Exception e){
            generalError(e.getMessage());
        }
    }

    private void generalError(String error){
        try {
            Okra.reactApplicationContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onClose", error);
        } catch (Exception ignored){}
    }
}
