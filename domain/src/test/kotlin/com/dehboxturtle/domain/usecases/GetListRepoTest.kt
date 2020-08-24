package com.dehboxturtle.domain.usecases

import com.dehboxturtle.domain.exception.NoConnectedException
import com.dehboxturtle.domain.exception.PersistenceException
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
class GetListRepoTest {

    @Mock
    private lateinit var repository: RepoRepository

    // Properties
    private val userName = "userName"

    // Use Case
    private val useCase by lazy { GetListRepo(repository) }

    @Test
    fun buildUseCase() {
        val repoList = mock<List<Repo>>()

        whenever(repository.isConnected).thenReturn(true)
        whenever(repository.getListRepo(userName)).thenReturn(Single.just(repoList))
        whenever(repository.saveListRepo(repoList)).thenReturn(Completable.complete())
        whenever(repository.getCacheListRepo(userName)).thenReturn(Single.just(repoList))
        whenever(repository.sortListRepo(repoList)).thenReturn(Single.just(repoList))

        useCase.execute(userName).test()
            .assertValueCount(1)
            .assertResult(repoList)
    }

    @Test
    fun buildUseCaseWithoutNetworkConnection() {
        val repoList = mock<List<Repo>>()

        whenever(repository.isConnected).thenReturn(false)
        whenever(repository.getCacheListRepo(userName)).thenReturn(Single.just(repoList))
        whenever(repository.getListRepo(userName)).thenReturn(Single.never())
        whenever(repository.sortListRepo(repoList)).thenReturn(Single.just(repoList))

        useCase.execute(userName).test()
            .assertValueCount(1)
            .assertResult(repoList)
    }

    @Test
    fun buildUseCaseWithoutNetworkConnectionAndDataCache() {
        whenever(repository.isConnected).thenReturn(false)
        whenever(repository.getCacheListRepo(userName)).thenReturn(Single.just(emptyList())) // throw NoConnectedException here
        whenever(repository.getListRepo(userName)).thenReturn(Single.never())
        whenever(repository.sortListRepo(emptyList())).thenReturn(Single.just(emptyList()))

        useCase.execute(userName).test()
            .assertError(NoConnectedException::class.java)
    }

    @Test
    fun buildUseCaseWithPersistenceException() {
        val repoList = mock<List<Repo>>()

        whenever(repository.isConnected).thenReturn(true)
        whenever(repository.getListRepo(userName)).thenReturn(Single.just(repoList))
        whenever(repository.saveListRepo(repoList)).thenReturn(
            Completable.error(PersistenceException)
        )
        whenever(repository.getCacheListRepo(userName)).thenReturn(Single.just(emptyList()))

        useCase.execute(userName).test()
            .assertError(PersistenceException::class.java)
    }

}
