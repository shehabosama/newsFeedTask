package com.example.newsfeedtask.adapters


import android.R.attr.data
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedtask.R
import com.example.newsfeedtask.model.NewsItem
import com.squareup.picasso.Picasso
import java.util.*


class PaginationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var context: Context? = null
    private var list: MutableList<NewsItem>? = mutableListOf()

    companion object {
         var _newsItemInterAction: NewsItemInterAction?=null
    }
    constructor(context: Context?,newsItemInterAction: NewsItemInterAction){
        this.context =context
        list = LinkedList()
        _newsItemInterAction=  newsItemInterAction

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
                val viewItem: View = inflater.inflate(R.layout.item_list, parent, false)
                viewHolder = ResultViewHolder(viewItem)
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newsItem: NewsItem = list!![position]
                val viewHolder: ResultViewHolder = holder as ResultViewHolder
                viewHolder.newsTitle.text = newsItem.webTitle
                viewHolder.newsDate.text = newsItem.webPublicationDate


        try {
            Picasso.get()
                .load(newsItem.fields!!.thumbnail)
                .placeholder(R.drawable.news_icon)
                .into(viewHolder.newsImageItewm)
            viewHolder.setOnClick(newsItem,position)
        } catch (e: Exception) {
            e.printStackTrace()
            Picasso.get()
                .load(R.drawable.news_icon)
                .into(viewHolder.newsImageItewm)
        }
    }

    fun removeAt(position: Int) {
        list!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list!!.size)
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    fun add(newsItem: NewsItem?) {
        list!!.add(newsItem!!)
        notifyItemInserted(list!!.size - 1)
    }

    fun addAll(newsItems: List<NewsItem?>) {
        for (result in newsItems) {
            add(result)
        }
        notifyDataSetChanged()
    }

    fun getItem(position: Int): NewsItem? {
        return list!![position]
    }

    fun getResultAt(position: Int): NewsItem? {
        return list!!.get(position)
    }
    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val newsTitle: TextView
         val newsDate: TextView
         val newsImageItewm: ImageView
         lateinit var supNewsItemInterAction:NewsItemInterAction
        init {
            newsTitle = itemView.findViewById<View>(R.id.newsTitle) as TextView
            newsDate = itemView.findViewById<View>(R.id.newsDate) as TextView
            newsImageItewm = itemView.findViewById(R.id.image_item)
            supNewsItemInterAction = _newsItemInterAction!!
        }
        fun setOnClick(newsItem:NewsItem, position: Int){
            itemView.setOnClickListener(View.OnClickListener {
                supNewsItemInterAction!!.onClick(newsItem,position)
            })
        }

    }


    public interface NewsItemInterAction{
        fun onClick(newsItem: NewsItem?, position: Int)
    }
}