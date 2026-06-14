package com.example.myapplication_damai

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication_damai.data.local.DatabaseProvider
import com.example.myapplication_damai.data.local.PerformanceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SearchResultScreen(
    keyword: String
) {

    val context = LocalContext.current

    val dao = remember {
        DatabaseProvider
            .getDatabase(context)
            .performanceDao()
    }

    var resultList by remember {
        mutableStateOf<List<PerformanceEntity>>(emptyList())
    }

    LaunchedEffect(keyword) {

        resultList =
            dao.searchPerformance(keyword)

    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(resultList) { item ->

            ListItem(
                headlineContent = {
                    Text(item.title)
                },
                supportingContent = {
                    Text("${item.city}  ${item.price}")
                }
            )
        }
    }
}