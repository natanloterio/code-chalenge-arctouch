package com.arctouch.codechallenge.base

import android.content.Context

interface BaseView {

    fun setupViewListeners()
    fun getContext(): Context
}