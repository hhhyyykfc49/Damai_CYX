package com.example.myapplication_damai

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication_damai.data.local.DatabaseProvider
import com.example.myapplication_damai.data.local.SearchHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val dao = remember {
        DatabaseProvider
            .getDatabase(context)
            .searchHistoryDao()
    }

    val scope = rememberCoroutineScope()

    var keyword by remember {
        mutableStateOf("")
    }

    // Room历史记录
    val historyList by dao
        .getAllHistory()
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopAppBar(

            navigationIcon = {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },

            title = {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextField(
                        value = keyword,
                        onValueChange = {
                            keyword = it
                        },
                        placeholder = {
                            Text("搜索演出")
                        },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Button(
                        onClick = {

                            if (keyword.isNotBlank()) {

                                scope.launch(Dispatchers.IO) {

                                  val exist =dao.findByKeyword(keyword)

                                    if(exist==null){
                                        dao.insert(
                                            SearchHistoryEntity(
                                                keyword = keyword
                                            )
                                        )

                                    }

                                }
                                navController.navigate(
                                    "search_result/$keyword")
                            }
                        }
                    ) {
                        Text("搜索")
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
//
//        Text(
//            text = "搜索历史",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "搜索历史",
                style = MaterialTheme.typography.titleMedium
            )

            TextButton(
                onClick = {

                    scope.launch(Dispatchers.IO) {
                        dao.clearAll()
                    }

                }
            ) {
                Text("清空")
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyColumn {

            items(historyList) { item ->

                ListItem(
                   modifier = Modifier.clickable{
                       keyword=item.keyword
                   },
                    headlineContent = {
                        Text(item.keyword)
                    }
                )

                HorizontalDivider()
            }
        }
    }
}