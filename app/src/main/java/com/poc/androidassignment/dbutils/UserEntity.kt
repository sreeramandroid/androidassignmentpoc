package com.poc.androidassignment.dbutils

import androidx.room.PrimaryKey

class UserEntity {
 @PrimaryKey(autoGenerate = true)
 var id: Int = 0
 var title: String = ""
 var description: String = ""
 var imageHref: String = ""
}