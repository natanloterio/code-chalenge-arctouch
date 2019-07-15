package com.arctouch.codechallenge.di.component

import com.arctouch.codechallenge.base.BaseView
import com.arctouch.codechallenge.ui.home.HomeActivityPresenter
import dagger.BindsInstance
import dagger.Component
import net.gahfy.feedme.injection.module.ContextModule
import net.gahfy.feedme.injection.module.NetworkModule
import javax.inject.Singleton



@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface HomeActivityPresenterInjector {

    fun inject(presenter: HomeActivityPresenter)

    @Component.Builder
    interface Builder {
        fun build(): HomeActivityPresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}