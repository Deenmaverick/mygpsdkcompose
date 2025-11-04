package com.deensdk.mygp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.service.network.response.gphome.AdDataForGP
import com.deenislamic.sdk.service.network.response.gphome.GPFloatingData
import com.deenislamic.sdk.utils.toast
import com.deenislamic.sdk.views.gphome.GPHome
import com.deensdk.mygp.views.DeenSdkScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeenSdkComposeFragment : Fragment(), DeenSDKCallback {

    private lateinit var gphome: GPHome
    private val token = "jwt-token"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DeenSDKCore.setupPermissionRequest(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 🟢 host your GPHome view inside Compose
        gphome = GPHome(requireContext())
        gphome.initView("homecard", this)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )


            setContent {
                DeenSdkScreen(
                    gphome = gphome,
                    onEnglishClick = {
                        DeenSDKCore.initSDK(
                            context = requireContext(),
                            language = "en",
                            baseApiUrl = "",
                            baseServiceUrl = "",
                            baseResourceUrl = "",
                            baseGPHomeUrl = "https://mygp-home.deenislamic.com/"
                        )
                        gphome.changeLanguage("en")
                    },
                    onBanglaClick = {
                        DeenSDKCore.initSDK(
                            context = requireContext(),
                            language = "bn",
                            baseApiUrl = "",
                            baseServiceUrl = "",
                            baseResourceUrl = "",
                            baseGPHomeUrl = "https://mygp-home.deenislamic.com/"
                        )
                        gphome.changeLanguage("bn")
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DeenSDKCore.setGPKEY("MyGP Testing Key")


    }

    // ----------------------------
    // Callbacks (same as before)
    // ----------------------------

    override fun DeenRequireToken() {
        Log.d("GPFloatingCard", "DeenRequireToken")

        lifecycleScope.launch(Dispatchers.IO) {
           DeenSDKCore.setToken(token)
        }
    }

    override fun onDeenTriggerEvent(event_name: String, param: String, adDataForGP: AdDataForGP?) {
        Log.d("GPFloatingCard", "$event_name $param")
        lifecycleScope.launch(Dispatchers.Main) {
            context?.toast("Event: $event_name,$param")
        }
    }

    override suspend fun DeenTokenExpired(): String {
        Log.d("GPFloatingCard", "DeenTokenExpired")

        lifecycleScope.launch(Dispatchers.Main) {
            context?.toast("Token expired")
        }

        return withContext(Dispatchers.IO) {
            delay(500)
            token
        }
    }

    override fun onDeenSDKInitSuccess() {
        Log.d("GPFloatingCard", "onDeenSDKInitSuccess")
    }

    override fun onDeenSDKInitFailed() {
        Log.d("GPFloatingCard", "onDeenSDKInitFailed")
    }

    override fun gpFloatingPinnedStatus(isPinned: Boolean) {
        Log.d("GPFloatingCard", "gpFloatingPinnedStatus $isPinned")
    }

    override fun gpFloatingCardData(data: GPFloatingData?) {
        //Log.d("GPMainActivity", "gpFloatingCardData ${Gson().toJson(data)}")
    }
}
