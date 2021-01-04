package com.tahir.validateuser_lib.helping

import com.tahir.validateuser_lib.interfaces.Validationcallbacks
import com.tahir.validateuser_lib.models.UserInfo
import java.util.*

object Validations {
    lateinit var vb: Validationcallbacks
    fun validateCountryCode(): Array<String> {

        val countryCodes: Array<String> by lazy {

            Locale.getISOCountries()
        }

        return countryCodes
    }


    fun setListener(vc: Validationcallbacks) {

        this.vb = vc

    }

    fun validateUserInputs(
        name: String,
        address1: String,
        address2: String,
        city: String,
        postalCode: String,
        state: String,
        countryCode: String
    ) {
        var errorMsg: StringBuilder = StringBuilder()

        if (name.isEmpty()) {
            errorMsg.append("Name is required\n")

        }
        if (address1.isEmpty()) {
            errorMsg.append("Address 1 is required\n")

        }
        if (address2.isEmpty()) {
            errorMsg.append("Address 2 is required\n")

        }
        if (city.isEmpty()) {
            errorMsg.append("City is required\n")

        }
        if (postalCode.isEmpty()) {
            errorMsg.append("postal code is required\n")

        }
        if (countryCode.isEmpty()) {
            errorMsg.append("Country code is required\n")

        }
        if (!countryCode.isEmpty()) {
            if (validateCountryCode()
                    .find { it.equals(countryCode) } == null
            ) {
                errorMsg.append("Country code is not correct")

            }


        }
        if (errorMsg.toString().isEmpty()) {
            vb.OnValidationSuccess(
                UserInfo(
                    name,
                    address1,
                    address2,
                    city,
                    postalCode,
                    state,
                    countryCode
                )
            )

        } else {
            vb.onValidationError(errorMsg.toString())
        }
    }


}