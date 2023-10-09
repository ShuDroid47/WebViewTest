package com.news2day.webviewtest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.news2day.webviewtest.R
import com.news2day.webviewtest.adapters.CatDataAdapter
import com.news2day.webviewtest.databinding.ActivityCategoryBinding
import com.news2day.webviewtest.helpers.CatAdapterClickListener
import com.news2day.webviewtest.models.CategoryData
import com.news2day.webviewtest.network.ApiService
import com.news2day.webviewtest.network.repos.DataRepository
import com.news2day.webviewtest.ui.fatorymodels.CategoryVmFactory
import com.news2day.webviewtest.ui.viewmodels.CategoryViewModel

class CategoryActivity : AppCompatActivity(), CatAdapterClickListener {

    private lateinit var viewModel : CategoryViewModel
    private lateinit var binding : ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)
        viewModel = ViewModelProvider(this, CategoryVmFactory(DataRepository(ApiService()))).get(CategoryViewModel :: class.java)

        viewModel.getCatDataList()

        viewModel.catList.observe(this, Observer { dataList ->
            binding.rvCatList.also {
                it.setHasFixedSize(true)
                it.layoutManager = LinearLayoutManager(this)
                it.adapter = CatDataAdapter(dataList, this)
            }
        })

        viewModel.errorMessage.observe(this, Observer { msge ->
            String.also {
                Toast.makeText(
                    this,
                    "Check with Network",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        binding.srlRefresh.setOnRefreshListener(this:: onRefresh)
    }

    private fun onRefresh() {
        viewModel.refreshScreen()
    }

    override fun onItemClick(view: View, adapterData: CategoryData) {
        Toast.makeText(
            this,
            adapterData.name.te + "Clicked",
            Toast.LENGTH_LONG
        ).show()
    }
}