package com.example.uas_anmp_160420019.view

import android.view.View

interface LoginClick{
    fun onLoginClick(v: View)
}

interface DetailClick{
    fun onDetailClick(v: View)
}

interface ProfileInterface{
    fun onLogoutClick(v: View)
    fun onGantiPasswordClick(v:View)
}

interface DetailNewsInterface{
    fun onKembaliClick(v: View)
}

interface GantiPasswordInterface{
    fun onGantiClick(v: View)
}