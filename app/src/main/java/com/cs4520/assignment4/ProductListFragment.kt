package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs4520.assignment4.databinding.FragmentProductlistBinding

/*
This class extends Fragment, making it a component that can be added to an activity to encapsulate
its own UI and behavior.
 */
class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var binding: FragmentProductlistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductlistBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = binding.productRecyclerView
        recyclerview.layoutManager = LinearLayoutManager(context)
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.productList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    handleSuccess(result.data)
                }

                is Result.Error -> {
                    handleError(result.exception)
                }

                is Result.Empty -> {
                    handleEmpty(result.exception)
                }
            }
        }
    }

    private fun handleSuccess(products: List<Product>) {
        val adapter = ProductAdapter(products)
        binding.productRecyclerView.adapter = adapter
    }

    private fun handleError(exception: Exception) {
        Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_LONG).show()
    }

    private fun handleEmpty(exception: Exception) {
        Toast.makeText(context, "No product available", Toast.LENGTH_LONG).show()
    }


}
