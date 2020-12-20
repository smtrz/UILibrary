package com.tahir.validateuser_lib.modules


import com.tahir.validateuser_lib.repository.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Repository Module.
 *
 * This class has no useful logic; it returns Repository Object.
 *
 */

@Module
class DbRepoModule {


    @Provides
    @Singleton
    fun getRepository(): AppRepository {

        return AppRepository()

    }


}