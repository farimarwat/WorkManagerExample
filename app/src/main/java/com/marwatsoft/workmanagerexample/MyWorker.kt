package com.marwatsoft.workmanagerexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(val context: Context, params:WorkerParameters):Worker(context,params) {
    val CHANNEL_ID = "WorkManaderID"
    override fun doWork(): Result {
        Log.e("WorkManager","Worker is running")
        createNotificationChannel(context)
        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setContentTitle("Testing")
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(pendingIntent)
            .build()
        with(NotificationManagerCompat.from(context)){
            notify(20,notification)
        }
        return Result.success()
    }
    fun createNotificationChannel(context:Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(context,NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}