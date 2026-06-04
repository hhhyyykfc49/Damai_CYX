package com.example.myapplication_damai

import android.graphics.Paint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.annotation.ColorInt
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


private  val MineTopBg= Color(0xFFF9EAD3)


@Composable
fun MineHeader()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MineTopBg)
            .padding(horizontal = 12.dp, vertical = 24.dp)
    ) {
        //设置+ 消息
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {}){
                Icon(painterResource(R.drawable.ic_setting),null,tint= Color.Unspecified)
            }
            IconButton(onClick = {}){
                Icon(painterResource(R.drawable.ic_message),null, tint = Color.Unspecified)
            }
        }
        Spacer(Modifier.height(8.dp))
        //头像+昵称信息
        Row(verticalAlignment = Alignment.Top){
            Image(
                painterResource(R.drawable.ic_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(Modifier.width(12.dp))
            Column{
                Text("麦子7bGL5", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Image(
                    painterResource(R.drawable.damai_vip),
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                        .padding(top = 3.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    "0关注  |  0粉丝   |   0获赞与观看",
                    fontSize = 13.sp,
                    color=Color.DarkGray,
                    modifier = Modifier.padding(top=6.dp)
                )
            }
        }

    }
}


@Composable
fun VipCardModule(){
    Column(
        modifier = Modifier
            .background(MineTopBg)

    ) { Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal =10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFD2A6))
            .padding(6.dp)
    ){
        Column (modifier = Modifier
            .height(90.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            ) {
                Text(
                    text = "大麦VIP会员.会员中心",
                    fontSize = 16.sp,
                    color = Color(0xFF6d3c23),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(135.dp))

                Text(
                    text = "全部权益 >",
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

            }

            Spacer(Modifier.height(10.dp))
//            红色VIp图标：只限定高度，宽度自适应
          Row(
              verticalAlignment = Alignment.CenterVertically

          ) {
              Image(
                  painter = painterResource(R.drawable.vip),
                  contentDescription = null,
                  modifier = Modifier.height(46.dp),
                  contentScale = ContentScale.Fit

              )
              Spacer(modifier = Modifier.width(10.dp))
              Column(
                  modifier = Modifier.weight(1f)
              ) {
                  Text(
                      text = "你的会员有效期至2026.12.31",
                      fontSize = 15.sp
                  )
                  Text(
                      text = "可享演出优先购、免费票等权益",
                      fontSize = 12.sp,
                      color = Color(0xFF784922),
                      modifier = Modifier.padding(top = 3.dp)
                  )

              }
              Button(
                  onClick={},
                  modifier = Modifier
                      .height(35.dp),
                  shape=RoundedCornerShape(22.dp),
                  colors= ButtonDefaults.buttonColors(
                      containerColor = Color(0xFFfaead4),
                      contentColor = Color(0xFF704933)
                  )
              ){
                  Text("去看看", fontSize = 13.sp,
                      fontWeight = FontWeight.Bold)

              }
          }
        }

    }}
}

//单个功能Item
@Composable
fun MineFourItem(iconRes:Int,name:String,onClick:()->Unit){
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF6F6F6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(33.dp),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, fontSize = 12.sp, color = Color.DarkGray)
    }
}

//整行4个功能卡片容器
@Composable
fun FuncOneRow(navController: NavController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFfefefe))
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MineFourItem(R.drawable.ic_order,"我的订单",{navController.navigate("order")})
            MineFourItem(R.drawable.ic_coupon,"优惠券",{})
            MineFourItem(R.drawable.ic_visitor,"观演人",{})
            MineFourItem(R.drawable.ic_address,"收货地址",{})
        }
    }
}


//@Composable
//fun WantAndBookCard() {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 12.dp),
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(Color.White)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp, horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // 左侧：想看
//            Text(text = "3", fontSize = 22.sp)
//            Spacer(modifier = Modifier.width(3.dp))
//            Text(text = "想看", fontSize = 14.sp, color = Color.DarkGray)
//            Spacer(Modifier.width(80.dp))
//            Text(text = ">", color = Color.Gray, fontSize = 13.sp)
//
//            // 中间分割竖线
//            Box(
//                modifier = Modifier
//                    .width(1.dp)
//                    .height(32.dp)
//                    .background(Color(0xFFEAEAEA))
//
//            )
//
//            // 右侧：抢票预约
//            Spacer(Modifier.width(100.dp ))
//            Text(text = "1", fontSize = 22.sp)
//            Spacer(modifier = Modifier.width(3.dp))
//            Text(text = "抢票预约", fontSize = 14.sp, color = Color.DarkGray)
//            Text(text = "1今日抢 >", fontSize = 13.sp, color = Color(0xFFFF5068))
//        }
//    }
//}

@Composable
fun WantAndBookCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧想看：整列横向铺满
            Column(modifier = Modifier.weight(1f)
                .fillMaxWidth()
                .padding(end = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text("3", fontSize = 22.sp)
                    Spacer(Modifier.width(4.dp))
                    Text("想看", fontSize = 14.sp, color = Color.DarkGray)
                    Spacer(Modifier.weight(1f)) // 剩余空间挤到右边
                    Text(">", color = Color.Gray, fontSize = 15.sp)
                }
            }

            // 中间分割线
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(32.dp)
                    .background(Color(0xFFEAEAEA))
            )

            // 右侧抢票预约
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text("1", fontSize = 22.sp)
                    Spacer(Modifier.width(4.dp))
                    Text("抢票预约", fontSize = 14.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text("1今日抢 >", fontSize = 13.sp, color = Color(0xFFacaeb4))
                }
            }
        }
    }
}



// 数据源
data class FuncInfo(val name: String, val icon: Int)

@Composable
fun FuncTwoRow() {
    val list = listOf(
        FuncInfo("在线客服", R.drawable.ic_service),
        FuncInfo("周边商城", R.drawable.ic_mall),
        FuncInfo("观演团", R.drawable.ic_team_visitor),
        FuncInfo("用户反馈", R.drawable.ic_feedback),
        FuncInfo("帮助中心", R.drawable.ic_setting),
        FuncInfo("设置", R.drawable.ic_message)
    )
    // 列表状态，监听滚动
    val lazyState = rememberLazyListState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            // 横向懒加载列表
            LazyRow(
                state = lazyState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                items(list.size) { index ->
                    MineFourItem(name = list[index].name, iconRes =  list[index].icon, onClick = {})
                }
            }
//            Spacer(Modifier.height(12.dp))
//            // 滚动指示器（圆点跟着滑动）
//            ScrollIndicator(lazyState = lazyState, total = list.size)
        }
    }
}

//@Composable
//fun ScrollIndicator(lazyState: LazyListState, total: Int) {
//    val itemWidth = 70.dp
//    val dotSize = 8.dp
//    // 实时滚动偏移
//    val scrollOffset by remember { derivedStateOf { lazyState.firstVisibleItemScrollOffset.toFloat() } }
//    val currentIndex = lazyState.firstVisibleItemIndex
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        // 灰色静态圆点
//        repeat(total) {
//            Box(
//                Modifier
//                    .size(dotSize)
//                    .clip(CircleShape)
//                    .background(Color(0xFFDDDDDD))
//            )
//        }
//        // 高亮圆点：offset跟随滚动实时位移
//        val offsetPx = with(LocalDensity.current) { itemWidth.toPx() }
//        Box(
//            Modifier
//                .offset(x = ((currentIndex * offsetPx + scrollOffset) / LocalDensity.current.density).dp)
//                .size(dotSize)
//                .clip(CircleShape)
//                .background(Color(0xFFFF5068))
//        )
//    }
//}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MineBannerCarousel() {

    val bannerList = listOf(
        R.drawable.mine_banner1,
        R.drawable.mine_banner2,
        R.drawable.mine_banner3,
        R.drawable.mine_banner4,
        R.drawable.mine_banner5
    )



    val pageState = rememberPagerState(pageCount = { bannerList.size })

    //自动轮播协程
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val next = (pageState.currentPage + 1) % bannerList.size
            pageState.animateScrollToPage(next)
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(top = 4.dp, bottom = 0.dp)) {
            //横向分页轮播
            HorizontalPager(
                state = pageState,
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(12.dp))

            ) { index ->
                Image(
                    painterResource(bannerList[index]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }




        }
    }
}


@Composable
fun DynamicModule() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "动态",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 6.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //左卡片：待评价
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //左侧海报占位
                    Image(
                        painterResource(R.drawable.ic_livere),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 52.dp, height = 50.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("2条待评价", fontSize = 14.sp)
                            //小红点
                            Box(
                                Modifier
                                    .padding(start = 4.dp)
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFF3333))
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text("去评价 >", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            //右卡片：观演招募
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("0元观看好演出", fontSize = 14.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("观演团活动招募中 >", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}


@Composable
fun MineScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        MineHeader()
        VipCardModule()
        FuncOneRow (navController)
        Spacer(Modifier.height(10.dp))
        WantAndBookCard()
        Spacer(Modifier.height(10.dp))
        FuncTwoRow()
        MineBannerCarousel()
        DynamicModule()
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MineScreenPreview() {
    MineScreen(rememberNavController())
}