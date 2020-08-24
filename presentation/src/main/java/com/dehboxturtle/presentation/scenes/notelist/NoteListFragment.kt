package com.dehboxturtle.presentation.scenes.notelist

import android.os.Bundle
import android.view.View
import com.dehboxturtle.data.helper.TimberWrapper
import com.dehboxturtle.domain.model.Note
import com.dehboxturtle.presentation.R
import com.dehboxturtle.presentation.scenes.base.view.ABaseDataFragment
import com.dehboxturtle.presentation.scenes.base.view.ContentState
import com.dehboxturtle.presentation.scenes.base.view.LoadingState
import com.jakewharton.rxbinding4.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.note_list_fragment.*
import javax.inject.Inject

class NoteListFragment : ABaseDataFragment(R.layout.note_list_fragment), NoteListView {

    companion object {
        fun newInstance(): NoteListFragment = NoteListFragment()
    }

    @Inject
    lateinit var presenter: NoteListPresenter

    private fun getParam() = "dehboxturtle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    private fun initView() {

    }

    //region INTENTS
    override fun intentLoadData(): Observable<String> = Observable.just(getParam())

    override fun intentRefreshData(): Observable<String> = swipeRefreshLayout.refreshes().map { getParam() }

    override fun intentErrorRetry(): Observable<String> = btnErrorRetry.clicks().map { getParam() }
    //endregion

    //region RENDER
    override fun render(viewModel: NoteListViewModel) {
        TimberWrapper.d { "Render: " + viewModel }

        showLoading(viewModel.loadingState == LoadingState.LOADING)
        showRefreshingLoading(swipeRefreshLayout, false)
        showRetryLoading(viewModel.loadingState == LoadingState.RETRY)
        showContent(content, viewModel.contentState == ContentState.CONTENT)
        showError(viewModel.contentState == ContentState.ERROR)

        renderData(viewModel.data)
        renderError(viewModel.errorMessage)
        renderSnack(viewModel.snackMessage)
    }

    private fun renderData(data: List<Note>?) {
        data?.also {
            // TODO
        }
    }
    //endregion

}