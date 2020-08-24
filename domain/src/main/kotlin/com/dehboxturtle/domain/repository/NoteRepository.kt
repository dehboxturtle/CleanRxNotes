package com.dehboxturtle.domain.repository

import com.dehboxturtle.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface NoteRepository {

    val isConnected: Boolean

    //region LIST NOTE
    fun getListNote(userName: String): Single<List<Note>>

    fun getCacheListNote(userName: String): Single<List<Note>>

    fun sortListNote(list: List<Note>): Single<List<Note>>

    fun saveListNote(noteList: List<Note>): Completable
    //endregion

    //region NOTE
    fun getNote(name: String, userName: String): Single<Note>

    fun getCacheNote(id: Long): Maybe<Note>

    fun saveNote(note: Note): Completable
    //endregion
}