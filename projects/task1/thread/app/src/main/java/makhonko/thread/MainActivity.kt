package makhonko.thread

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var prefs: SharedPreferences
    private lateinit var backgroundThread: Thread

    private fun createThread(): Thread {
        return Thread {
            try {
                while (true) {
                    Log.i(TAG, "working")
                    Thread.sleep(1000)
                    runOnUiThread {
                        textSecondsElapsed.post {
                            textSecondsElapsed.text =
                                String.format(getString(R.string.seconds), secondsElapsed++)
                        }
                    }
                }
            } catch (e: InterruptedException) {
            }
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
        prefs.edit().putInt(TAG, secondsElapsed).apply()//закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        Log.i(TAG, "onStop")
        Log.i(TAG, "$secondsElapsed")
        backgroundThread.interrupt()
        super.onStop()
    }

    override fun onStart() {
        backgroundThread = createThread()
        backgroundThread.start()
        Log.i(TAG, "onStart")
        super.onStart()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}