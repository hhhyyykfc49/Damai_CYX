package com.example.myapplication_damai

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController



//@Composable
//fun BottomNavBar(navController: NavHostController) {
//    val items = listOf(
//        NavRoute.Home,
//        NavRoute.Live,
//        NavRoute.Vip,
//        NavRoute.Ticket,
//        NavRoute.Mine
//    )
//
//    val currentRoute by navController.currentBackStackEntryAsState()
//    val selectedRoute = currentRoute?.destination?.route
//
//    NavigationBar(
//        containerColor = Color.White
//    ) {
//        items.forEach { item ->
//            val selected = selectedRoute == item.route
//
//            NavigationBarItem(
//                selected = selected,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(NavRoute.Home.route)
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                icon = {
//                    Icon(
//                        painter = painterResource(id = item.iconRes),
//                        contentDescription = item.title,
//                        modifier = Modifier.size(24.dp),
//                        tint = if (selected) {
//                            Color(0xFFFF5722)  // 选中：橙色
//                        } else {
//                            Color.Unspecified  // 未选中：不染色 = 原图！
//                        }
//                    )
//                },
//                label = { Text(item.title) },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color(0xFFFF5722),
//                    unselectedIconColor = Color.Unspecified,
//                    selectedTextColor = Color(0xFFFF5722),
//                    unselectedTextColor = Color.Gray
//                )
//            )
//        }
//    }
//}


@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        NavRoute.Home,
        NavRoute.Live,
        NavRoute.Vip,
        NavRoute.Ticket,
        NavRoute.Mine
    )

    val currentRoute by navController.currentBackStackEntryAsState()
    val selectedRoute = currentRoute?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { item ->
            val selected = selectedRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(NavRoute.Home.route)
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(if (selected) item.iconSelect else item.iconNormal),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFFFF5722),
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.Unspecified,
                    unselectedIconColor = Color.Unspecified
                )
            )
        }
    }
}
// 底部导航栏预览
@Preview(name = "底部导航栏", showSystemUi = false, showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController())
}