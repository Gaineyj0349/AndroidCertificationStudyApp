package com.gainwise.androidcert.AndroidCore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_android_core_main.*


class AndroidCoreMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.gainwise.androidcert.R.layout.activity_android_core_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val subCategories = arrayOf("Toasts and SnackBars", "Localizing your app", "Application Fundamentals",
            "Notifications", "Job Scheduler")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,subCategories)

        android_core_main_listview_java.adapter = adapter
        android_core_main_listview_kotlin.adapter = adapter

        // Kotlin ListView Item Click Listener
        android_core_main_listview_kotlin.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selection = android_core_main_listview_kotlin.getItemAtPosition(p2) as String

                when(selection){
                    "Toasts and SnackBars"->{
                        startActivity(Intent(this@AndroidCoreMain, com.gainwise.androidcert.AndroidCore.Kotlin.ToastAndSnackBar::class.java))
                    }
                    "Localizing your app"->{
                        showDialog1()
                    }
                    "Application Fundamentals"->{
                        showDialog2()
                    }
                    "Notifications"->{
                    }
                    "Job Scheduler"->{
                    }
                }
            }
        })


        // Java ListView Item Click Listener
        android_core_main_listview_java.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selection = android_core_main_listview_java.getItemAtPosition(p2) as String

                when(selection){
                    "Toasts and SnackBars"->{
                        startActivity(Intent(this@AndroidCoreMain,com.gainwise.androidcert.AndroidCore.Java.ToastAndSnackBar::class.java))
                    }
                    "Localizing your app"->{
                        showDialog1()
                    }
                    "Application Fundamentals"->{
                        showDialog2()
                    }
                    "Notifications"->{
                        startActivity(Intent(this@AndroidCoreMain,com.gainwise.androidcert.AndroidCore.Java.NotificationsAct::class.java))
                    }
                    "Job Scheduler"->{
                    }
                }
            }
        })

    }

    fun showDialog1(){
        val builder = AlertDialog.Builder(this@AndroidCoreMain)
        builder.setMessage("The docs online are outstanding for this. Click Yes to launch the browser with the developer docs on the subject. Click No to dismiss. " +
                "\n\nSimple instruction example:\nRight click strings.xml\nClick Translations Editor\nClick the world icon to add a new supported locale.")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes") { dialog, id ->
            val url = "https://developer.android.com/guide/topics/resources/localization"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

            dialog.cancel()
        }
        builder.setNegativeButton("No") { dialog, id ->

            dialog.cancel()
        }

        val alert = builder.create()
        alert.show()
    }

    fun showDialog2(){
        val builder = AlertDialog.Builder(this@AndroidCoreMain)
        builder.setMessage("The docs online detail this with great precision. It's an overview of the fundamentals in the android environment. Click Yes to launch the browser with the developer docs on the subject. Click No to dismiss.")
        builder.setCancelable(true)

        builder.setPositiveButton("Yes") { dialog, id ->
            val url = "https://developer.android.com/guide/components/fundamentals"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

            dialog.cancel()
        }
        builder.setNegativeButton("No") { dialog, id ->

            dialog.cancel()
        }

        val alert = builder.create()
        alert.show()
    }


}
