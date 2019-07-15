package com.arctouch.codechallenge.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arctouch.codechallenge.api.TmdbApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        presenter.init();
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }


    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return this
    }
}