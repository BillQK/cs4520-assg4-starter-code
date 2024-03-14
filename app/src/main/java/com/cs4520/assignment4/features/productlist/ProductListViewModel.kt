package com.cs4520.assignment4.features.productlist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.common.Result
import com.cs4520.assignment4.core.model.Product
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: ProductListRepo) : ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>>>()
    val productList: LiveData<Result<List<Product>>> = _productList

    init {
        fetchProducts()
    }

    private fun fetchProducts(page: Int? = null) {
        viewModelScope.launch {
            val result = repository.getProducts(page)
            _productList.postValue(result)
        }

    }

}
