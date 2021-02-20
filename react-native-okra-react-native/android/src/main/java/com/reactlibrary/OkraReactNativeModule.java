package com.reactlibrary;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.reactlibrary.models.Enums;
import com.reactlibrary.utils.GeneralUtils;
import com.reactlibrary.utils.OkraOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OkraReactNativeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public OkraReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "OkraReactNative";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void openOkraWidget(Map<String, Object> okraOptions){
        Okra.create(getReactApplicationContext(), getCurrentActivity(), okraOptions);
    }

}