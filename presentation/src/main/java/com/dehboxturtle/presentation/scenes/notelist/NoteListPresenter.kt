package com.dehboxturtle.presentation.scenes.notelist

import com.dehboxturtle.domain.functions.DelayFunction
import com.dehboxturtle.presentation.exception.ErrorMessageFactory
import com.dehboxturtle.presentation.scenes.base.presenter.APresenter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class NoteListPresenter
@Inject constructor(
    private val getData:,
    private val router: NoteListRouter,
    private val scheduler: Scheduler,
    errorMessageFactory: ErrorMessageFactory
) : APresenter<NoteListView, NoteListViewModel>(errorMessageFactory) {

    override fun attach(view: NoteListView) {
        val loadData = view.intentLoadData().flatMap { loadData(it) }
        val refreshData = view.intentRefreshData().flatMap { refreshData(it) }
        val retryData = view.intentErrorRetry().flatMap { retryData(it) }

        subscribeViewModel(view, loadData, refreshData, retryData)
    }

    //region USE CASES TO VIEW MODEL
    private fun getData(username: String): Observable<NoteListViewModel> =
        getData.execute(username).toObservable()
            .map { NoteListViewModel.createData(it) }

    private fun loadData(username: String): Observable<NoteListViewModel> =
        getData(username)
            .startWith(NoteListViewModel.createLoading())
            .onErrorReturn { onError(it) }

    private fun refreshData(username: String): Observable<NoteListViewModel> =
        getData(username)
            .onErrorReturn { NoteListViewModel.createSnack(getErrorMessage(it)) }

    private fun retryData(username: String): Observable<NoteListViewModel> =
        getData(username)
            .startWith(NoteListViewModel.createRetryLoading())
            .onErrorResumeNext(DelayFunction<NoteListViewModel>(scheduler))
            .onErrorReturn { onError(it) }
    //endregion

    private fun onError(error: Throwable): NoteListViewModel =
        NoteListViewModel.createError(getErrorMessage(error))

}