package com.arctouch.codechallenge.base

import com.arctouch.codechallenge.ui.home.HomeActivityPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    init {
        init()
    }


    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    abstract fun init()
    abstract fun onResume()


}