package com.news2day.webviewtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.news2day.webviewtest.R
import com.news2day.webviewtest.databinding.RvItemCategoryBinding
import com.news2day.webviewtest.helpers.CatAdapterClickListener
import com.news2day.webviewtest.network.models.CategoryData

class CatDataAdapter constructor(
    private var catDataList: ArrayList<CategoryData>,
    private val listener: CatAdapterClickListener
) : RecyclerView.Adapter<CatDataAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatDataAdapter.ItemHolder, position: Int) {
        var catData = catDataList[position]
        holder.rvItemView.catData = catData

        holder.rvItemView.cvParent.setOnClickListener {
            listener.onRvItemClick(
                holder.rvItemView.cvParent,
                catDataList[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return catDataList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ItemHolder(val rvItemView: RvItemCategoryBinding): RecyclerView.ViewHolder(rvItemView.root)

}