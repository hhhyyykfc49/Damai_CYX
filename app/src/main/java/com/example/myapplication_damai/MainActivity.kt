package com.example.myapplication_damai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication_damai.data.local.DatabaseProvider
import com.example.myapplication_damai.data.local.PerformanceEntity
import com.example.myapplication_damai.ui.theme.MyApplication_DaMaiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MainNav()
//
//        }
//    }
//}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPerformanceData()

        setContent {
            MainNav()
        }
    }

    private fun initPerformanceData() {

        CoroutineScope(Dispatchers.IO).launch {

            val dao =
                DatabaseProvider
                    .getDatabase(this@MainActivity)
                    .performanceDao()

            dao.insert(
                PerformanceEntity(
                    title = "Jay嘉年华世界巡回演唱会",
                    city = "福州",
                    price = "¥580"
                )
            )

            dao.insert(
                PerformanceEntity(
                    title = "五月天好好好想见到你演唱会",
                    city = "厦门",
                    price = "¥380"
                )
            )

            dao.insert(
                PerformanceEntity(
                    title = "邓紫棋GLORIA巡演",
                    city = "南昌",
                    price = "¥480"
                )
            )
        }
    }
}


