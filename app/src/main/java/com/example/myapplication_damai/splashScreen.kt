package com.example.myapplication_damai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    var showSplash by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        delay(5000)

        showSplash = false

        onFinished()
    }

    if (showSplash) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            // 开屏广告图片
            Image(
                painter = painterResource(
                    id = R.drawable.splash_ad
                ),
                contentDescription = "开屏广告",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // 右上角跳过按钮
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 55.dp,
                        end = 20.dp
                    )
                    .size(
                        width = 60.dp,
                        height = 36.dp
                    )
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(
                        Color.Black.copy(alpha = 0.45f)
                    )
                    .clickable {
                        onFinished()
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "跳过",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
