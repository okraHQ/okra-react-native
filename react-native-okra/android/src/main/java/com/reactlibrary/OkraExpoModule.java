package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class OkraExpoModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public OkraExpoModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

    }

    @Override
    public String getName() {
        return "OkraExpo";
    }


}
