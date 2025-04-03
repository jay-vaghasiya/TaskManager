package com.jay.taskmanager.di

import android.content.Context
import androidx.room.Room
import com.jay.taskmanager.model.DataStoreManager
import com.jay.taskmanager.model.database.TaskDatabase
import com.jay.taskmanager.model.repository.TaskRepository
import com.jay.taskmanager.utils.Constant.DATABASE
import com.jay.taskmanager.viewModel.TaskStateViewModel
import com.jay.taskmanager.viewModel.TaskViewModel
import com.jay.taskmanager.viewModel.UserNameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get<Context>(), TaskDatabase::class.java, DATABASE).build()
    }
    single { get<TaskDatabase>().taskDao() }
}

val repositoryModule = module {
    single { TaskRepository(get()) }
}

val viewModelModule = module {
    viewModel { TaskViewModel(get()) }
    viewModel { TaskStateViewModel() }
    viewModel { UserNameViewModel(get()) }
}
val dataStoreModule = module {
    single { DataStoreManager(androidContext()) }
}

val appModules = listOf(dataStoreModule, databaseModule, repositoryModule, viewModelModule)