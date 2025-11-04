package com.deensdk.mygp

import android.app.Application
import com.deenislamic.sdk.DeenSDKCore

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        DeenSDKCore.initSDK(
            context = this,
            language = "en",
            baseApiUrl = ""/*"https://mygp-dev.grameenphone.com/mygpapi/deenapi/"*/,
            baseServiceUrl = "",
            baseResourceUrl = "",
            baseGPHomeUrl = "https://mygp-home.deenislamic.com/"
        )
    }

}