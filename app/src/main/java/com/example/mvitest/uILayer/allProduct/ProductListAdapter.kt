package com.example.mvitest.uILayer.allProduct


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvitest.data.domain.Products
import com.example.mvitest.databinding.ProductItemBinding

private const val TAG = "ProductListAdapter"
class ProductListAdapter(
    private var products: List<Products>,
    private val listener: IProductsList
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private lateinit var binding: ProductItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObject: Products = products[holder.adapterPosition]
        holder.tvBrand.text = currentObject.brand
        holder.tvPrice.text = currentObject.price.toString()
        holder.tvTitle.text = currentObject.title
        Glide.with(binding.root).load(currentObject.url).into(holder.imageView)

        binding.btnSave.setOnClickListener {
            Log.i(TAG, "onBindViewHolder: $currentObject")
            listener.saveProduct(currentObject)
        }

    }

    fun setList(remoteProduct: List<Products>) {
        products = remoteProduct
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: ProductItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val imageView: ImageView = binding.imageView
        val tvBrand: TextView = binding.tvBrand
        val tvPrice: TextView = binding.tvPrice
        val tvTitle = binding.tvTitle

    }
}
