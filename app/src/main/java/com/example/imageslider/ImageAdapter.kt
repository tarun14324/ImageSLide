package com.example.imageslider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.imageslider.databinding.ItemImageSlideViewBinding

class ImageAdapter(
    val imageList: ArrayList<Int>, val viewPager: ViewPager2,
    private val onItemClicked: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private lateinit var binding: ItemImageSlideViewBinding

    class ImageViewHolder(view: ItemImageSlideViewBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.image
        val card = view.cardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image_slide_view,
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.image.setImageResource(imageList[position])
        holder.card.setOnClickListener {
            onItemClicked(viewPager.currentItem)
        }
        if (position == imageList.size - 1) {
            viewPager.post {
                imageList.addAll(imageList)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}