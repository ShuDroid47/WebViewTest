package com.news2day.webviewtest.ui.fatorymodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news2day.webviewtest.network.repos.DataRepository
import com.news2day.webviewtest.ui.viewmodels.CategoryViewModel

@Suppress("UNCHECKED_CAST")
class CategoryVmFactory constructor(private val mRepo : DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(CategoryViewModel :: class.java))
            CategoryViewModel(this.mRepo) as T
        else
        {
            throw IllegalArgumentException ("ViewModel not found.")
        }
    }
}