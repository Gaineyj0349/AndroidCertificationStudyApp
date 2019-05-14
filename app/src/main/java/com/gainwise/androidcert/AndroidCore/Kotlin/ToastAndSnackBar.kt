package com.gainwise.androidcert.AndroidCore.Kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gainwise.androidcert.R

class ToastAndSnackBar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_and_snack_bar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //toast
        val toastButton = findViewById<Button>(R.id.toastButton)
        toastButton.setOnClickListener {
            Toast.makeText(this@ToastAndSnackBar, "Hello Toast World!", Toast.LENGTH_LONG).show()
        }


        //snackbar with action
        val snackbarButton = findViewById<Button>(R.id.snackbarButton)
        snackbarButton.setOnClickListener { view ->
            val snackbar = Snackbar
                .make(view, "Hello SnackBar World", Snackbar.LENGTH_LONG)
            snackbar.setAction("Click Me", SoundListener())
            snackbar.show()
        }



    }


    //custom listener class for snackbar click
    internal inner class SoundListener : View.OnClickListener {

        override fun onClick(v: View) {
            val builder = AlertDialog.Builder(this@ToastAndSnackBar)
            builder.setMessage("Awesome snack time, click yes to close.")
            builder.setCancelable(true)

            builder.setPositiveButton(
                "Yes"
            ) { dialog, id -> dialog.cancel() }


            val alert = builder.create()
            alert.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toast_snackbar_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.toastDocs -> {
                val url = "https://developer.android.com/guide/topics/ui/notifiers/toasts"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                true
            }
            R.id.snackbarDocs -> {
                val url = "https://developer.android.com/reference/android/support/design/widget/Snackbar"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
