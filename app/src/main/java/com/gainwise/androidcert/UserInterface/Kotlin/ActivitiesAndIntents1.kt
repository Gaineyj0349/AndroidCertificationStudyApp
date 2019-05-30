package com.gainwise.androidcert.UserInterface.Kotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.gainwise.androidcert.R

class ActivitiesAndIntents1 : AppCompatActivity() {


    // EditText view for the message
    private var mMessageEditText: EditText? = null
    // TextView for the reply header
    private var mReplyTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_and_intents1)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.user_interface_act_intent1_et)
        mReplyTextView = findViewById(R.id.user_interface_act_intent1_tv1)

    }

     fun startAct2(v: View) {
        val intent = Intent(this, com.gainwise.androidcert.UserInterface.Kotlin.ActivitiesAndIntents2::class.java)
        val message = mMessageEditText!!.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == Activity.RESULT_OK) {
                val reply = data!!.getStringExtra(ActivitiesAndIntents2.EXTRA_REPLY)

                // Set the reply and make it visible.
                mReplyTextView!!.text = reply
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activities_and_intents, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.menu_activiti_intent_training -> {
                val url =
                    "https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/index.html?index=..%2F..android-training#0"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }
            R.id.menu_activity_docs -> {
                val url = "https://developer.android.com/reference/android/app/Activity"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }
            R.id.menu_intent_docs -> {
                val url = "https://developer.android.com/reference/android/content/Intent?hl=en"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {

        // Unique tag for the intent reply
        val TEXT_REQUEST = 1
        // Unique tag required for the intent extra
        val EXTRA_MESSAGE = "com.gainwise.androidcert.KEYEXAMPLE"
    }


}
