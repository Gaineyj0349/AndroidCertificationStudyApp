package com.gainwise.androidcert.UserInterface

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gainwise.androidcert.R

class UserInterfaceMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_interface_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
