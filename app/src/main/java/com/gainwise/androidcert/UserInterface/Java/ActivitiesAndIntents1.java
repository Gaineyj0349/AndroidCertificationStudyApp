package com.gainwise.androidcert.UserInterface.Java;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.gainwise.androidcert.R;

public class ActivitiesAndIntents1 extends AppCompatActivity {

    // Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;
    // Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE
            = "com.gainwise.androidcert.KEYEXAMPLE";


    // EditText view for the message
    private EditText mMessageEditText;
    // TextView for the reply header
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_and_intents1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.user_interface_act_intent1_et);
        mReplyTextView = findViewById(R.id.user_interface_act_intent1_tv1);

    }

    void startAct2(View v){
        Intent intent = new Intent(this, com.gainwise.androidcert.UserInterface.Java.ActivitiesAndIntents2.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(com.gainwise.androidcert.UserInterface.Java.ActivitiesAndIntents2.EXTRA_REPLY);

                // Set the reply and make it visible.
                mReplyTextView.setText(reply);
            }
        }
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
