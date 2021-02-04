package com.farhad.techcrunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhad.techcrunch.viewmodel.NewsStatus
import com.farhad.techcrunch.viewmodel.NewsViewModel
import com.farhad.techcrunch.viewmodel.NewsViewModelFactory

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val newsViewModel: NewsViewModel by activityViewModels {
        NewsViewModelFactory(TechApp.instance.repository)
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        progressBar = view.findViewById(R.id.progressbar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = NewsListAdapter(Glide.with(activity!!))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        newsViewModel.news.observe(this) { status ->
            status.let {
                when (it) {
                    is NewsStatus.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    is NewsStatus.Success -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        (recyclerView.adapter as NewsListAdapter).submitList(it.news)
                    }
                    is NewsStatus.Error -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}