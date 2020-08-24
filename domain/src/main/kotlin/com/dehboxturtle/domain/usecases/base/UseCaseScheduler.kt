package com.dehboxturtle.domain.usecases.base

import io.reactivex.rxjava3.core.Scheduler

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 */
data class UseCaseScheduler(val run: Scheduler, val post: Scheduler)
