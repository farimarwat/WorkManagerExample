package com.marwatsoft.workmanagerexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var mContext:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        setupWorker()
    }
    fun setupWorker(){
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = PeriodicWorkRequest.Builder(MyWorker::class.java,20,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(mContext).enqueue(request)

    }
}