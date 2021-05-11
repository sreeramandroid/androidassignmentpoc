package com.poc.androidassignment.repository

import android.content.Context
import com.poc.androidassignment.dbutils.AppDB
import com.poc.androidassignment.model.Row


class DatabaseRepository {

 fun Context.getUserDao() =  AppDB.getAppDatabase(applicationContext)?.userDao()

 fun addData(context: Context, user: List<Row>) {
  val userDao = context.getUserDao()
  userDao?.deleteUser(user)
  userDao?.saveUserData(user)
 }

 fun deleteDbList(context: Context) {
  val userDao = context.getUserDao()
  val list = userDao?.getAllUserInfo()
  userDao?.deleteUser(list)
 }


 fun fetchUsersFromDb(context: Context): List<Row>? {
  val userDao = context.getUserDao()
  return userDao?.getAllUserInfo()

 }

}
 /**
  * Adding data to db
  *
  ****/


