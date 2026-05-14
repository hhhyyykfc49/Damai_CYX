package com.example.myapplication_damai

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 分类数据（不变）
data class CategoryItem(
    val icon: Int,
    val name: String
)

val categoryList = listOf(
    CategoryItem(R.drawable.ic_concert, "演唱会"),
    CategoryItem(R.drawable.ic_music_festival, "音乐节"),
    CategoryItem(R.drawable.ic_livehouse, "Livehouse"),
    CategoryItem(R.drawable.ic_drama, "话剧音乐剧"),
    CategoryItem(R.drawable.ic_talk_show, "脱口秀"),
    CategoryItem(R.drawable.ic_exhibition, "展览"),
    CategoryItem(R.drawable.ic_travel, "旅游"),
    CategoryItem(R.drawable.ic_movie, "电影"),
    CategoryItem(R.drawable.ic_dance, "舞蹈舞剧"),
    CategoryItem(R.drawable.ic_all, "全部")
)

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            // 🔥 三层渐变 + statusBarsPadding 顶吸完全保留
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .statusBarsPadding()
                    .height(120.dp)
//                    .padding(vertical = 10.dp)
                    .background(
                        //第一层：粉色横向渐变
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFF94B4), Color(0xFFFF80A0)),
                            start = Offset(0f, 0f), end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
                    .background(
                      //  第二层：白色半透明叠加
                        brush = Brush.linearGradient(
                            colors=listOf(Color(0x00FFFFFF), Color(0x99FFD4BC)),
                            start =Offset(0f, 0f),end= Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
                    .background(
                     //   第三层：径向渐变
                        brush = Brush.radialGradient(
                            colors=listOf(Color(0x33FFD4BC),Color(0x00FFFFFF)),
                            center = Offset(Float.POSITIVE_INFINITY, 0f), radius = 200f
                        )
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp, end = 2.dp, top=44.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box()
                    {
                        Row( modifier = Modifier
                            .padding(start = 0.dp, end = 2.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.location),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                                    .padding(end=4.dp)
                            )
                            Text(
                                text = "福州",
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .background(Color.White, RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp, bottomEnd = 22.dp, bottomStart = 4.dp))
                            .padding(horizontal = 12.dp)
                            .clickable { },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.search_box),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "|",
                                color=Color(0xFFe9a7c8),
                                fontSize = 30.sp,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                            Text(
                                text = "龙梅子",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 6.dp)
                            )
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .padding(end = 0.dp)
                                    .height(40.dp),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFed88ba)
                                )
                            ) {
                                Text("搜索", color = Color.White)
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(36.dp)
                            .clickable {},
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bubble),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CategoryGrid()
            HotAreaModule()
        }
    }
}

// 分类模块（完全不变）
@Composable
fun CategoryGrid() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    CategoryItemView(categoryList[index])
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    CategoryItemView(categoryList[index + 5])
                }
            }
        }
    }
}

@Composable
fun CategoryItemView(item: CategoryItem) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = item.name,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF333333)
        )
    }
}


//抢票播报站+天天低价
@Composable
fun HotAreaModule(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp) //卡片中间间距
        //============左侧：抢票播报站===============

    ) {
        Card(
            modifier = Modifier
                .weight(1f)   //左右平分宽度
                .height(160.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8F8)),
                elevation= CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ){
                //标题行
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "抢票播报站",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color=Color(0xFF222222)
                    )
                    Text(
                        text = "张学友广州 >",
                        fontSize = 12.sp,
                        color=Color(0xFF999999)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    //内容行:头像+演出名
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.ic_drama),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column()
                            {
                                Text(
                                    text = "G.E.M.邓紫棋 I AN GLORIA世界巡回演唱会...",
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    color=Color(0xFF333333)
                                )
                                Text(
                                    text="39.9万人想看",
                                    fontSize = 11.sp,
                                    color=Color(0xFF888888)
                                )
                            }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    //倒计时按钮
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(28.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(R.drawable.bubble),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop // 图片铺满按钮区域
                        )


                    }

                }
            }
        }
        //================右侧：天天低价=============
        Card(
            modifier = Modifier
                .weight(1f)
                .height(160.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8F8)),
            elevation= CardDefaults.cardElevation(0.dp)
        ) {

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}