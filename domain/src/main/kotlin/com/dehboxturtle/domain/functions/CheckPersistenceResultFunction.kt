package com.dehboxturtle.domain.functions

import com.dehboxturtle.domain.exception.PersistenceException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * [Function] that adds a delay to receiving the onComplete when a [Single] return an error
 */
class CheckPersistenceResultFunction : Function<Boolean, Completable> {

    @Throws(Exception::class)
    override fun apply(result: Boolean): Completable =
        if (result) Completable.complete()
        else Completable.error(PersistenceException)

}
