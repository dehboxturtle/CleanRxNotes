package com.dehboxturtle.presentation.extensions

import com.dehboxturtle.presentation.scenes.base.presenter.APresenter
import com.dehboxturtle.presentation.scenes.base.view.LoadDataView

fun <P : APresenter<V, VM>, V : LoadDataView<VM>, VM> P.enableTest(view: V) {
    detach()
    testMode = true
    attach(view)
}