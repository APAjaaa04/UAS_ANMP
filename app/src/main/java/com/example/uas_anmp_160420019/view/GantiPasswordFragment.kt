package com.example.uas_anmp_160420019.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uas_anmp_160420019.R
import com.example.uas_anmp_160420019.databinding.FragmentGantiPasswordBinding
import com.example.uas_anmp_160420019.util.Global
import com.example.uas_anmp_160420019.viewmodel.UserViewModel

class GantiPasswordFragment : Fragment(), GantiPasswordInterface{
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentGantiPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentGantiPasswordBinding>(inflater,R.layout.fragment_ganti_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.user
        binding.buttonListener = this
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(Global.userId)
        observeViewModel()


    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, {
            binding.user = it
        })
    }
    override fun onGantiClick(v: View) {
        var passwordLama = view?.findViewById<TextView>(R.id.txtPasswordLama)?.text.toString()
        var passwordBaru = view?.findViewById<TextView>(R.id.txtPasswordBaru)?.text.toString()
        var ulangPassword = view?.findViewById<TextView>(R.id.txtUlangPassword)?.text.toString()
        var error = view?.findViewById<TextView>(R.id.txtGantiError)

        if(passwordLama!= binding.user!!.password){
            error?.text = "Password lama salah"
        }
        else if(passwordLama==passwordBaru){
            error?.text = "Password tidak boleh sama dengan yang lama"
        }
        else if(passwordBaru!=ulangPassword){
            error?.text = "Password baru tidak sama"
        }
        else{
            viewModel.gantiPassword(Global.userId,passwordBaru)
            val action=GantiPasswordFragmentDirections.actionProfile()
            Navigation.findNavController(v).navigate(action)
        }
    }
}