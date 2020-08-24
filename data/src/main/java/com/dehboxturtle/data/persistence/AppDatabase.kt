package com.dehboxturtle.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dehboxturtle.data.persistence.dao.NoteDao
import com.dehboxturtle.data.persistence.dao.RepoDao
import com.dehboxturtle.data.persistence.entity.RepoEntity

@Database(entities = [(RepoEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    abstract fun noteDao(): NoteDao

}
