package com.reactlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import com.reactlibrary.Okra;
import com.reactlibrary.handlers.OkraHandler;

public class WebInterface {
    Context mContext;
    OkraOptions okraOptions;

    // Instantiate the interface and set the context
    public WebInterface(Context c, OkraOptions okraOptions) {
        mContext = c;
        this.okraOptions = okraOptions;
    }

    @JavascriptInterface
    public void exitModal() {
        Intent intent = new Intent(mContext, Okra.baseContext.getClass());
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void onSuccess(String json) {
        OkraHandler.data = json;
        OkraHandler.isSuccessful = true;
        OkraHandler.isDone = true;
    }

    @JavascriptInterface
    public void onError(String json) {
        OkraHandler.data = json;
        OkraHandler.hasError = true;
        OkraHandler.isDone = true;
    }

    @JavascriptInterface
    public void onClose(String json) {}
}
