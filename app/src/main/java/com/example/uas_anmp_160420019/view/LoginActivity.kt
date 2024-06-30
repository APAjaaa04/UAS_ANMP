package com.example.uas_anmp_160420019.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uas_anmp_160420019.R
import com.example.uas_anmp_160420019.databinding.ActivityLoginBinding
import com.example.uas_anmp_160420019.model.User
import com.example.uas_anmp_160420019.util.Global
import com.example.uas_anmp_160420019.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginClick{
    private lateinit var viewModel:LoginViewModel
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        Global.userLogin = ""
        Global.userId = 0
        binding.user = User("", "","", "","")
        binding.buttonListener = this
    }

    override fun onLoginClick(v: View) {
        viewModel.login(binding.user!!.username, binding.user!!.password)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(this, Observer{
            if (it == null){
                Toast.makeText(this, "Username/Password Salah", Toast.LENGTH_SHORT).show()
            }
            else{
                Global.userLogin = it.username
                print(it.username)
                Global.userId = it.id
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}