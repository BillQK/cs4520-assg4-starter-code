package com.cs4520.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductListViewModel() : ViewModel() {
    private val repo = ProductListRepo(Retrofit.api);
    private val _productList = MutableLiveData<Result<List<Product>>>()
    val productList : LiveData<Result<List<Product>>> = _productList;

    init {
        fetchProducts()
    }


    private fun fetchProducts(page: Int? = null) {
        viewModelScope.launch {
            val result = repo.getProductsFromApi(page)
                _productList.value = result
        }
    }

}
