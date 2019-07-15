package com.arctouch.codechallenge.ui.detail

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.movie_detail.*

class MovieDetailActivity : BaseActivity<MovieDetailActivityPresenter>(), MovieDetailActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)


    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun setupViews() {


    }

    override fun updateView(viewModel: MovieDetailActivityViewModel) {

        val movieImageUrlBuilder = MovieImageUrlBuilder()
        val movie = viewModel.movie
        titleTextView.text = movie.title
        genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
        releaseDateTextView.text = movie.releaseDate

        Glide.with(this)
                .load(viewModel.movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(posterDetailImageView)

    }

    override fun showLoadingView() {
        //progressBar.visibility = View.VISIBLE
    }

    override fun setupViewListeners() {


    }

    override fun hideLoadingView() {
        //progressBar.visibility = View.GONE
    }

    override fun instantiatePresenter(): MovieDetailActivityPresenter {
        var m: Movie = this.getMovieFromIntent() as Movie
        var presenter = MovieDetailActivityPresenter(this,m)
        return presenter
    }

    private fun getMovieFromIntent(): Movie {
        var str = intent.extras.getString("MOVIE")
        var gson = Gson()
        var obj = gson.fromJson(str,Movie::class.java)
        return obj as Movie
    }


}
