package com.example.coroutineworkmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.coroutineworkmanagerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var workManager: WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(baseContext)
        binding.btnStartWork.setOnClickListener {
            val workRequest = OneTimeWorkRequestBuilder<ForegroundWorker>()
                .addTag(Tags.COROUTINE_WORK_TAG)
                .build()
            workManager.enqueue(workRequest)
        }
        binding.btnStopWork.setOnClickListener {
            workManager.cancelAllWorkByTag(Tags.COROUTINE_WORK_TAG)
        }

    }
}