package com.example.examensegunda

import android.app.Application
import com.example.examensegunda.data.AppDatabase

class ExamApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}