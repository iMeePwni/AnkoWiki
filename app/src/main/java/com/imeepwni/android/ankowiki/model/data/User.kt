package com.imeepwni.android.ankowiki.model.data

/**
 * Create by guofeng on 2017/7/21.
 */
data class User(var id: Int = -1,
                var name: String,
                var age: Int,
                var sex: String) {
    companion object {
        val TABLE_NAME = "user"
        val ID = "id"
        val NAME = "name"
        val AGE = "age"
        val SEX = "sex"
    }
}