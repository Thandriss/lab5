package makhonko.downloadingex

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApp: Application() {
    val downloadThread: ExecutorService = Executors.newSingleThreadExecutor()
}