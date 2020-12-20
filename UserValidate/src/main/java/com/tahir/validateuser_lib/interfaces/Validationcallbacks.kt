package com.tahir.validateuser_lib.interfaces

import com.tahir.validateuser_lib.models.UserInfo

interface Validationcallbacks {
    fun onValidationError(error: String)
    fun OnValidationSuccess(userInfo: UserInfo)

}