package com.dehboxturtle.presentation.scenes.base.view

/**
 * Copyright (C) 2020 Salvador Martinez
 * Licensed under the Apache License Version 2.0
 * Interface representing a View that will use to load data.
 */
interface LoadDataView<in ViewModel> {

    fun render(viewModel: ViewModel)

}
