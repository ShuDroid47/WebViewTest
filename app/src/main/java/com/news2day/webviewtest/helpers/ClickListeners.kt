package com.news2day.webviewtest.helpers

import android.view.View
import com.news2day.webviewtest.network.models.CategoryData

interface CatAdapterClickListener{
    fun onRvItemClick(view: View, adapterData: CategoryData)
}