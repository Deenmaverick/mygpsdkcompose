package com.deensdk.mygp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deenislamic.sdk.views.gphome.GPHome

@Composable
fun DeenSdkScreen(
    gphome: GPHome,
    onEnglishClick: () -> Unit,
    onBanglaClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            //  Show your custom GPHome view
            GPHomeView(gpHome = gphome)

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.wrapContentSize().padding(2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(onClick = onEnglishClick) {
                    Text("English")
                }
                Button(onClick = onBanglaClick) {
                    Text("Bangla")
                }
            }
        }
    }
}

