package com.example.uas_anmp_160420019.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.uas_anmp_160420019.model.HobbyDatabase

val DB_NAME = "hobbydb"

fun buildDb(context: Context): HobbyDatabase {
    val db = Room.databaseBuilder(context.applicationContext,
        HobbyDatabase::class.java, DB_NAME)
        .createFromAsset("database/hobby.db")
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `news` (`id` INTEGER NOT NULL, `judul` TEXT NOT NULL, `deskripsi` TEXT NOT NULL, `category` TEXT NOT NULL, `pengarang` INTEGER NOT NULL, PRIMARY KEY(`id`))"
        )
    }
}

