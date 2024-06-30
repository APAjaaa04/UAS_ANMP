package com.example.uas_anmp_160420019.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HobbyDao {
    @Query("SELECT * FROM users WHERE username= :username and password= :password")
    fun login(username:String,password:String): User
    @Query("SELECT * FROM news")
    fun getNews(): List<News>

    @Query("SELECT * FROM news WHERE id= :id")
    fun getDetailNews(id:Int): News

    @Query("SELECT * FROM users WHERE id= :id")
    fun getUserData(id:Int): User

    @Query("UPDATE users SET password=:password WHERE id= :id")
    fun gantiPassword(id:Int,password: String)
}