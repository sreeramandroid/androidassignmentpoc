package com.poc.androidassignment.dbutils

import com.poc.androidassignment.model.Row

class UserDataSource(private val db: UserDAO) {

 fun saveData(users: List<Row>?) = db.saveUserData(users)

 fun getData() = db.getAllUserInfo()
}