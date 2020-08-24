package com.dehboxturtle.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dehboxturtle.data.persistence.dao.base.BaseDao
import com.dehboxturtle.data.persistence.entity.NoteEntity

@Dao
abstract class NoteDao : BaseDao<NoteEntity> {

    /**
     * Select a note by the id
     * @param id The note id
     * @return A [NoteEntity]
     */
    @Query("SELECT * FROM ${NoteEntity.NOTE_TABLE} WHERE ${NoteEntity.NOTE_ID} = :id")
    abstract fun get(id: Long): NoteEntity?

    /**
     * Select all notes by the userName
     * @return A list of [NoteEntity] of all the notes in the table for user
     */
    @Query("SELECT * FROM ${NoteEntity.NOTE_TABLE} WHERE ${NoteEntity.NOTE_USER_NAME} = :userName")
    abstract fun getAll(userName: String): List<NoteEntity>

    @Transaction
    open fun insertAndDeleteInTransaction(newNote: NoteEntity, oldNote: NoteEntity) {
        // Anything inside this method runs in a single transaction.
        insert(newNote)
        delete(oldNote)
    }

}