package com.cs4520.assignment4


class ProductListRepo(private val productService : ProductService) : IProductListRepo {
    override suspend fun getProductsFromApi(page: Int?): Result<List<Product>> {
        return try {
            val response = productService.getProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it)
                } ?: Result.Empty(Exception("No data"))
            } else {
              return Result.Error(Exception("API Error: ${response.message()}$"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
