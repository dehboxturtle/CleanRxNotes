package com.dehboxturtle.domain.usecases

import com.dehboxturtle.domain.exception.NoConnectedException
import com.dehboxturtle.domain.functions.StatementSingle
import com.dehboxturtle.domain.model.Repo
import com.dehboxturtle.domain.repository.RepoRepository
import com.dehboxturtle.domain.usecases.base.Logger
import com.dehboxturtle.domain.usecases.base.SingleUseCase
import com.dehboxturtle.domain.usecases.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * This class is an implementation of [SingleUseCase] that represents a use case for
 * retrieving a [Repo].
 */
class GetRepo
@Inject internal constructor(
    private val repoRepository: RepoRepository,
    useCaseScheduler: UseCaseScheduler? = null, logger: Logger? = null
) : SingleUseCase<Repo, GetRepo.Param>(useCaseScheduler, logger) {

    override fun build(param: Param): Single<Repo> {
        val cacheSingle = repoRepository.getCacheRepo(param.id)
            .switchIfEmpty(Maybe.error(NoConnectedException))
            .toSingle()

        val netSingle = repoRepository.getRepo(param.name, param.userName)
            .flatMap { repoRepository.saveRepo(it).andThen(cacheSingle) }

        return StatementSingle.ifThen(repoRepository.isConnected, netSingle, cacheSingle)
    }

    data class Param(val id: Long, val name: String, val userName: String)

}
