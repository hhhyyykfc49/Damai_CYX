package com.example.myapplication_damai
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopGradientArea(
            navController = navController
        )
        Spacer(modifier = Modifier.height(16.dp))

        ConcertInfoCard()

        SessionModule()

        EventInfoModule()

        NoticeModule()

        TicketStrategyModule()

    }
}


@Composable
fun TopGradientArea(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE8E8),
                        Color.White
                    )
                )
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 50.dp
                ),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            // 返回按钮
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF666666)
            ) {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Row {

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFF666666)
                ) {

                    IconButton(
                        onClick = {}
                    ) {

                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFF666666)
                ) {

                    IconButton(
                        onClick = {}
                    ) {

                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConcertInfoCard() {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            // 海报
            Image(
                painter = painterResource(
                    id = R.drawable.lazy_row_banner1
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = buildAnnotatedString {

                        withStyle(
                            SpanStyle(
                                color = Color(0xFFb594fc),
                                background = Color(0xFFe0e7fc)
                            )
                        ) {
                            append("总票代 ")
                        }

                        append(
                            "福州·2026苏见信「尽兴而活」巡回演唱会-福州站"
                        )
                    },
                    fontSize = 16.sp,
                    maxLines = 2,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // 价格
                Text(
                    text = "¥280-1600",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

            Row(
            ) {
                // 套票
                Text(
                    text = "2人套票 | 7.4折",
                    color = Color(0xFFFF7A00),
                    modifier = Modifier
                        .background(
                            Color(0xFFFFF2E6),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                )

                Text(
                    text = "全部 >",
                    color=Color(0xFFa7abb4),
                    fontSize = 16.sp,
                 modifier = Modifier.padding(start = 50.dp)
                )
            }
            }
        }
    }


}

data class SessionItem(
    val city: String,
    val date: String = "",
    val selected: Boolean = false
)
@Composable
fun SessionCard(
    item: SessionItem
) {

    Card(
        modifier = Modifier
            .width(110.dp)
            .height(72.dp),

        shape = RoundedCornerShape(12.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                if (item.selected)
                    Color(0xFFFFF3EE)
                else
                    Color.White
        ),

        border =
            if (item.selected)
                BorderStroke(
                    1.dp,
                    Color(0xFFFF6A3D)
                )
            else
                BorderStroke(
                    1.dp,
                    Color(0xFFEAEAEA)
                )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = item.city,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color =
                    if (item.selected)
                        Color(0xFFFF6A3D)
                    else
                        Color.Black
            )

            if (item.date.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = item.date,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
fun SessionModule() {

    val sessionList = listOf(

        SessionItem(
            city = "沈阳站"
        ),

        SessionItem(
            city = "福州站",
            date = "2026.07.18 周六",
            selected = true
        ),

        SessionItem(
            city = "求加场"
        )
    )

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

    ) {


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(sessionList) {

                SessionCard(it)

            }
        }
    }
}

@Composable
fun EventInfoModule() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "2026.07.25 周六 19:30",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "约120分钟（以现场为准）",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            HorizontalDivider()

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "福州市 · 福州海峡奥林匹克体育中心综合馆",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "福州市仓山区东岭路333号",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}
@Composable
fun NoticeModule() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text("✔ 重要通知")

                Text("⚠ 条件退")

                Text("✔ 实名制购票和入场")

                Text("...")
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "预售 | 本商品为预售，正式开票后将第一时间配票",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
    }
}
@Composable
fun TicketStrategyModule() {

    val strategyBrush = Brush.linearGradient(

        colors = listOf(
            Color(0xFFff460d),
            Color.White
        ),

        start = Offset(0f, -3000f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                brush = strategyBrush,
                shape = RoundedCornerShape(16.dp)
            )
          ,


        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor =Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "抢票攻略",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "预选本次实名抢票观演人,抢票快人一步",
                    fontSize = 14.sp,
                    color = Color.Black
                )



            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6A3D)
                )
            ) {

                Text(
                    text = "去设置",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun WantWatchModule() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "大家都想看",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "43.9万人想看 >",
                color = Color(0xFFFF6A3D),
                fontSize = 13.sp
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DataScreenPreview() {
   DetailScreen(navController = rememberNavController())
}