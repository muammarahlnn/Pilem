package com.ardnn.pilem

import android.app.Application
import com.ardnn.pilem.core.di.CoreComponent
import com.ardnn.pilem.core.di.DaggerCoreComponent
import com.ardnn.pilem.di.AppComponent
import com.ardnn.pilem.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}