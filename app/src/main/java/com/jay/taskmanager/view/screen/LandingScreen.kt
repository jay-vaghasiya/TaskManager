package com.jay.taskmanager.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LowPriority
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jay.taskmanager.utils.Constant
import com.jay.taskmanager.view.component.ActionIcon
import com.jay.taskmanager.view.component.EmptyState
import com.jay.taskmanager.view.component.FilterIcon
import com.jay.taskmanager.view.component.LandingTextButton
import com.jay.taskmanager.view.component.ShimmerEffect
import com.jay.taskmanager.view.component.SwipeableItemWithActions
import com.jay.taskmanager.view.component.TaskCard
import com.jay.taskmanager.viewModel.TaskViewModel
import com.jay.taskmanager.viewModel.UserNameViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavHostController) {
    val viewModel: TaskViewModel = koinViewModel()
    val nameViewMOoel: UserNameViewModel = koinViewModel()
    val name by nameViewMOoel.name.collectAsStateWithLifecycle()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    var filterActive by rememberSaveable { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val filterTask by remember {
        derivedStateOf {
            when (filterActive) {
                "date" -> tasks.sortedByDescending { it.dueDate }
                "priority" -> tasks.sortedByDescending { it.priority.ordinal }
                "alpha" -> tasks.sortedByDescending { it.title }
                else -> tasks
            }
        }
    }

    var showShimmer by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        showShimmer = false
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.matchParentSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            if (name?.isNotBlank() == true) {
                                "Hello $name"
                            } else {
                                "Hello John Doe"
                            }, fontWeight = FontWeight.Medium
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Settings)
                        }) {
                            Icon(Icons.Rounded.Settings, contentDescription = null)
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            bottomBar = {
                BottomAppBar(
                    actions = {

                        FilterIcon(
                            isActive = filterActive == "date",
                            onClick = {
                                filterActive = if (filterActive == "date") null else "date"


                            },
                            imageVector = Icons.Filled.DateRange
                        )

                        FilterIcon(
                            isActive = filterActive == "priority",
                            onClick = {
                                filterActive = if (filterActive == "priority") null else "priority"
                            },
                            imageVector = Icons.Filled.LowPriority
                        )

                        FilterIcon(
                            isActive = filterActive == "alpha",
                            onClick = {
                                filterActive = if (filterActive == "alpha") null else "alpha"
                            },
                            imageVector = Icons.Filled.SortByAlpha
                        )


                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(TaskCreation) {

                                }
                            },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Localized description")
                        }
                    }
                )
            }
        ) { innerPadding ->
            val btList = listOf("My List", "Pending", "Completed")
            val pagerState = rememberPagerState(
                initialPage = btList.indexOf("My List"),
                pageCount = { btList.size })

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    btList.forEachIndexed { index, text ->
                        LandingTextButton(
                            text = text,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            isSelected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> {
                            if (showShimmer) {
                                LazyColumn {
                                    items(6) {
                                        ShimmerEffect(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(144.dp)
                                                .padding(12.dp)
                                                .clip(RoundedCornerShape(24.dp))
                                        )
                                    }

                                }

                            } else
                                if (filterTask.isEmpty()) {

                                    EmptyState(text = Constant.MOTIVATION_TEXT_PENDING)
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        contentPadding = PaddingValues(8.dp)
                                    ) {
                                        items(filterTask, key = { it.id }) { item ->
                                            var isRevealed by remember { mutableStateOf(false) }

                                            SwipeableItemWithActions(
                                                isRevealed = isRevealed,
                                                onExpanded = { isRevealed = true },
                                                onCollapsed = { isRevealed = false },
                                                actions = {

                                                    ActionIcon(
                                                        onClick = {
                                                            viewModel.deleteTask(item)
                                                            scope.launch {
                                                                snackbarHostState.showSnackbar(
                                                                    message = "Deleted successfully",
                                                                    duration = SnackbarDuration.Short
                                                                )
                                                            }
                                                        },
                                                        backgroundColor = Color.Red,
                                                        icon = Icons.Default.Delete,
                                                        modifier = Modifier
                                                            .size(64.dp)
                                                            .padding(4.dp)
                                                            .clip(
                                                                RoundedCornerShape(12.dp)
                                                            )
                                                    )
                                                    ActionIcon(
                                                        onClick = {
                                                            viewModel.updateTaskCompletion(
                                                                taskId = item.id,
                                                                isCompleted = true
                                                            )
                                                            scope.launch {
                                                                snackbarHostState.showSnackbar(
                                                                    message = "Completed successfully",
                                                                    duration = SnackbarDuration.Short
                                                                )
                                                            }
                                                        },
                                                        backgroundColor = Color.Blue,
                                                        icon = Icons.Default.Done,
                                                        modifier = Modifier
                                                            .size(64.dp)
                                                            .padding(4.dp)
                                                            .clip(
                                                                RoundedCornerShape(12.dp)
                                                            )
                                                    )
                                                }
                                            ) {
                                                TaskCard(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp),
                                                    task = item
                                                ) {

                                                    navController.navigate(TaskDetail(it))
                                                }
                                            }
                                        }
                                    }

                                }

                        }

                        1 -> {
                            val filterList = filterTask.filter { !it.isCompleted }
                            if (showShimmer) {
                                LazyColumn {
                                    items(6) {
                                        ShimmerEffect(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(144.dp)
                                                .padding(12.dp)
                                                .clip(RoundedCornerShape(24.dp))
                                        )
                                    }

                                }

                            } else
                                if (filterList.isEmpty()) {
                                    EmptyState(Constant.MOTIVATION_TEXT_PENDING)
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        contentPadding = PaddingValues(8.dp)
                                    ) {
                                        items(filterList, key = { it.id }) { item ->
                                            var isRevealed by remember { mutableStateOf(false) }

                                            SwipeableItemWithActions(
                                                isRevealed = isRevealed,
                                                onExpanded = { isRevealed = true },
                                                onCollapsed = { isRevealed = false },
                                                actions = {

                                                    ActionIcon(
                                                        onClick = {
                                                            viewModel.deleteTask(item)
                                                            scope.launch {
                                                                snackbarHostState.showSnackbar(
                                                                    message = "Deleted successfully",
                                                                    duration = SnackbarDuration.Short
                                                                )
                                                            }
                                                        },
                                                        backgroundColor = Color.Red,
                                                        icon = Icons.Default.Delete,
                                                        modifier = Modifier
                                                            .size(64.dp)
                                                            .padding(4.dp)
                                                            .clip(
                                                                RoundedCornerShape(12.dp)
                                                            )
                                                    )
                                                    ActionIcon(
                                                        onClick = {
                                                            viewModel.updateTaskCompletion(
                                                                taskId = item.id,
                                                                isCompleted = true
                                                            )
                                                            scope.launch {
                                                                snackbarHostState.showSnackbar(
                                                                    message = "Completed successfully",
                                                                    duration = SnackbarDuration.Short
                                                                )
                                                            }
                                                        },
                                                        backgroundColor = Color.Blue,
                                                        icon = Icons.Default.Done,
                                                        modifier = Modifier
                                                            .size(64.dp)
                                                            .padding(4.dp)
                                                            .clip(
                                                                RoundedCornerShape(12.dp)
                                                            )
                                                    )
                                                }
                                            ) {
                                                TaskCard(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp),
                                                    task = item
                                                ) {
                                                    navController.navigate(TaskDetail(it))
                                                }
                                            }
                                        }
                                    }
                                }
                        }

                        else -> {
                            val filterList = filterTask.filter { it.isCompleted }
                            if (showShimmer) {
                                LazyColumn {
                                    items(6) {
                                        ShimmerEffect(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(144.dp)
                                                .padding(12.dp)
                                                .clip(RoundedCornerShape(24.dp))
                                        )
                                    }

                                }

                            } else
                                if (filterList.isEmpty()) {
                                    EmptyState(Constant.MOTIVATION_TEXT_COMPLETED)
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        contentPadding = PaddingValues(8.dp)
                                    ) {
                                        items(filterList, key = { it.id }) { item ->
                                            var isRevealed by remember { mutableStateOf(false) }

                                            SwipeableItemWithActions(
                                                isRevealed = isRevealed,
                                                onExpanded = { isRevealed = true },
                                                onCollapsed = { isRevealed = false },
                                                actions = {

                                                    ActionIcon(
                                                        onClick = {
                                                            viewModel.deleteTask(item)
                                                            scope.launch {
                                                                snackbarHostState.showSnackbar(
                                                                    message = "Deleted successfully",
                                                                    duration = SnackbarDuration.Short
                                                                )
                                                            }
                                                        },
                                                        backgroundColor = Color.Red,
                                                        icon = Icons.Default.Delete,
                                                        modifier = Modifier
                                                            .size(64.dp)
                                                            .padding(4.dp)
                                                            .clip(
                                                                RoundedCornerShape(12.dp)
                                                            )
                                                    )

                                                }
                                            ) {
                                                TaskCard(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp),
                                                    task = item
                                                ) {
                                                    scope.launch {
                                                        snackbarHostState.showSnackbar(
                                                            message = "Completed cannot be edited",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                        }

                    }


                }
            }


        }


    }
}

