package makhonko.executionservice

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var prefs: SharedPreferences
    private lateinit var backgroundThread: Future<*>

    private fun startCount(myExecutor: ExecutorService) = myExecutor.submit {
        try {
            while (!myExecutor.isShutdown) {
                Log.i(TAG, "working")
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text =
                        String.format(getString(R.string.seconds), secondsElapsed++)
                }
            }
        } catch (e: InterruptedException) {
            Log.i(TAG, "stopped thread")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getPreferences(Context.MODE_PRIVATE)//закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        secondsElapsed = prefs.getInt(TAG, Context.MODE_PRIVATE) //закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        Log.i(TAG, "onCreate")
    }
    override fun onStop() {
        super.onStop()
        prefs.edit().putInt(TAG, secondsElapsed).apply()//закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        backgroundThread.cancel(true)
        Log.i(TAG, "onStop")
        Log.i(TAG, "$secondsElapsed")
    }

    override fun onStart() {
        Log.i(TAG, "onStart")
        secondsElapsed = prefs.getInt(TAG, Context.MODE_PRIVATE)//закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        super.onStart()
        backgroundThread = startCount((application as MyApp).execP)
    }

    /*override fun onPause() {
        backgroundThread.cancel(true)
        super.onPause()
    }*/
    companion object {
        const val TAG = "MainActivity"
    }

    //для решения с onSaveInstanceState/onRestoreInstanceState раскомментировать

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(TAG, secondsElapsed) }
        Log.i(TAG, outState.getInt(TAG).toString())
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i(TAG, savedInstanceState.getInt(TAG).toString())
        secondsElapsed = savedInstanceState.getInt(TAG)
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")
    }
}