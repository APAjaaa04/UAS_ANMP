package com.example.uas_anmp_160420019.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uas_anmp_160420019.R
import com.example.uas_anmp_160420019.databinding.FragmentUserBinding
import com.example.uas_anmp_160420019.util.Global
import com.example.uas_anmp_160420019.viewmodel.UserViewModel

class UserFragment : Fragment(), ProfileInterface{


    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater,R.layout.fragment_user, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user
        binding.buttonListener=this

        viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(Global.userId)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner,{
            binding.user = it
        })
    }

    override fun onLogoutClick(v: View) {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onGantiPasswordClick(v: View) {
        val action=UserFragmentDirections.actionGantiPassword()
        Navigation.findNavController(v).navigate(action)
    }
}