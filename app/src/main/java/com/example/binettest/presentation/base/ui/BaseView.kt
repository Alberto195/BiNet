package com.example.binettest.presentation.base.ui

interface BaseView<ViewState> {
    fun render(viewState: ViewState)
}