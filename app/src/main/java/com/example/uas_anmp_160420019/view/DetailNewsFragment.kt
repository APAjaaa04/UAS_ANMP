package com.example.uas_anmp_160420019.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uas_anmp_160420019.databinding.FragmentDetailNewsBinding
import com.example.uas_anmp_160420019.R
import com.example.uas_anmp_160420019.viewmodel.DetailNewsViewModel
import com.example.uas_anmp_160420019.view.DetailNewsInterface

class DetailNewsFragment : Fragment(), DetailNewsInterface {
    private lateinit var viewModel: DetailNewsViewModel
    private lateinit var binding: FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailNewsBinding>(inflater,R.layout.fragment_detail_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.news
        binding.user
        binding.buttonListener = this

        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        val newsId = DetailNewsFragmentArgs.fromBundle(requireArguments()).idNews
        viewModel.fetch(newsId)
        observeViewModelNews()
    }

    fun observeViewModelNews() {
        viewModel.newsLD.observe(viewLifecycleOwner,  {
            var userId = it.pengarang
            binding.news = it
            viewModel.fetchPengarang(userId)
            observeViewModelUser()
        })
    }

    fun observeViewModelUser() {
        viewModel.userLD.observe(viewLifecycleOwner,  {
            binding.user = it
        })
    }

    override fun onKembaliClick(v: View) {
        val action=DetailNewsFragmentDirections.actionNewsFragment()
        Navigation.findNavController(v).navigate(action)
    }
}