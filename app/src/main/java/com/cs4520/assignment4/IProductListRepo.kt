package com.cs4520.assignment4

import okhttp3.ResponseBody

interface IProductListRepo {
    suspend fun getProductsFromApi(page: Int?): Result<List<Product>>
} 
