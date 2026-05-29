package com.example.myapplication_damai

import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
    ) {
        //第一层顶吸：渐变搜索栏（滑动到Tab处会被替换)
        stickyHeader {
            HomeGradientTopBar()
        }


        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MyBookAppointModule()
                CategoryGrid()
                HotAreaModule()
                FunctionCardModule()
                BannerCarousel()
                MustSeePerformanceModule()
            }
        }

        //第二层顶吸：横向滑动Tab导航栏（替换原来的搜索栏）
        stickyHeader {
            HomeTabNavigation()
        }
    item {
        PerformanceWaterFallList()
    }


    }
}


@Composable
fun HomeGradientTopBar()
{
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
//   ------------横向Tab导航---------------
@Composable
fun HomeTabNavigation()
{
    val tablist=listOf("推荐","演唱会","音乐节","话剧","脱口秀","展览","亲子")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        tablist.forEach { title->
            Text(
                text=title,
                fontSize = 16.sp,
                color = if(title=="推荐")Color(0xFFFF5722) else Color.Gray
            )

        }
    }
}


@Composable
fun MyBookAppointModule() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp) // 统一内边距
        ) {
            // 标题行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "我的抢票预约",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF4081)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "今日抢票 一键直达",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 头像 + 右侧内容（关键改动在这里）
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top // 头像顶部对齐，更美观
            ) {
                // 头像
                Image(
                    painter = painterResource(id = R.drawable.dengziqi),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                // 右侧整个区域：用 Box 实现 文字+右下角图标
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 文字内容（演出信息 + 票价）
                    Column {
                        Text(
                            text = "【福州】G.E.M.邓紫棋 I AM GLORIA世界巡回演唱会2.0 -福州站",
                            fontSize = 14.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "¥460 × 1张",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    // ==============================
                    // 图标 放在右下角，不挤压、不同行
                    // ==============================
                    Image(
                        painter = painterResource(id = R.drawable.ready_to_buy_ticket),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomEnd) // 右下角
                            .height(20.dp) // 你可以随便调大小，不会变形
                    )
                }
            }
        }
    }
}



// 分类模块（完全不变）
@Composable
fun CategoryGrid() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    CategoryItemView(categoryList[index])
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
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
            .width(65.dp)
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
        horizontalArrangement = Arrangement.spacedBy(10.dp) //卡片中间间距
        //============左侧：抢票播报站===============

    ) {
        Card(
            modifier = Modifier
                .weight(1f)   //左右平分宽度
                .height(160.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation= CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ){
                //标题行
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "抢票播报站",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF222222)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(21.dp)
                            .background(Color(0xFFfefefe), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center

                    ){
                    Text(
                        text = "张学友广州 >",
                        fontSize = 11.sp,
                        color = Color(0xFFf3a8c2)

                    )
                    }
                }
                    Spacer(modifier = Modifier.height(10.dp))

                    //内容行:头像+演出名
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(R.drawable.dengziqi),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column()
                            {
                                Text(
                                    text = "G.E.M.邓紫棋 I AM GLORIA世界巡回演唱会...",
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    color=Color(0xFF333333)
                                )
                                Text(
                                    text="39.9万人想看",
                                    fontSize = 11.sp,
                                    color=Color(0xFFec7097)
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
                            painter = painterResource(R.drawable.ic_buy),
                            contentDescription = null,
                            modifier = Modifier.width(80.dp)
                                .padding(start = 10.dp),
                            contentScale = ContentScale.Fit // 图片铺满按钮区域
                        )


                    }

            }
        }
        //================右侧：天天低价=============
        Card(
            modifier = Modifier
                .weight(1f)
                .height(160.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation= CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "天天低价",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color=Color(0xFF222222)
                    )
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(21.dp)
                            .background(Color(0xFFfefefe), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center

                    ){
                    Text(
                        text = "领券购票更优惠 >",
                        fontSize = 11.sp,
                        color= Color(0xFF999999)
                    )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                //两个商品横向排列
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //左边商品
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.ic_livere),
                            contentDescription =null,
                            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .padding(top=4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFF4D8F))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ){
                            Text(text="¥99起", color = Color.White, fontSize = 11.sp)
                        }

                    }
                    //右边商品
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.drama_lei_yu),
                            contentDescription =null,
                            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .padding(top=4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFF4D8F))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ){
                            Text(text="¥100起", color = Color.White, fontSize = 11.sp)
                        }

                    }
                }
            }

        }
    }
}

@Composable
fun FunctionCardModule() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 演出日历
        Box(
            modifier = Modifier
                .weight(1f)
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "演出日历", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text(text = "按时间选演出", fontSize = 11.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // 大麦演出榜
        Box(
            modifier = Modifier
                .weight(1f)
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "大麦演出榜", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text(text = "你的观演指南", fontSize = 11.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_top),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // 大麦团购
        Box(
            modifier = Modifier
                .weight(1f)
                .height(70.dp) // 固定高度
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White) // 背景还是白色
                .padding(start = 6.dp, end = 0.dp)
                .border(
                    width = 2.dp,
            color = Color.Red,
            shape = RoundedCornerShape(10.dp) // 和背景一样的圆角
        ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                // 左侧文字
                Column {
                    Text(
                        text = "大麦团购",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF4081)
                    )
                    Text(
                        text = "超低价随时退",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // 把图片推到最右边

                Image(
                    painter = painterResource(id = R.drawable.ic_right_row), // 你的图片
                    contentDescription = null,
                    modifier = Modifier
                        .height(90.dp),
                    contentScale = ContentScale.Fit // 保持图片比例
                )
            }
        }
    }
}


val bannerList = listOf(
    R.drawable.banner1,
    R.drawable.banner2,
    R.drawable.banner4
)

@Composable
fun BannerCarousel() {

    val pagerState = rememberPagerState(
        pageCount = { bannerList.size }
    )

    // 自动轮播
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % bannerList.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp) // 左右边距，更美观
    ) {
        // 官方原生轮播图
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        ) { page ->
            Image(
                painter = painterResource(id = bannerList[page]),
                contentDescription = "轮播图",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

    }
}


data class MustSeeItem(
    val imageRes:Int,
    val desc: String
)


//单个演出卡片
@Composable
fun MustSeeItemCard(item: MustSeeItem)
{

    Column(
        modifier = Modifier.width(100.dp)
    ) {
      Image(
          painter=painterResource(item.imageRes),
          contentDescription = null,
          modifier = Modifier
              .size((140.dp))
              .clip(RoundedCornerShape(12.dp))
      )
        Spacer(modifier = Modifier.height(6.dp))

        //描述
        Text(
            text = item.desc,
            fontSize = 12.sp,
            color= Color.Gray,
            maxLines = 2
        )
    }

}


@Composable
fun MustSeePerformanceModule(){

    //存放演出数据
    val MustSeeLIst=listOf(
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner1,
            desc = "2026苏见信「尽兴而活」巡回演唱会﹣福州站"
        ),
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner2,
            desc = "杨千嬅 Live MY LIVE 1.0 世界巡回演唱会﹣厦门站"
        ),
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner3,
            desc = "春日海海2026鹿先森乐队工人体育馆演唱会"
        ),
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner4,
            desc = "致敬大师《天空 之城》《千与千寻》《龙猫 》宫崎骏&久石让经典动漫.."
        ),
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner5,
            desc = "7月23日婺剧《三打白骨精》"
        ),
        MustSeeItem(
            imageRes = R.drawable.lazy_row_banner6,
            desc = "Zkaaai-2026巡演Livehouse"
        )
    )


    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = "必看演出",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )



        //横向滑动列表
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MustSeeLIst){  item ->
                MustSeeItemCard(item)
            }
        }

    }
}

//瀑布流模块
// =====================演出数据实体====================
data class PerformanceInfo(
    val imgRes:Int,
    val title:String,
    val desc:String,
    val price:String,
    val cardHeight:Int
)


// ===================== 瀑布流单个条目布局 =====================
@Composable
fun PerformanceWaterfallItem(item: PerformanceInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        // 演出图片，高度不同形成错落瀑布流
        Image(
            painter = painterResource(item.imgRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(item.cardHeight.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
            Text(
                text = item.desc,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = item.price,
                fontSize = 13.sp,
                color = Color(0xFFFF4081),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}
//==========瀑布流主流表====================
@Composable
fun PerformanceWaterFallList(){
    val PerformanceList=listOf(
        PerformanceInfo(
            imgRes = R.drawable.water_1,
            title = "咸阳．萧敬腾2026 野生 巡回演唱会﹣﹣咸阳站",
            desc = "明天15.38开抢",
            price = "¥180起",
            cardHeight = 180
        ),
        PerformanceInfo(
            imgRes =R.drawable.water_2,
            title = "金华． 【HI,LIVE】《我们的爱》艺灵音乐．遇见live流行经典金曲演唱会﹣义乌站",
            desc = "7月18日开演",
            price = "¥88起",
            cardHeight = 220
        ),
        PerformanceInfo(
            imgRes = R.drawable.water_3,
            title = "南昌．G.E.M．邓紫棋I AM GLORIA世界巡回演唱会2.0﹣南昌站",
            desc = "8月8日开演",
            price = "380起",
            cardHeight = 190
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
    ){
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            //列之间的距离
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            //行之间的距离
            verticalItemSpacing =12.dp
        ) {
            items(PerformanceList){item ->
                PerformanceWaterfallItem(item)

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}