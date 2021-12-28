package mkhonko.coroutines

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var prefs: SharedPreferences
    private var job: Job? = null
    fun startThread(){
        job = lifecycleScope.launchWhenStarted {      //
            withContext(Dispatchers.Main) {
                while (isActive) {
                    Log.i(TAG, "working")
                    delay(1000)
                    textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed++)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getPreferences(Context.MODE_PRIVATE)
        secondsElapsed = prefs.getInt(
            TAG,
            Context.MODE_PRIVATE
        )
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onStop() {
        super.onStop()
        prefs.edit().putInt(TAG, secondsElapsed)
            .apply()
        job?.cancel("end")
        Log.i(TAG, "onStop")
        Log.i(TAG, "$secondsElapsed")
    }

    override fun onStart() {
        Log.i(TAG, "onStart")
        secondsElapsed = prefs.getInt(TAG, Context.MODE_PRIVATE)//закомментировать если нужно решение для onSaveInstanceState/onRestoreInstanceState
        startThread()
        Log.i(TAG, "onStart")
        super.onStart()
    }

    /*override fun onPause() {
        job?.cancel("end")
        super.onPause()
    }*/
    override fun onResume() {
        Log.i(TAG, "onR")
        super.onResume()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
