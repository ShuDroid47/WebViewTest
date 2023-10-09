package com.news2day.webviewtest.helpers

import android.view.View
import com.news2day.webviewtest.models.CategoryData

interface CatAdapterClickListener{
    fun onItemClick(view: View, adapterData: CategoryData)
}