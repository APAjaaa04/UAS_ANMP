package com.example.uas_anmp_160420019.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_anmp_160420019.R
import com.example.uas_anmp_160420019.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var viewModel:NewsViewModel
    private val newsAdapter=NewsAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.refresh()
        var recViewNews = view.findViewById<RecyclerView>(R.id.recViewNews)
        recViewNews.layoutManager = LinearLayoutManager(context)
        recViewNews.adapter=newsAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.newsLD.observe(viewLifecycleOwner,{
            newsAdapter.update(it)
            var txtError = view?.findViewById<TextView>(R.id.txtError)
            var progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

            if(it.isEmpty()) {
                txtError?.visibility = View.VISIBLE
                progressBar?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        })
    }
}