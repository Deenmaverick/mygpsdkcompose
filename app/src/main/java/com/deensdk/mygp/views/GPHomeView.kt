package com.deensdk.mygp.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.deenislamic.sdk.views.gphome.GPHome

@Composable
fun GPHomeView(gpHome: GPHome){
    AndroidView(factory = {gpHome},
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}