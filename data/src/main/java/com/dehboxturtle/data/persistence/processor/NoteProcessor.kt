package com.dehboxturtle.data.persistence.processor

import com.dehboxturtle.data.persistence.dao.NoteDao
import com.dehboxturtle.data.persistence.entity.NoteEntity
import com.dehboxturtle.data.persistence.processor.base.BaseProcessor
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteProcessor
@Inject internal constructor(private val dao: NoteDao) : BaseProcessor<NoteEntity>(dao) {

    fun get(id: Long): Maybe<NoteEntity> =
        Maybe.fromCallable { dao.get(id) }

    fun getAll(userName: String): Single<List<NoteEntity>> =
        Single.fromCallable { dao.getAll(userName) }

}