package com.arctouch.codechallenge.ui.home

import android.util.Log
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.di.component.DaggerHomeActivityPresenterInjector
import com.arctouch.codechallenge.di.component.HomeActivityPresenterInjector
import com.arctouch.codechallenge.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.gahfy.feedme.injection.module.ContextModule
import net.gahfy.feedme.injection.module.NetworkModule
import javax.inject.Inject

class HomeActivityPresenter (view: HomeActivityContract.View) : BasePresenter<HomeActivityContract.View>(view), HomeActivityContract.Presenter, HomeAdapter.OnClickMovieCallback {
    @Inject
    lateinit var api: TmdbApi

    protected lateinit var injectorHome: HomeActivityPresenterInjector

    lateinit var viewModel : HomeActivityViewModel

    override fun init() {
        injectorHome = DaggerHomeActivityPresenterInjector
                .builder()
                .baseView(view)
                .contextModule(ContextModule)
                .networkModule(NetworkModule)
                .build()

        injectorHome.inject(this)

        viewModel = HomeActivityViewModel()
        view.setupViews()
    }

    private fun loadUpcomingMovies() {
        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, viewModel.page, TmdbApi.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    val moviesWithGenres = it.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                    }

                    viewModel.moviesWithGenres = moviesWithGenres
                    view.updateView(viewModel)
                    view.hideLoadingView()
                }, {
                    Log.w("APP","erro api:"+it.message)
                    view.showMessage("Connectivity problems. Try again")
                }

                )

    }

    override fun onResume() {
        view.showLoadingView()
        loadUpcomingMovies()
        view.hideLoadingView()
    }


    override fun onClickMovie(movie: Movie) {
        view.openMovieDetails(movie)
    }

}