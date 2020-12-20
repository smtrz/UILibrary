package com.tahir.validateuser_lib.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Mehthod to return context
 *
 * This class has no useful logic just returns back the context .
 *
 * @param c Context.
 */
@Module
class ContextModule(private val c: Context) {


    @Provides
    @Singleton
    fun provideContext(): Context {

        return c
    }
}
