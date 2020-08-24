package com.dehboxturtle.presentation.scenes.base

import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.dehboxturtle.presentation.scenes.base.view.LoadDataView

abstract class Robot<VM>(private val view: LoadDataView<VM>? = null) {

    protected fun <T> render(currentClass: T, viewModel: VM, func: T.() -> Unit) =
        apply {
            view?.also {
                runOnUiThread { it.render(viewModel) }
                currentClass.func()
            }
        }

}