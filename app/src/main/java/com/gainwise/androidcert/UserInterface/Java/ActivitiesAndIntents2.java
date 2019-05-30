package com.gainwise.androidcert.UserInterface.Java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.gainwise.androidcert.R;

public class ActivitiesAndIntents2 extends AppCompatActivity {

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY =
            "com.gainwise.androidcert.KEYREPLY";

    // EditText view for the message
    private EditText mMessageEditText;
    // TextView for the incoming text
    private TextView mReplyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_and_intents2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.user_interface_act_intent2_et);
        mReplyTextView = findViewById(R.id.user_interface_act_intent2_tv1);

        //get message from intent
        String passedMessage = getIntent().getStringExtra(com.gainwise.androidcert.UserInterface.Java.ActivitiesAndIntents1.EXTRA_MESSAGE);

        mReplyTextView.setText(passedMessage);

    }

    void finishAct(View v){
        // Get the reply message from the edit text.
        String reply = mMessageEditText.getText().toString();

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        //calling setResult to ok lets activity know it was successful
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activities_and_intents, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_activiti_intent_training: {
                String url = "https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/index.html?index=..%2F..android-training#0";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            case R.id.menu_activity_docs: {
                String url = "https://developer.android.com/reference/android/app/Activity";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            case R.id.menu_intent_docs: {
                String url = "https://developer.android.com/reference/android/content/Intent?hl=en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }




}
