package com.tahir.validateuser_lib.interfaces


import com.tahir.validateuser_lib.models.UserInfo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * API Interface for Retrofit
 */
interface EndpointsInterface {

    @POST("/{id}")

    suspend fun postUserData(@Path("id") id: String, @Body body: UserInfo): Response<ResponseBody>


}



