package com.news2day.webviewtest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.news2day.webviewtest.R
import com.news2day.webviewtest.adapters.CatDataAdapter
import com.news2day.webviewtest.databinding.ActivityCategoryBinding
import com.news2day.webviewtest.helpers.CatAdapterClickListener
import com.news2day.webviewtest.network.models.CategoryData
import com.news2day.webviewtest.ui.fatorymodels.CategoryVmFactory
import com.news2day.webviewtest.ui.viewmodels.CategoryViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class CategoryActivity : AppCompatActivity(), CatAdapterClickListener, DIAware {

    override val di: DI by closestDI()
    private val factory : CategoryVmFactory by instance()

    private lateinit var viewModel : CategoryViewModel
    private lateinit var binding : ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_category
        )

        viewModel = ViewModelProvider(this,
            factory
        )[CategoryViewModel :: class.java]

        //viewModel.getCatDataList()
        viewModel.getCatDataList2()

        setDataListObserver()

        errorMessageObserver()

        //binding.srlRefresh.isEnabled = true
        binding.srlRefresh.setOnRefreshListener(this:: onRefresh)
        binding.btnSort.setOnClickListener(this::onClick)
        binding.btnUpdate.setOnClickListener(this::onClick)
    }

    private fun errorMessageObserver() {
        viewModel.errorMessage.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setDataListObserver() {
        viewModel.catList.observe(this) { dataList ->
            setNoRefresh()
            binding.rvCatList.also {
                Log.e("DataList Size: ", "Size: ${dataList.size}")
                if (dataList.size > 0) {
                    it.setHasFixedSize(true)
                    it.layoutManager = LinearLayoutManager(this)
                    it.adapter = CatDataAdapter(dataList, this)
                }
            }
        }
    }

    private fun onClick(view: View?) {
        viewModel.onClickEvents(view!!)
    }

    private fun setNoRefresh() {
        if (binding.srlRefresh.isRefreshing)
            binding.srlRefresh.isRefreshing = false
    }

    private fun onRefresh() {
        viewModel.refreshScreen()
    }

    override fun onRvItemClick(view: View, adapterData: CategoryData) {
        Toast.makeText(
            this,
            adapterData.name.te + "Clicked",
            Toast.LENGTH_LONG
        ).show()
    }
}