package com.yongky.hobbyapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User
    (var username:String,var password:String, var namaDepan:String, var namaBelakang:String, var email:String){
        @PrimaryKey(autoGenerate = true)
        var id:Int = 0
    }

@Entity
data class News(var judul:String,var deskripsi:String,var category:String,var pengarang:Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}