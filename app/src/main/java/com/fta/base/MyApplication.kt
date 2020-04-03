package com.fta.base

import android.app.Application
import android.content.ContextWrapper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

private lateinit var INSTANCE: Application

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}

object AppContext : ContextWrapper(INSTANCE)
