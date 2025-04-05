package com.example.myapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Problema(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val descricao: String,
    val gravidade: String
)