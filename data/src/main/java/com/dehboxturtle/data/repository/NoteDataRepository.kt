package com.dehboxturtle.data.repository

import com.dehboxturtle.data.di.providers.NetworkChecker
import com.dehboxturtle.data.mapper.NoteMapper
import com.dehboxturtle.data.persistence.processor.NoteProcessor
import com.dehboxturtle.domain.model.Note
import com.dehboxturtle.domain.model.Repo
import com.dehboxturtle.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable

/**
 * [NoteRepository] for retrieving note data.
 */
class NoteDataRepository(
    private val noteMapper: NoteMapper,
    private val noteProcessor: NoteProcessor,
    private val networkChecker: NetworkChecker
) : NoteRepository {
    override val isConnected: Boolean
        get() = networkChecker.isConnected

    //region NOTE LIST
    override fun getListNote(userName: String): Single<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getCacheListNote(userName: String): Single<List<Note>> =
        noteProcessor.getAll(userName)
            .map { noteMapper.transform(it) }

    override fun sortListNote(list: List<Note>): Single<List<Note>> =
        Single.just(list.sortedWith(Comparator { o1, o2 ->
            when {
                o1.id < o2.id -> 1
                o1.id == o2.id -> 0
                else -> -1
            }
        }))

    override fun saveListNote(noteList: List<Note>): Completable =
        noteList.toObservable().flatMapCompletable { saveNote(it) }
    //endregion

    //region NOTE
    override fun getNote(name: String, userName: String): Single<Note> {
        TODO("Not yet implemented")
    }

    override fun getCacheNote(id: Long): Maybe<Note> =
        noteProcessor.get(id)
            .map { noteMapper.transform(it) }

    override fun saveNote(note: Note): Completable =
        noteProcessor.get(note.id)
            .defaultIfEmpty(noteMapper.transformToEntity(note))
            .flatMapCompletable {
                noteProcessor.insert(
                    noteMapper.transformToEntity(
                        note.copy()
                    )
                )
            }
    //endregion
}
