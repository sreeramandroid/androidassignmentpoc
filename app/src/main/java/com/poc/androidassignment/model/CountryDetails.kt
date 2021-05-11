package com.poc.androidassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CountryDetails(
    val rows: List<Row>,
    val title: String?
)

@Entity(tableName = "userInfo")
data class Row(
    val description: String? = null,
    val imageHref: String? = null,
    val title: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}