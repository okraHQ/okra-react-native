package com.reactlibrary.utils;

import com.facebook.react.bridge.ReadableMap;
import com.reactlibrary.models.Enums;
import com.reactlibrary.models.Filter;
import com.reactlibrary.models.Guarantor;

import java.util.ArrayList;
import java.util.Collections;

public class GeneralUtils {

    static public OkraOptions parseToOkraOptions(OkraOptions okraOptions, ReadableMap options){

        if(options.hasKey("color")){
            okraOptions.setColor(options.getString("color"));
        }

        if(options.hasKey("limit")){
            okraOptions.setLimit(options.getString("limit"));
        }

        if(options.hasKey("corporate")) {
            okraOptions.setCorporate(options.getBoolean("corporate"));
        }

        if(options.hasKey("connectMessage")) {
            okraOptions.setConnectMessage(options.getString("connectMessage"));
        }

        if(options.hasKey("callback_url")) {
            okraOptions.setCallback_url(options.getString("callback_url"));
        }

        if(options.hasKey("redirect_url")) {
            okraOptions.setRedirect_url(options.getString("redirect_url"));
        }

        if(options.hasKey("logo")) {
            okraOptions.setLogo(options.getString("logo"));
        }

        if(options.hasKey("widget_success")) {
            okraOptions.setWidget_success(options.getString("widget_success"));
        }

        if(options.hasKey("currency")) {
            okraOptions.setCurrency(options.getString("currency"));
        }

        if(options.hasKey("exp")) {
            okraOptions.setExp(options.getString("exp"));
        }

        if(options.hasKey("success_title")) {
            okraOptions.setSuccess_title(options.getString("success_title"));
        }

        if(options.hasKey("success_message")) {
            okraOptions.setSuccess_message(options.getString("success_message"));
        }

        if(options.hasKey("guarantors")) {
           try{
               boolean status = options.getMap("guarantors").hasKey("status") && options.getMap("guarantors").getBoolean("status");
               String message = options.getMap("guarantors").hasKey("message") ? options.getMap("guarantors").getString("message") : "";
               int number =  options.getMap("guarantors").hasKey("message") ? options.getMap("guarantors").getInt("number") : 3;

               okraOptions.setGuarantors(new Guarantor(status, message, number));

           }catch (Exception exception){
               okraOptions.setGuarantors(new Guarantor(false, "", 3));
           }
        }

        if(options.hasKey("filter")) {
            ArrayList<String> banks = new ArrayList<>();
            try{
                String industry_type =  options.getMap("filter").hasKey("industry_type") ? options.getMap("filter").getString("industry_type") : Enums.IndustryType.all.toString();
                if(options.getMap("filter").hasKey("banks")){
                    for(int index = 0; index < options.getMap("filter").getArray("banks").size(); index++){
                        banks.add( options.getMap("filter").getArray("banks").getString(index));
                    }
                }
                okraOptions.setFilter(new Filter(industry_type, banks));
            }catch (Exception exception){
                okraOptions.setFilter(new Filter(Enums.IndustryType.all.name(), banks));
            }
        }

        return okraOptions;
    }
}
