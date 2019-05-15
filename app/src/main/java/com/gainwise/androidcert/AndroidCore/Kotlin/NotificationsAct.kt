package com.gainwise.androidcert.AndroidCore.Kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.gainwise.androidcert.MainActivity
import kotlinx.android.synthetic.main.activity_notifications.*








class NotificationsAct : AppCompatActivity() {

    /*
    The NotificationManager acts as a sort of creator and publisher of notification channels and
    a handle to launch/modify/cancel a notification to the system.

    The NotificationBuilder is the appearance of the noticiation and actions specific to that notification,
    like an intent.
     */

    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private val NOTIFICATION_ID = 0
    private lateinit var mNotifyManager: NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.gainwise.androidcert.R.layout.activity_notifications)

        createNotificationChannel()

        notifications_notify.setOnClickListener{sendNotification()}

    }

    fun createNotificationChannel() {
        mNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //since sdk26 aka Oreo - notification channels must be implemented
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //construct
            val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID,
                "Demo Notifications", NotificationManager.IMPORTANCE_HIGH)

            //customize the channel
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Cert Demo App");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        //create an action on notification tap
        val notificationIntent = Intent(this, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(this,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        //the second parameter is the channel id desired to attach to notification
        val notifyBuilder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("Hello Notification World!")
            .setSmallIcon(com.gainwise.androidcert.R.drawable.ic_android)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        return notifyBuilder;
    }

    fun sendNotification(){
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.gainwise.androidcert.R.menu.notification_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            com.gainwise.androidcert.R.id.notificationsTraining -> {
                val url =
                    "https://codelabs.developers.google.com/codelabs/android-training-notifications/index.html?index=..%2F..android-training#0"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }
            com.gainwise.androidcert.R.id.notificationDocs -> {
                val url = "https://developer.android.com/guide/topics/ui/notifiers/notifications"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
