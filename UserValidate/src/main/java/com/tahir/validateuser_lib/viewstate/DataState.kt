package com.tahir.validateuser_lib.viewstate


/**
 *  DataState class.
 * Handles use cases for network calls.
 */
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
