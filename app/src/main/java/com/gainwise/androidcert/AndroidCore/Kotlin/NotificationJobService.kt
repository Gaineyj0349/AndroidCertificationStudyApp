package com.gainwise.androidcert.AndroidCore.Kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.gainwise.androidcert.MainActivity
import com.gainwise.androidcert.R


class NotificationJobService : JobService() {

    internal lateinit var mNotifyManager: NotificationManager

    private//the second parameter is the channel id desired to attach to notification
    val notificationBuilder: NotificationCompat.Builder
        get() = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Job Service")
            .setContentText("Your Job is Running")
            .setSmallIcon(R.drawable.ic_job_running)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

    override fun onStartJob(jobParameters: JobParameters): Boolean {

        Log.d("CertDemo", "onStartJob called")

        //Create the notification channel
        createNotificationChannel()

        //Set up the notification content intent to launch the app when clicked
        val contentPendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = notificationBuilder
        builder.setContentIntent(contentPendingIntent)

        mNotifyManager.notify(NOTIFICATION_ID, builder.build())

        return false
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return true
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    fun createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Job Service notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifications from Job Service"

            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {

        // Notification channel ID.
        private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

        private val NOTIFICATION_ID = 9
    }

}
