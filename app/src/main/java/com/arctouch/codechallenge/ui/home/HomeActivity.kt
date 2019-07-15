package com.arctouch.codechallenge.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.ui.detail.MovieDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity<HomeActivityPresenter>(), HomeActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

    }

    override fun setupViews() {


    }

    override fun updateView(viewModel: HomeActivityViewModel) {
        recyclerView.adapter = HomeAdapter(viewModel.moviesWithGenres, presenter)
    }

    override fun showMessage(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingView() {
        loadingScreen.visibility = View.VISIBLE
    }

    override fun setupViewListeners() {


    }

    override fun hideLoadingView() {
        loadingScreen.visibility = View.GONE
    }

    override fun instantiatePresenter(): HomeActivityPresenter {
        return HomeActivityPresenter(this)
    }

    override fun openMovieDetails(movie: Movie) {
        intent = Intent()
        intent.setClass(applicationContext,MovieDetailActivity::class.java)
        var gson = Gson()
        intent.putExtra("MOVIE",gson.toJson(movie))// workarround para problema da serialização
        // da classe Movie
        startActivity(intent)
    }


}
