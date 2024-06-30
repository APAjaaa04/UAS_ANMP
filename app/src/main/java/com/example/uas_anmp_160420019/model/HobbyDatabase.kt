package com.yongky.hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.yongky.hobbyapp.util.DB_NAME
import com.yongky.hobbyapp.util.MIGRATION_1_2

@Database(entities = arrayOf(User::class, News::class), version =  2)
abstract class HobbyDatabase: RoomDatabase() {
    abstract fun hobbyDao(): HobbyDao

    companion object {
        @Volatile private var instance: HobbyDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            databaseBuilder(
                context.applicationContext,
                HobbyDatabase::class.java,
                DB_NAME).createFromAsset("database/hobby.db").addMigrations(MIGRATION_1_2).build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}
