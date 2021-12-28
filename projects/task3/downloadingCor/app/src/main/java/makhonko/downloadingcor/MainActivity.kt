package makhonko.downloadingcor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val photoUrlStr =
        "https://i.pinimg.com/564x/4e/42/63/4e4263f144f379611e50910cc249113a.jpg"
    private lateinit var iV: ImageView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.bt)
        iV = findViewById(R.id.view)

        val usermodel: MainViewModel by viewModels()

        button.setOnClickListener {
            usermodel.download(URL(photoUrlStr))
            usermodel.bitmap.observe(this) {
                iV.setImageBitmap(it)
            }
        }
    }
}