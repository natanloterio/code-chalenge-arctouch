package com.arctouch.codechallenge.ui.home

import com.arctouch.codechallenge.base.BaseView
import com.arctouch.codechallenge.model.Movie

interface HomeActivityContract {

    interface View : BaseView {
        fun showLoadingView()
        fun hideLoadingView()
        fun updateView(moviesWithGenres: HomeActivityViewModel)
        fun setupViews()
        fun openMovieDetails(movie: Movie)
        fun showMessage(s: String)
    }

     interface Presenter

}