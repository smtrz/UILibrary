package com.tahir.validateuser_lib.configs

import android.app.Application
import com.tahir.validateuser_lib.components.AppLevelComponent
import com.tahir.validateuser_lib.components.DaggerAppLevelComponent
import com.tahir.validateuser_lib.constants.AppConstant
import com.tahir.validateuser_lib.modules.ContextModule
import com.tahir.validateuser_lib.modules.DbRepoModule
import com.tahir.validateuser_lib.modules.NetModule


open class App : Application() {
    lateinit var appLevelComponent: AppLevelComponent


    override fun onCreate() {
        super.onCreate()
        app = this
        appLevelComponent = DaggerAppLevelComponent.builder()
            .contextModule(ContextModule(this))
            .dbRepoModule(DbRepoModule())
            .netModule(NetModule(AppConstant.BaseUrl))
            .build()


    }

    companion object {
        lateinit var app: App
    }


}
