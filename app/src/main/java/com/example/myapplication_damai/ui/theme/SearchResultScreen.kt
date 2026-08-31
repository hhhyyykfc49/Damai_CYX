package com.example.myapplication_damai

import androidx.annotation.RestrictTo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication_damai.data.local.DatabaseProvider
import com.example.myapplication_damai.data.local.PerformanceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SearchResultScreen(
    keyword: String
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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

        item {
            Box(
                modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 10.dp).clickable(onClick = {
                        scope.launch(Dispatchers.IO) {
                            dao.clearAll()
                            resultList = dao.searchPerformance(keyword)
                        }
                    }
                )
            )
        }

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