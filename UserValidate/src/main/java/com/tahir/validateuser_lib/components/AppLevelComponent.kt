package com.tahir.validateuser_lib.components

import com.tahir.validateuser_lib.activities.UserValidationActivity
import com.tahir.validateuser_lib.modules.ContextModule
import com.tahir.validateuser_lib.modules.DbRepoModule
import com.tahir.validateuser_lib.modules.NetModule
import com.tahir.validateuser_lib.repository.AppRepository
import com.tahir.validateuser_lib.vm.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, DbRepoModule::class, NetModule::class])
@Singleton
interface AppLevelComponent {
    fun inject(ma: MainActivityViewModel)
    fun inject(dr: UserValidationActivity)
    fun inject(dr: AppRepository)

}
