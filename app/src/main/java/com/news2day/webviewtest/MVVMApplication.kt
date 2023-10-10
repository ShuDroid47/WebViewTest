package com.news2day.webviewtest

import android.app.Application
import com.news2day.webviewtest.network.ApiService
import com.news2day.webviewtest.network.NetworkConnectionInterceptor
import com.news2day.webviewtest.network.repos.DataRepository
import com.news2day.webviewtest.ui.fatorymodels.CategoryVmFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

class MVVMApplication : Application(), DIAware {
    override val di: DI
        get() = DI.lazy {
            import(androidXModule(this@MVVMApplication))

            bind<NetworkConnectionInterceptor>() with  singleton { NetworkConnectionInterceptor(instance()) }
            bind<ApiService>() with  singleton { ApiService(instance()) }
            bind<DataRepository>() with  singleton { DataRepository(instance()) }
            bind<CategoryVmFactory>() with provider { CategoryVmFactory(instance()) }
        }

}
