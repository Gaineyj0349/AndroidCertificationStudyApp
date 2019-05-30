package com.gainwise.androidcert.UserInterface

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.gainwise.androidcert.R
import kotlinx.android.synthetic.main.activity_user_interface_main.*

class UserInterfaceMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_interface_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val subCategories = arrayOf("Activities and Intents", "Interactive UI", "Constraint Layout",
            "Custom View with Accessibility", "Styles and Themes", "RecyclerView", "Navigation Drawer", "Menus and Pickers", "User Navigation")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,subCategories)

        user_interface_main_listview_java.adapter = adapter
        user_interface_main_listview_kotlin.adapter = adapter

        // Kotlin ListView Item Click Listener
        user_interface_main_listview_kotlin.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selection = user_interface_main_listview_kotlin.getItemAtPosition(p2) as String

                when(selection){
                    "Activities and Intents"->{
                        startActivity(Intent(this@UserInterfaceMain, com.gainwise.androidcert.UserInterface.Kotlin.ActivitiesAndIntents1::class.java))
                    }
                    "Interactive UI"->{
                     
                    }
                    "Constraint Layout"->{

                    }
                    "Custom View with Accessibility"->{

                    }
                    "Styles and Themes"->{

                    }
                    "RecyclerView"->{

                    }
                    "Navigation Drawer"->{

                    }
                    "Menus and Pickers"->{

                    }
                    "User Navigation"->{

                    }
                }
            }
        })


        // Java ListView Item Click Listener
        user_interface_main_listview_java.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selection = user_interface_main_listview_java.getItemAtPosition(p2) as String

                when(selection){
                    "Activities and Intents"->{
                        startActivity(Intent(this@UserInterfaceMain, com.gainwise.androidcert.UserInterface.Java.ActivitiesAndIntents1::class.java))
                    }
                    "Interactive UI"->{

                    }
                    "Constraint Layout"->{

                    }
                    "Custom View with Accessibility"->{

                    }
                    "Styles and Themes"->{

                    }
                    "RecyclerView"->{

                    }
                    "Navigation Drawer"->{

                    }
                    "Menus and Pickers"->{

                    }
                    "User Navigation"->{

                    }
                }
            }
        })

    }
}
