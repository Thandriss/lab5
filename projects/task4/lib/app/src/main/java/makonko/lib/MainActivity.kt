package makonko.lib

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var iV: ImageView
    lateinit var button: Button
    private val photoUrlStr = "https://i.pinimg.com/564x/4e/42/63/4e4263f144f379611e50910cc249113a.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.bt)
        iV = findViewById(R.id.view)
        button.setOnClickListener {
            Picasso.get().load(photoUrlStr).into(iV)
        }
        Log.i("Download", "is end")
    }
}