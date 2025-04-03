package com.jay.taskmanager.view.screen

import kotlinx.serialization.Serializable

@Serializable
object Landing

@Serializable
data class TaskDetail(val taskId: Int)

@Serializable
object TaskCreation

@Serializable
object Settings