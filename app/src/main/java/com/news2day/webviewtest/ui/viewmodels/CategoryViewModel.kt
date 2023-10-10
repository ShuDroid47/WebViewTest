package com.news2day.webviewtest.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news2day.webviewtest.R
import com.news2day.webviewtest.helpers.ApiExceptions
import com.news2day.webviewtest.helpers.Coroutines
import com.news2day.webviewtest.helpers.NoInternetException
import com.news2day.webviewtest.network.models.CategoryData
import com.news2day.webviewtest.network.repos.DataRepository

class CategoryViewModel constructor(private val mRepo : DataRepository) : ViewModel() {
    private val catLiveDataList = MutableLiveData<ArrayList<CategoryData>>()

    val catList: LiveData<ArrayList<CategoryData>>
        get() = catLiveDataList

    var errorMessage = MutableLiveData<String>()

    fun refreshScreen(){
         catLiveDataList.value =ArrayList<CategoryData>()
        //getCatDataList()
        getCatDataList2()
    }

    private fun sortList() {
        catLiveDataList.value = catLiveDataList.value?.apply {
            sortBy {
                it.unique_no
            }
        }
    }

    fun updateTitle(){
        catLiveDataList.value?.get(catLiveDataList.value!!.size-1)!!.name.te = "Science"
        catLiveDataList.value = catLiveDataList.value
    }

//    fun getCatDataList(){
//        Coroutines.main {
//            try{
//                val catResponse = mRepo.getCatData()
//                if(catResponse.isSuccessful) {
//                    catResponse.let {
//                        catLiveDataList.value = it.body()?.data?.let { it1 -> ArrayList(it1) }
//                        return@let Log.d("DataResponse","DataListSize: ${catList.value?.size}")
//                    }
//                }else{
//                    errorMessage.value = "No News Found."
//                }
//            }catch (e: ApiExceptions){
//                errorMessage.value = e.message
//            }
//        }
//
//    }

    fun getCatDataList2(){
        Coroutines.main {
            try{
                val catResponse = mRepo.getCatDataResponse()

                    catResponse.let {
                        catLiveDataList.value = ArrayList(it.data)
                        return@let Log.d("DataResponse", "DataListSize: ${catList.value?.size}")
                    }
            }catch (e: ApiExceptions){
                errorMessage.value = e.message
            }catch (e: NoInternetException){
                errorMessage.value = e.message
            }
        }
    }

    fun onClickEvents (view: View){
        when (view.id){
            R.id.btn_sort -> sortList()
            R.id.btn_update -> updateTitle()
        }
    }
}
