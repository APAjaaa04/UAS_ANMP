package com.example.uas_anmp_160420019.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uas_anmp_160420019.model.News
import com.example.uas_anmp_160420019.model.User
import com.example.uas_anmp_160420019.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailNewsViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val newsLD = MutableLiveData<News>()
    val userLD = MutableLiveData<User>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id: Int) {
        launch {
            val db = buildDb(getApplication())
            newsLD.postValue(db.hobbyDao().getDetailNews(id))
        }
    }

    fun fetchPengarang(id:Int){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.hobbyDao().getUserData(id))
        }
    }
}