package com.example.myapplication_damai

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication_damai.ui.theme.OrderScreen

//@Composable
//fun MainNav() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        bottomBar = { BottomNavBar(navController) }
//    ) { padding ->
//        NavHost(
//            navController = navController,
//            startDestination = NavRoute.Home.route,
//            modifier = Modifier.padding(padding)
//        ) {
//            composable(NavRoute.Home.route) { HomeScreen() }
//            composable(NavRoute.Live.route) { LiveScreen() }
//            composable(NavRoute.Vip.route) { VipScreen() }
//            composable(NavRoute.Ticket.route) { TicketScreen() }
//            composable(NavRoute.Mine.route) { MineScreen() }
//        }
//    }
//}
//@Preview(name = "主界面", showSystemUi = true, showBackground = true)
//@Composable
//fun MainNavPreview() {
//    MainNav()
//}



//@Composable
//fun MainNav() {
//    val navController = rememberNavController()
//
//
//
//    NavHost(
//            navController = navController,
//            startDestination = NavRoute.Home.route,
////            modifier = Modifier.padding(top = 40.dp)
//        ) {
//            composable(NavRoute.Home.route) { HomeScreen() }
//            composable(NavRoute.Live.route) { LiveScreen() }
//            composable(NavRoute.Vip.route) { VipScreen() }
//            composable(NavRoute.Ticket.route) { TicketScreen() }
//            composable(NavRoute.Mine.route) { MineScreen() }
//
//    }
//}




@Composable
fun MainNav() {
    val navController = rememberNavController()

    // 垂直布局：内容区 + 底部导航栏
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. 页面内容（占满大部分空间）
        NavHost(
            navController = navController,
            startDestination = NavRoute.Home.route,
            modifier = Modifier.weight(1f) // 关键：让内容占满剩余空间
        ) {
            composable(NavRoute.Home.route) { HomeScreen() }
            composable(NavRoute.Live.route) { LiveScreen() }
            composable(NavRoute.Vip.route) { VipScreen() }
            composable(NavRoute.Ticket.route) { TicketScreen() }
            composable(NavRoute.Mine.route) { MineScreen(navController=navController)}
            composable("order"){ OrderScreen(navController) }
        }

        // 2. 底部导航栏
        BottomNavBar(navController)
    }
}


@Preview(name = "主界面", showSystemUi = true, showBackground = true)
@Composable
fun MainNavPreview() {
    MainNav()
}