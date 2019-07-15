package com.arctouch.codechallenge.ui.detail

import com.arctouch.codechallenge.base.BaseView

interface MovieDetailActivityContract {

    interface View : BaseView {
        fun showLoadingView()
        fun hideLoadingView()
        fun updateView(viewModel: MovieDetailActivityViewModel)
        fun setupViews()
    }

     interface Presenter

}