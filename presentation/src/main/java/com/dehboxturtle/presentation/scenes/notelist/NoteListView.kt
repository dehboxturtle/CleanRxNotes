package com.dehboxturtle.presentation.scenes.notelist

import com.dehboxturtle.presentation.scenes.base.view.LoadDataView
import io.reactivex.rxjava3.core.Observable

interface NoteListView : LoadDataView<NoteListViewModel> {

    fun intentLoadData(): Observable<String>

    fun intentRefreshData(): Observable<String>

    fun intentErrorRetry(): Observable<String>

}