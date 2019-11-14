package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.data.local.ContentEntity
import kotlinx.android.synthetic.main.item_content.view.*


interface ContentItemListener {
    fun onItemClick(id : String)
    fun onCheck(id: String, isFavorite: Boolean, adapterPosition: Int)
}


class ContentAdapter(private val listener : ContentItemListener) : RecyclerView.Adapter<ContentAdapter.ContentHolder>() {

    private var contents : List<ContentEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        return ContentHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_content,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(contents[position], listener)
    }

    override fun getItemCount(): Int = contents.size

      class ContentHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(content : ContentEntity, listener : ContentItemListener) {
            itemView.item_title.text = content.name
            itemView.item_favorite.isChecked = content.isFavorite
            Glide.with(itemView)
                .load(content.artworkUrl100)
                .into(itemView.item_logo)

            itemView.setOnClickListener {
                listener.onItemClick(content.contentId)
            }

            itemView.item_favorite.setOnCheckedChangeListener { _, b ->
                listener.onCheck(content.contentId, b, adapterPosition)
            }
        }

    }

    fun setData(data : List<ContentEntity>) {
        val diffResult = DiffUtil.calculateDiff(ContentDiffUtils(contents, data))
        contents = data
        diffResult.dispatchUpdatesTo(this)
    }
}

class ContentDiffUtils(
        private val oldContents : List<ContentEntity>,
        private val newContents : List<ContentEntity>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContents[oldItemPosition] == newContents[newItemPosition]
    }

    override fun getOldListSize(): Int = oldContents.size

    override fun getNewListSize(): Int = newContents.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContents[oldItemPosition] == newContents[newItemPosition]
    }
}