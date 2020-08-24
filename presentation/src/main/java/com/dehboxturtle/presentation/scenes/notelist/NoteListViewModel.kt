package com.dehboxturtle.presentation.scenes.notelist

import com.dehboxturtle.domain.model.Note
import com.dehboxturtle.presentation.scenes.base.view.ContentState
import com.dehboxturtle.presentation.scenes.base.view.LoadingState

data class NoteListViewModel(
    val loadingState: LoadingState = LoadingState.NONE,
    val contentState: ContentState = ContentState.NONE,
    val data: List<Note>? = null,
    val errorMessage: String? = null,
    val snackMessage: String? = null
) {

    companion object {
        fun createLoading() = NoteListViewModel(
            loadingState = LoadingState.LOADING,
            contentState = ContentState.CONTENT
        )

        fun createRetryLoading() =
            NoteListViewModel(loadingState = LoadingState.RETRY, contentState = ContentState.ERROR)

        fun createData(data: List<Note>) =
            NoteListViewModel(contentState = ContentState.CONTENT, data = data)

        fun createError(error: String) =
            NoteListViewModel(contentState = ContentState.ERROR, errorMessage = error)

        fun createSnack(snackMessage: String) =
            NoteListViewModel(contentState = ContentState.CONTENT, snackMessage = snackMessage)
    }

}