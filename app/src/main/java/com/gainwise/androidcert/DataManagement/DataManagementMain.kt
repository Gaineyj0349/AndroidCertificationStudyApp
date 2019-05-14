package com.gainwise.androidcert.DataManagement

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gainwise.androidcert.R

class DataManagementMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_management_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
