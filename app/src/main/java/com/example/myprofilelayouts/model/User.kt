package com.example.myprofilelayouts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val photo: String,
    val name: String,
    val profession: String,
    val address: String,
)