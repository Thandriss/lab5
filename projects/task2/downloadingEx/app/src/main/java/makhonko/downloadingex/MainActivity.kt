package makhonko.downloadingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.util.Log
import androidx.activity.viewModels
import makhonko.downloadingex.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var iV: ImageView
    lateinit var button: Button
    private val photoUrlStr = "https://i.pinimg.com/564x/4e/42/63/4e4263f144f379611e50910cc249113a.jpg"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val mainViewModel: MainViewModel by viewModels()
        button = findViewById(R.id.bt)
        iV = findViewById(R.id.view)
        button.setOnClickListener {
            mainViewModel.download(URL(photoUrlStr))
        }
        mainViewModel.bitmap.observe(this) {
            iV.setImageBitmap(it)
        }
        Log.i("Download", "is end")
    }

}