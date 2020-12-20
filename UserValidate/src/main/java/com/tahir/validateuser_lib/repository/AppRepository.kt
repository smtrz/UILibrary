package com.tahir.validateuser_lib.repository

import com.tahir.validateuser_lib.configs.App
import com.tahir.validateuser_lib.interfaces.EndpointsInterface
import com.tahir.validateuser_lib.models.UserInfo
import com.tahir.validateuser_lib.viewstate.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import javax.inject.Inject


class AppRepository {
    @Inject
    lateinit var retrofit: Retrofit

    /**
     *  DI
     */
    init {
        App.app.appLevelComponent.inject(this@AppRepository)

    }


    /**
     * Post User data after validation to the server.
     *
     * Method to post user data.
     */
    suspend fun postUserData(userInfo: UserInfo, id: String): Flow<DataState<ResponseBody>> = flow {
        var apiResponse: Response

        val endpoints = retrofit.create(EndpointsInterface::class.java)

        emit(DataState.Loading)

        try {

            apiResponse = endpoints.postUserData(id, userInfo).raw()
// checking all the response codes.
            when (apiResponse.code) {
                200 -> {
                    emit(DataState.Success(apiResponse.body!!))

                }
                400 -> {
                    emit(DataState.Error("400 - Bad request"))

                }
                404 -> {
                    emit(DataState.Error("404 - Not found"))

                }
                500 -> {
                    emit(DataState.Error("500 - Internal server error"))

                }
                403 -> {
                    emit(DataState.Error("500 - Forbidden"))

                }
                401 -> {
                    emit(DataState.Error("401 -  Unauthorized"))

                }
                else -> {
                    emit(DataState.Error("Error occured with response code " + apiResponse?.code))
                }

            }


        } catch (e: Exception) {
            emit(DataState.Error(e.message.toString()))
        }

    }
}
