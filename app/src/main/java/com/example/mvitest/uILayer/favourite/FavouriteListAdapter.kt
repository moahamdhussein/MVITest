package com.example.mvitest.uILayer.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvitest.data.domain.Products
import com.example.mvitest.databinding.FavoriteItemBinding

class FavouriteListAdapter(private var favouriteProducts: List<Products>,val  listener: IFavouriteList) : RecyclerView.Adapter<FavouriteListAdapter.ViewHolder>() {
    private lateinit var binding: FavoriteItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = FavoriteItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = favouriteProducts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObject: Products = favouriteProducts[holder.adapterPosition]
        holder.tvBrand.text = currentObject.brand
        holder.tvPrice.text = currentObject.price.toString()
        holder.tvTitle.text = currentObject.title
        Glide.with(binding.root).load(currentObject.url).into(holder.imageView)
        binding.btnRemove.setOnClickListener { listener.deleteProduct(currentObject) }
    }

    fun setList(products: List<Products>) {
        favouriteProducts = products
        notifyDataSetChanged()

    }

    inner class  ViewHolder(itemView: FavoriteItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val imageView: ImageView = binding.imageView
        val tvBrand: TextView =binding.tvBrand
        val tvPrice: TextView =binding.tvPrice
        val tvTitle = binding.tvTitle
    }
}
