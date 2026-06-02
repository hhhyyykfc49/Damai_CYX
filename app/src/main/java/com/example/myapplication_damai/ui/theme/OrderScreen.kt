package com.example.myapplication_damai.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication_damai.MineScreen
import com.example.myapplication_damai.R
import kotlinx.coroutines.launch

//一级顶部标签
val topTabList = listOf("演出/门票", "团购券", "电影", "小食", "商城")
//二级筛选标签
val subTabList = listOf("全部", "待付款", "待收货", "待评价")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController) {
    val pageState = rememberPagerState(pageCount = { topTabList.size })
    var selectSubIndex by remember { mutableIntStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("我的订单", fontSize = 20.sp) },
            navigationIcon = {
                IconButton (onClick = {navController.popBackStack()}){
                Icon(painterResource(R.drawable.left_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp))
                }
            }


            )
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F7FA))
        ) {
            //1.顶部横向一级Tab
            ScrollableTabRow(
                selectedTabIndex = pageState.currentPage,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pageState.currentPage]),
                        color = Color(0xFFFFAA00),
                        height = 3.dp
                    )
                }
            ) {
                val scope = rememberCoroutineScope()
                topTabList.forEachIndexed { index, title ->
                    Tab(
                        selected = index == pageState.currentPage,
                        onClick = {
                            scope.launch {
                               pageState .animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                            fontSize = 17.sp,
                            color = if (index == pageState.currentPage) Color.Black else Color.Gray
                        )
                    }
                }
            }

            //2.二级筛选标签行
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                subTabList.forEachIndexed { idx, name ->
                    val isSelect = idx == selectSubIndex
                    Box(
                        modifier = Modifier
                            .clickable { selectSubIndex = idx }
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelect) Color(0xFFFFE8CC) else Color.White)
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(
                            name,
                            fontSize = 15.sp,
                            color = if (isSelect) Color(0xFFfe5204) else Color.DarkGray
                        )
                    }
                }
            }

            //3.页面分页容器
            HorizontalPager(
                state = pageState,
                modifier = Modifier.fillMaxSize()
            ) {
                //全部标签共用空页面
                EmptyOrderView()
            }
        }
    }
}

//空订单页面（中间插画+文字）
@Composable
fun EmptyOrderView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 后续替换为空状态插画
        Box(
            modifier = Modifier
                .size(220.dp)
                .background(Color(0xFFE9EDF3), RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "您暂时没有订单呦~",
            fontSize = 20.sp,
            color = Color(0xFF333333),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(navController = rememberNavController())
}