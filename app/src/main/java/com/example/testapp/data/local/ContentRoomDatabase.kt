package com.example.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ContentEntity::class], version = 3, exportSchema = false)
abstract class ContentRoomDatabase : RoomDatabase() {
    abstract fun contentDao() : ContentDao
}
