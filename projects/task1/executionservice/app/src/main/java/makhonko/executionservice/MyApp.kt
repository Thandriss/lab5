package makhonko.executionservice

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApp : Application() {
    val execP: ExecutorService = Executors.newSingleThreadExecutor()
}