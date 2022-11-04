package com.myu.myurandomimageapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myu.myurandomimageapp.databinding.ImageLayoutBinding
import com.myu.myurandomimageapp.model.ImageResultItem

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

   inner class ImageViewHolder(val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object  : DiffUtil.ItemCallback<ImageResultItem>(){

        override fun areItemsTheSame(oldItem: ImageResultItem, newItem: ImageResultItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ImageResultItem,
            newItem: ImageResultItem,
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ  = AsyncListDiffer(this,diffCallBack)

    fun submitList(list : List<ImageResultItem>) = differ.submitList(list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
            )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
      val currImage = differ.currentList.get(position)

        holder.binding.apply {
            tvDescription.text =  currImage.description

            val imageLink = currImage.urls.full
            imageView.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }


}