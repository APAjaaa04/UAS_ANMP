package com.example.uas_anmp_160420019.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_anmp_160420019.databinding.NewsItemBinding
import com.example.uas_anmp_160420019.model.News

class NewsAdapter(val newsList:ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(), DetailClick{

    class NewsViewHolder (var view:NewsItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.news = newsList[position]
        holder.view.buttonListener = this
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun update(newNewsList: List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }
    override fun onDetailClick(v: View) {
        val action=NewsFragmentDirections.actionDetailNews(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}