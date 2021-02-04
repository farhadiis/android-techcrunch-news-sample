package com.farhad.techcrunch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.farhad.techcrunch.model.NewsItem


private const val ARG_PARAM_NEWS = "news_param"

class DetailNewsFragment : Fragment() {

    private var newsItem: NewsItem? = null

    private lateinit var imageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var authorView: TextView
    private lateinit var dateView: TextView
    private lateinit var contentView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsItem = it.getSerializable(ARG_PARAM_NEWS) as? NewsItem
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.imageView)
        titleView = view.findViewById(R.id.titleView)
        authorView = view.findViewById(R.id.authorView)
        dateView = view.findViewById(R.id.dateView)
        contentView = view.findViewById(R.id.contentView)
        contentView.setOnClickListener {
            newsItem?.url?.let {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsItem?.let {
            Glide.with(this)
                .load(it.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            titleView.text = it.title
            authorView.text = it.author
            dateView.text = Util.getIsoDate(it.publishedAt)
            contentView.text = it.content
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(newsItem: NewsItem) =
            DetailNewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_NEWS, newsItem)
                }
            }
    }
}