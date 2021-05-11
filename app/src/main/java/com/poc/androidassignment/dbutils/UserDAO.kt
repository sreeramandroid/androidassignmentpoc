package com.poc.androidassignment.dbutils

import androidx.room.*
import com.poc.androidassignment.model.Row

@Dao
interface UserDAO {
    @Insert
    fun saveUserData(user: List<Row>?)

    @Query("SELECT * FROM userInfo")
    fun getAllUserInfo(): List<Row>?

    @Delete
    fun deleteUser(user: List<Row>?)

    @Update
    fun updateUser(user: Row?)

}