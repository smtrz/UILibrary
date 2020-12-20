package com.tahir.sampleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tahir.validateuser_lib.activities.UserValidationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_launch.setOnClickListener(this)
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
}