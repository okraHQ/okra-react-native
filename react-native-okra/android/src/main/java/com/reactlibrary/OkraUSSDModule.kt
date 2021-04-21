package com.reactlibrary

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import com.facebook.react.bridge.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hover.sdk.api.Hover
import com.hover.sdk.api.HoverParameters
import com.hover.sdk.permissions.PermissionHelper
import com.reactlibrary.interfaces.BankServices
import com.reactlibrary.models.IntentData
import com.reactlibrary.services.USSDActionDeterminer
import com.reactlibrary.services.USSDActionDeterminerImpl
import com.reactlibrary.utils.BankUtils
import com.reactlibrary.utils.PaymentUtils
import org.json.JSONObject
import java.lang.reflect.Type


class OkraUSSDModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext){
    private var mActivityEventListener: ActivityEventListener? = null
    var generalmapOkraOptions: MutableMap<String, Any>? = null
    private var ussdActionDeterminer: USSDActionDeterminer? = null
    init {
          mActivityEventListener = object : BaseActivityEventListener() {
            override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, intent: Intent) {
                generalmapOkraOptions?.let { ussdActionDeterminer?.onUSDDResultReceived(intent, it) }
            }
        }
        reactContext.addActivityEventListener(mActivityEventListener)
    }

    override fun getName(): String {
        return "OkraUSSD"
    }


    private var mPickerPromise: Promise? = null



    @ReactMethod
    fun initHover() {
        Hover.initialize(reactApplicationContext)
        ussdActionDeterminer = currentActivity?.let { USSDActionDeterminerImpl(it) }
    }

    @ReactMethod
    fun initOptions(options:String){
        val type: Type = object : TypeToken<MutableMap<String, Any>>() {}.type
        val myMap: MutableMap<String, Any> = Gson().fromJson(options, type)
        generalmapOkraOptions = myMap
    }





    @ReactMethod
    fun permissionOn(permission:String,promise: Promise){
        var value = false
         when (permission) {
            "message", "messages" -> {
                value = PermissionHelper(reactApplicationContext).hasSmsPerm()
                promise.resolve(value)
            }
            "accessibility" -> {
                val ph = PermissionHelper(reactApplicationContext)
                value = ph.hasAccessPerm() && ph.hasBasicPerms() && ph.hasPhonePerm() && ph.hasOverlayPerm()
                promise.resolve(value)

            }
            else -> promise.resolve(false)
        }
    }

    @ReactMethod
    fun switchPermissionOn(permission: String){
        val ph = PermissionHelper(reactApplicationContext)
        if (permission == "message" || permission == "messages") {
            if (!ph.hasBasicPerms()) {
                ph.requestBasicPerms(currentActivity, 1)
            }
            if (!ph.hasPhonePerm()) {
                ph.requestPhone(currentActivity, 1)
            }
        } else {
            if (!ph.hasAccessPerm()) {
                ph.requestAccessPerm()
            }
            if (!ph.hasOverlayPerm()) {
                ph.requestOverlayPerm()
            }
        }
    }

    @ReactMethod
    fun hasUssdFeature(promise: Promise) {
        promise.resolve(true)
    }

    @ReactMethod
    fun startUSSDPayment(json:String){

        try {
            PaymentUtils.startPayment(json, currentActivity!!)
        } catch (ex: Exception) {
            println(ex)
        }
    }

    @ReactMethod
    fun openUSSD(json: String){
        try {
            val jsonObject = JSONObject(json)
            val payment = if (jsonObject.has("payment")) jsonObject.getString("payment") else "false"
            val options  =  try {
                if (jsonObject.has("options")) jsonObject.getJSONObject("options").toString() else ""
            }catch (ex:Exception){
                ""
            }
            val bankSlug = jsonObject.getJSONObject("bank").getString("slug")
            val bgColor = jsonObject.getJSONObject("bank").getString("bg")
            val accentColor = jsonObject.getJSONObject("bank").getString("accent")
            val buttonColor = jsonObject.getJSONObject("bank").getString("button")
            val pin = if (jsonObject.has("pin")) if (jsonObject.getString("pin").trim { it <= ' ' }.isEmpty()) "" else jsonObject.getString("pin") else ""
            val nuban = if (jsonObject.has("account")) jsonObject.getJSONObject("account").getString("number").trim { it <= ' ' } else ""
            val recordId = if (jsonObject.has("record")) jsonObject.getString("record") else ""
            val bankServices: BankServices = BankUtils.getBankImplementation(bankSlug)
            PaymentUtils.currentBankService = bankServices
            PaymentUtils.lastPaymentAction = false
            PaymentUtils.paymentConfirmed = true
            PaymentUtils.bankSlug = bankSlug
            PaymentUtils.bgColor = bgColor
            PaymentUtils.accentColor = accentColor
            PaymentUtils.options = options
            PaymentUtils.buttonColor = buttonColor
            PaymentUtils.pin = pin
            PaymentUtils.nuban = nuban
            PaymentUtils.recordId = recordId
            BankUtils.fireIntent(currentActivity, bankServices.getActionByIndex(1), IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor, payment, options))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
