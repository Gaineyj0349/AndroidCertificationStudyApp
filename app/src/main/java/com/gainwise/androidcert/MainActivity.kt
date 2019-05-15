package com.gainwise.androidcert

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import com.gainwise.androidcert.AndroidCore.AndroidCoreMain
import com.gainwise.androidcert.DataManagement.DataManagementMain
import com.gainwise.androidcert.Debugging.DebuggingMain
import com.gainwise.androidcert.Testing.TestingMain
import com.gainwise.androidcert.UserInterface.UserInterfaceMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val categories = arrayOf("Android Core", "Data Management", "Debugging", "Testing", "User Interface")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,categories)

        main_activity_listview.adapter = adapter

        // ListView Item Click Listener
        main_activity_listview.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

               val selection = main_activity_listview.getItemAtPosition(p2) as String

                when(selection){
                    "Android Core"->{
                        startActivity(Intent(this@MainActivity, AndroidCoreMain::class.java))
                    }
                    "Data Management"->{
                        startActivity(Intent(this@MainActivity, DataManagementMain::class.java))
                    }
                    "Debugging"->{
                        startActivity(Intent(this@MainActivity, DebuggingMain::class.java))
                    }
                    "Testing"->{
                        startActivity(Intent(this@MainActivity, TestingMain::class.java))
                    }
                    "User Interface"->{
                        startActivity(Intent(this@MainActivity, UserInterfaceMain::class.java))
                    }
                }
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.githubLink -> {
                val url = "https://github.com/Gaineyj0349/AndroidCertificationStudyApp"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
