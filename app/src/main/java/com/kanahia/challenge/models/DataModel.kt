package com.kanahia.challenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataModel(
    @PrimaryKey
    val name: String,
    val price: Int,
    val sold: Boolean,
    val image: String)