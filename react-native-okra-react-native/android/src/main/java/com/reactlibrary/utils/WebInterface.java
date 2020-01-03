package com.reactlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import com.reactlibrary.Okra;

public class WebInterface {
    Context mContext;

    // Instantiate the interface and set the context
    public WebInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void exitModal() {
        Intent intent = new Intent(mContext, Okra.baseContext.getClass());
        mContext.startActivity(intent);
    }
}
