package com.dehboxturtle.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dehboxturtle.data.persistence.entity.NoteEntity.Companion.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
class NoteEntity(
    @PrimaryKey @ColumnInfo(name = NOTE_ID) val id: Long,
    @ColumnInfo(name = NOTE_NAME) val name: String,
    @ColumnInfo(name = NOTE_DESCRIPTION) val description: String,
    @ColumnInfo(name = NOTE_URL) val url: String,
    @ColumnInfo(name = NOTE_IS_FAVORITE) val isFavorite: Boolean,
    @ColumnInfo(name = NOTE_USER_NAME) val userName: String
) {

    companion object {
        // TABLE
        const val NOTE_TABLE = "note"

        // COLUMN
        const val NOTE_ID = "id"
        const val NOTE_NAME = "name"
        const val NOTE_DESCRIPTION = "description"
        const val NOTE_URL = "url"
        const val NOTE_IS_FAVORITE = "is_favorite"
        const val NOTE_USER_NAME = "user_name"
    }

}