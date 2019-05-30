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

class ActivitiesAndIntents2 : AppCompatActivity() {

    // EditText view for the message
    private var mMessageEditText: EditText? = null
    // TextView for the incoming text
    private var mReplyTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_and_intents2)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.user_interface_act_intent2_et)
        mReplyTextView = findViewById(R.id.user_interface_act_intent2_tv1)

        //get message from intent
        val passedMessage = intent.getStringExtra(ActivitiesAndIntents1.EXTRA_MESSAGE)

        mReplyTextView!!.text = passedMessage

    }

    fun finishAct(v: View) {
        // Get the reply message from the edit text.
        val reply = mMessageEditText!!.text.toString()

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        //calling setResult to ok lets activity know it was successful
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
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

        // Unique tag for the intent reply.
        val EXTRA_REPLY = "com.gainwise.androidcert.KEYREPLY"
    }


}
