package com.example.uas_anmp_160420019.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uas_anmp_160420019.model.User
import com.example.uas_anmp_160420019.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope
{
    val userLD = MutableLiveData<User>()
    private val job = Job()

    fun login(username:String,password:String){
        launch {
            val db = buildDb(
                getApplication()
            )
            userLD.postValue(db.hobbyDao().login(username,password))
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}