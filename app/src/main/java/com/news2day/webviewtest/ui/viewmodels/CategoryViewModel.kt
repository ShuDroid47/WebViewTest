package com.news2day.webviewtest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news2day.webviewtest.models.CatResposeData
import com.news2day.webviewtest.models.CategoryData
import com.news2day.webviewtest.network.ApiService
import com.news2day.webviewtest.network.repos.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class CategoryViewModel constructor(private val mRepo : DataRepository) : ViewModel() {
    private var catLiveDataList = MutableLiveData<ArrayList<CategoryData>>()

    val catList: LiveData<ArrayList<CategoryData>>
        get() = catLiveDataList

    val errorMessage = MutableLiveData<String>()

    fun refreshScreen(){
         catLiveDataList = MutableLiveData<ArrayList<CategoryData>>()
        getCatDataList()
    }

    fun updateTitle(){
       // catLiveDataList.postValue()
    }

    fun getCatDataList(){
        val response = mRepo.getCatData()
        response.enqueue(object : Callback<CatResposeData>{
            override fun onResponse(
                call: Call<CatResposeData>,
                response: Response<CatResposeData>
            ) {
                response.body()?.data.also { catLiveDataList.value =
                    it?.let { it1 -> ArrayList(it1) }
                }
            }

            override fun onFailure(call: Call<CatResposeData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
