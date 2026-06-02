package com.example.myapplication_damai

//导入对应的activity的drawable

sealed class NavRoute(
    val route: String,
    val title: String,
    val iconNormal: Int,
    val iconSelect: Int
) {
    object Home: NavRoute("home", "首页", R.drawable.ic_home, R.drawable.ic_home_sel)
    object Live: NavRoute("live", "演出", R.drawable.ic_live, R.drawable.ic_live_sel)
    object Vip: NavRoute("vip", "会员", R.drawable.ic_search, R.drawable.ic_vip_sel)
    object Ticket: NavRoute("ticket", "票务", R.drawable.ic_shoppingcard, R.drawable.ic_ticket_sel)
    object Mine: NavRoute("mine", "我的", R.drawable.ic_mine, R.drawable.ic_mine_sel)
}


