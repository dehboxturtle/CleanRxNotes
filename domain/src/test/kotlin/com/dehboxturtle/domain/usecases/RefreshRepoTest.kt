package com.dehboxturtle.domain.usecases

import com.dehboxturtle.domain.exception.NoConnectedException
import com.dehboxturtle.domain.model.Repo
import com.dehboxturtle.domain.repository.RepoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RefreshRepoTest {

    @Mock
    private lateinit var repository: RepoRepository

    // Use Case
    private val useCase by lazy { RefreshRepo(repository) }

    @Test
    fun buildUseCase() {
        val repoId = 1L
        val repoName = "repoName"
        val userName = "userName"
        val repo = Repo(
            repoId,
            repoName,
            "description",
            "http://myrepo.com",
            false,
            userName
        )

        whenever(repository.isConnected).thenReturn(true)
        whenever(repository.getRepo(repoName, userName)).thenReturn(Single.just(repo))
        whenever(repository.saveRepo(repo)).thenReturn(Completable.complete())

        useCase.execute(RefreshRepo.Param(repoId, repoName, userName)).test()
            .assertValueCount(1)
            .assertResult(repo)
    }

    @Test
    fun buildUseCaseWithoutNetworkConnection() {
        whenever(repository.isConnected).thenReturn(false)

        useCase.execute(mock()).test()
            .assertError(NoConnectedException::class.java)
    }

}
