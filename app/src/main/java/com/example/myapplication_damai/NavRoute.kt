package com.example.myapplication_damai

//导入对应的activity的drawable

sealed class NavRoute(
    val route: String,
    val iconRes: Int,
    val title: String
) {
    object Home: NavRoute("home", iconRes = R.drawable.ic_home, title = "首页")
    object Live: NavRoute("live", iconRes = R.drawable.ic_live, title = "现场")
    object Vip: NavRoute("vip", iconRes = R.drawable.ic_search, title = "VIP")
    object Ticket: NavRoute("ticket", iconRes = R.drawable.ic_shoppingcard, title = "票夹")
    object Mine: NavRoute("mine", iconRes = R.drawable.ic_mine, title = "我的")
}


