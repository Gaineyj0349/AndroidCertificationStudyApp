package com.gainwise.androidcert.AndroidCore.Java;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.gainwise.androidcert.R;

public class ToastAndSnackBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_and_snack_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //toast
        Button toastButton = findViewById(R.id.toastButton);
        toastButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(ToastAndSnackBar.this, "Hello Toast World!", Toast.LENGTH_LONG).show();
            }
        });


        //snackbar with action
        Button snackbarButton = findViewById(R.id.snackbarButton);
        snackbarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar
                        .make(view, "Hello SnackBar World", Snackbar.LENGTH_LONG);
                snackbar.setAction("Click Me", new SoundListener());
                snackbar.show();
            }
        });



    }


    //custom listener class for snackbar click
    class SoundListener implements View.OnClickListener {        

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ToastAndSnackBar.this);
            builder.setMessage("Awesome snack time, click yes to close.");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toast_snackbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.toastDocs: {
                String url = "https://developer.android.com/guide/topics/ui/notifiers/toasts";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            case R.id.snackbarDocs: {
                String url = "https://developer.android.com/reference/android/support/design/widget/Snackbar";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }
}
