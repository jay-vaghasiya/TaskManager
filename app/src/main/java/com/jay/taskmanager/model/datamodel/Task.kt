package com.jay.taskmanager.model.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String?,
    var priority: Priority,
    var dueDate: String,
    var isCompleted: Boolean = false
)

