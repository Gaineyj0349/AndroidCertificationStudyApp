package com.gainwise.androidcert.AndroidCore.Kotlin

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.gainwise.androidcert.R

class NotificationScheduler : AppCompatActivity() {

    private var mScheduler: JobScheduler? = null

    //Switches for setting job options
    private var mDeviceIdleSwitch: Switch? = null
    private var mDeviceChargingSwitch: Switch? = null

    //Override deadline seekbar
    private var mSeekBar: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_scheduler)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mDeviceIdleSwitch = findViewById(R.id.jobschedular_idleSwitch)
        mDeviceChargingSwitch = findViewById(R.id.jobschedular_chargingSwitch)

        mSeekBar = findViewById(R.id.jobschedular_seekBar)
        val seekBarProgress = findViewById<TextView>(R.id.jobschedular_seekBarProgress)

        mSeekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (i > 0) {
                    seekBarProgress.text = "$i s"
                } else {
                    seekBarProgress.text = "Not Set"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }

    fun scheduleJob(v: View) {

        val seekBarInteger = mSeekBar!!.progress
        val seekBarSet = seekBarInteger > 0


        val networkOptions = findViewById<RadioGroup>(R.id.jobschedular_networkOptions)
        val selectedNetworkID = networkOptions.checkedRadioButtonId
        var selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE

        when (selectedNetworkID) {
            R.id.jobschedular_noNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
            R.id.jobschedular_anyNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY
            R.id.jobschedular_wifiNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED
        }

        mScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val serviceName = ComponentName(
            packageName,
            NotificationJobService::class.java.name
        )
        val builder = JobInfo.Builder(JOB_ID, serviceName)

        if (seekBarSet) {
            builder.setOverrideDeadline((seekBarInteger * 1000).toLong())
        }

        builder.setRequiredNetworkType(selectedNetworkOption)
            .setRequiresDeviceIdle(mDeviceIdleSwitch!!.isChecked)
            .setRequiresCharging(mDeviceChargingSwitch!!.isChecked)


        val constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE
                || mDeviceChargingSwitch!!.isChecked || mDeviceIdleSwitch!!.isChecked
                || seekBarSet)

        if (constraintSet) {
            //Schedule the job and notify the user
            val myJobInfo = builder.build()
            mScheduler!!.schedule(myJobInfo)
            Toast.makeText(this, "Job Scheduled, job will run when " + "the constraints are met.", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this, "Please set at least one constraint",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


    fun cancelJobs(v: View) {
        if (mScheduler != null) {
            mScheduler!!.cancelAll()
            mScheduler = null
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.job_schedular_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.jobschedularTraining -> {
                val url =
                    "https://codelabs.developers.google.com/codelabs/android-training-job-scheduler/index.html?index=..%2F..android-training#0"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }
            R.id.jobschedularDocs -> {
                val url = "https://developer.android.com/reference/android/app/job/JobScheduler.html"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val JOB_ID = 0
    }
}
