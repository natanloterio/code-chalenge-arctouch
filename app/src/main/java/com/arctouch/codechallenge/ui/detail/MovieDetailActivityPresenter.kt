package com.arctouch.codechallenge.ui.detail

import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.di.component.DaggerDetailActivityPresenterInjector
import com.arctouch.codechallenge.di.component.DetailActivityPresenterInjector
import com.arctouch.codechallenge.model.Movie
import net.gahfy.feedme.injection.module.ContextModule
import net.gahfy.feedme.injection.module.NetworkModule
import javax.inject.Inject

class MovieDetailActivityPresenter(_view: MovieDetailActivityContract.View) : BasePresenter<MovieDetailActivityContract.View>(_view), MovieDetailActivityContract.Presenter {

    protected lateinit var injectorDetailActivity: DetailActivityPresenterInjector
    protected lateinit var movie: Movie

    @Inject
    lateinit var api: TmdbApi
    lateinit var viewModel : MovieDetailActivityViewModel

    constructor(view: MovieDetailActivityContract.View, movie: Movie) : this(view) {
        this.movie = movie
    }


    override fun onResume() {
        viewModel = MovieDetailActivityViewModel(movie)
        view.setupViews()
        view.updateView(viewModel)
    }
    override fun init() {
        injectorDetailActivity = DaggerDetailActivityPresenterInjector.builder()
                .baseView(view)
                .contextModule(ContextModule)
                .networkModule(NetworkModule)
                .build()

        injectorDetailActivity.inject(this)



    }




}