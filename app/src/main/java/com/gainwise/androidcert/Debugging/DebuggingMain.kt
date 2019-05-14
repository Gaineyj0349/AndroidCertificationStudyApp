package com.gainwise.androidcert.Debugging

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gainwise.androidcert.R

class DebuggingMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debugging_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
