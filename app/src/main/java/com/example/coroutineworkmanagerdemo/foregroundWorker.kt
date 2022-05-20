package com.example.coroutineworkmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class ForegroundWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            for (i in 1..20){
                setForeground(createForegroundInfo(i))
                delay(1000)
            }
            Result.success()
        }catch (e : Exception){
            Log.i(Tags.LOG_I_TAG,e.toString())
            Result.failure()
        }
    }

    private fun createForegroundInfo(progress : Int):ForegroundInfo{
        return ForegroundInfo(
            1,
            Utils().notificationCreate(
                applicationContext,
                R.drawable.ic_launcher_background,
                "Working",
                "$progress %",
                MainActivity::class.java
            ).build()
        )
    }



}