package com.tahir.validateuser_lib.modules

import com.google.gson.GsonBuilder
import com.tahir.validateuser_lib.BuildConfig
import com.tahir.validateuser_lib.interfaces.EndpointsInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Network module class.
 *
 * This class has methods that returns objects that are useful for Network call.
 *
 * @param mBaseUrl for providing base url from App Level.
.
 */
@Module
class NetModule
    (internal var mBaseUrl: String) {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY

            return httpLoggingInterceptor
        }


    @Provides
    internal fun getApiInterface(retroFit: Retrofit): EndpointsInterface {
        return retroFit.create(EndpointsInterface::class.java)
    }

    @Provides
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            // disabling this for now in this release as its not much needed here.
            // .certificatePinner(getCertificatePinner(mBaseUrl, AppConstant.cert_publickeys))
            .build()
    }

    var gson = GsonBuilder()
        .setLenient()
        .create()
}
