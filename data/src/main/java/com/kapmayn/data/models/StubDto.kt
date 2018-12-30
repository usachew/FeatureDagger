package com.kapmayn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StubDto(
    @PrimaryKey
    val id: Long,
    val stubFieldFirst: String,
    val stubFieldSecond: String
)