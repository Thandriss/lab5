package makhonko.downloadingex

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.util.concurrent.ExecutorService

class MainViewModel(application: Application): AndroidViewModel(application) {

    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    private val cont = getApplication<MyApp>()
    private var executor: ExecutorService = cont.downloadThread

    fun download(url: URL) {
        executor.execute {
            val data = url.openConnection().getInputStream()
            val mIcon_val = BitmapFactory.decodeStream(data)
            bitmap.postValue(mIcon_val)
        }
    }
}