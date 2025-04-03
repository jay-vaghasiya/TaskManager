package com.jay.taskmanager.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jay.taskmanager.ui.theme.TaskManagerTheme
import com.jay.taskmanager.view.screen.Landing
import com.jay.taskmanager.view.screen.LandingScreen
import com.jay.taskmanager.view.screen.Settings
import com.jay.taskmanager.view.screen.SettingsScreen
import com.jay.taskmanager.view.screen.TaskCreation
import com.jay.taskmanager.view.screen.TaskCreationScreen
import com.jay.taskmanager.view.screen.TaskDetail
import com.jay.taskmanager.view.screen.TaskDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TaskManagerTheme {
                NavHost(
                    navController,
                    startDestination = Landing,
                    Modifier.fillMaxSize()
                ) {
                    composable<Landing> {
                        LandingScreen(navController)
                    }
                    composable<TaskDetail> {
                        val args = it.toRoute<TaskDetail>()
                        TaskDetailScreen(args.taskId, navController)
                    }
                    composable<TaskCreation> {
                        TaskCreationScreen(navController)
                    }
                    composable<Settings> {
                        SettingsScreen(navController)
                    }

                }


            }
        }
    }

}


