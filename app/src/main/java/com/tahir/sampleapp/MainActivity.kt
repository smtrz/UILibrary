package com.tahir.sampleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tahir.validateuser_lib.activities.UserValidationActivity
import com.tahir.validateuser_lib.helping.UIHelper
import com.tahir.validateuser_lib.helping.Validations
import com.tahir.validateuser_lib.interfaces.Validationcallbacks
import com.tahir.validateuser_lib.models.UserInfo

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, Validationcallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_launch.setOnClickListener(this)
        Validations.setListener(this)

    }

    fun startLibrary(apiId: String) {


        val intent = Intent(this, UserValidationActivity::class.java)
        intent.putExtra("id", apiId)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_launch -> {
                startLibrary("uv065guv")

            }

        }

    }

    override fun OnValidationSuccess(userInfo: UserInfo) {
        UIHelper.showShortToastInCenter(this, "Success from app")

    }

    override fun onValidationError(error: String) {
        UIHelper.showShortToastInCenter(this, "Error from app")
    }


}