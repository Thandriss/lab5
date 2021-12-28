package makhonko.downloadingcor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()
    fun download(url: URL) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = url.openConnection().getInputStream()
            val mIcon_val = BitmapFactory.decodeStream(data)
            withContext(Dispatchers.Main){
                bitmap.postValue(mIcon_val)
            }
        }
    }
}

