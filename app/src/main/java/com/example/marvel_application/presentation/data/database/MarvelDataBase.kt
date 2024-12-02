package com.example.marvel_application.presentation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvel_application.presentation.data.database.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}