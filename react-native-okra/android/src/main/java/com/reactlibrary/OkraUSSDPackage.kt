package com.reactlibrary

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.JavaScriptModule
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import java.util.*
import kotlin.collections.ArrayList

class OkraUSSDPackage : ReactPackage {

     fun createJSModules(): MutableList<Class<out JavaScriptModule>> {
        return emptyList<Class<out JavaScriptModule>>().toMutableList()
    }


    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {
        val modules = ArrayList<NativeModule>()
        modules.add(OkraUSSDModule(reactContext))
        return modules
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return Collections.emptyList()
    }

}