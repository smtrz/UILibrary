package com.tahir.validateuser_lib.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tahir.validateuser_lib.R
import com.tahir.validateuser_lib.helping.UIHelper
import com.tahir.validateuser_lib.helping.Validations
import com.tahir.validateuser_lib.interfaces.Validationcallbacks
import com.tahir.validateuser_lib.models.UserInfo
import com.tahir.validateuser_lib.viewstate.DataState
import com.tahir.validateuser_lib.viewstate.SubmitStatus
import com.tahir.validateuser_lib.vm.MainActivityViewModel
import com.tahir.validateuser_lib.vm.MyViewModelFactory
import kotlinx.android.synthetic.main.userinfovalidation.*
import okhttp3.ResponseBody

class UserValidationActivity : AppCompatActivity(), View.OnClickListener, Validationcallbacks {
    lateinit var mainactVm: MainActivityViewModel
    var id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userinfovalidation)
        init()
    }

    fun init() {
        mainactVm =
            ViewModelProvider(this, MyViewModelFactory()).get(MainActivityViewModel::class.java)
        submit.setOnClickListener(this)
        id = intent.getStringExtra("id")

        subscribeObservers()
    }

    /**
     * Subscribe to DataState Live data
     */
    private fun subscribeObservers() {
        mainactVm.dataState.observe(this, Observer { dataState ->
            when (dataState) {

                is DataState.Success<ResponseBody> -> {

                    displayProgressBar(false)
                    displaySuccessMsg()
                }
                is DataState.Error -> {

                    displayProgressBar(false)
                    displayError(dataState.exception)
                }
                is DataState.Loading -> {

                    displayProgressBar(true)
                }
            }
        })
    }

    /**
     * Display progressbar
     */
    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.submit -> {

                Validations.validateUserInputs(
                    edit_txtinput_name.text.toString(),
                    edit_txtinput_address_1.text.toString(),
                    edit_txtinput_address_2.text.toString(),
                    edit_txtinput_city.text.toString(),
                    edit_txtinput_postalcode.text.toString(),
                    edit_txtinput_state.text.toString(),
                    edit_txtinput_countrycode.text.toString(), this
                )


            }


        }
    }

    /**
     * This method will be used to show the error messages if any
     */
    private fun displayError(message: String?) {

        var msg: String?
        msg = message
        if (message == null) {

            msg = "unknown error"
        }
        UIHelper.showAlertDialog(msg!!, "Error occured", this)


    }

    /**
     * This method will be used to show the succcess messages if any
     */
    private fun displaySuccessMsg() {

        UIHelper.showAlertDialog(
            "User information Submitted successfully.",
            "Data Submitted",
            this
        )


    }

    /**
     * This method will be invoked when the form validation fails
     */
    override fun onValidationError(error: String) {
        UIHelper.showAlertDialog(error, "Validation error(s)", this)

    }

    /**
     * This method will be invoked when the form validation succeeds
     */
    override fun OnValidationSuccess(userInfo: UserInfo) {

        id.let {
            mainactVm.setStateEvent(SubmitStatus.submitUserData, it!!, userInfo)


        }
    }


}