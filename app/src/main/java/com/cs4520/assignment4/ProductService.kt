package com.cs4520.assignment4

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(Api.ENDPOINT)
    suspend fun getProducts() : Response<List<Product>>
}
