package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

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
    public void openOkraWidget(ReadableMap options){
        ArrayList  products = new ArrayList<Enums.Product>();
        for(int index = 0; index < options.getArray("products").size(); index++){
            String product = options.getArray("products").getString(index);
            products.add(Enums.Product.valueOf(product));
        }
        OkraOptions okraOptions = new OkraOptions(true, options.getString("key"),options.getString("token"), products, Enums.Environment.valueOf(options.getString("environment")),options.getString("clientName"));
        Okra.create(getReactApplicationContext(), okraOptions);
    }
}
