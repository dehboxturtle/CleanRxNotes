package com.dehboxturtle.presentation.scenes.repo

import com.dehboxturtle.domain.usecases.GetRepo
import com.dehboxturtle.domain.usecases.RefreshRepo
import com.dehboxturtle.presentation.scenes.base.view.LoadDataView
import io.reactivex.rxjava3.core.Observable

interface RepoView : LoadDataView<RepoViewModel> {

    fun intentLoadData(): Observable<GetRepo.Param>

    fun intentRefreshData(): Observable<RefreshRepo.Param>

    fun intentErrorRetry(): Observable<GetRepo.Param>

    fun intentActionLink(): Observable<Unit>

}