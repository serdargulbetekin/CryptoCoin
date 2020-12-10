package com.example.cryptocoinloodos.modules.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinloodos.databinding.ActivityFavoriteCryptoCoinBinding

class FavoriteCryptoCoinActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavoriteCryptoCoinBinding.inflate(layoutInflater) }
    private val viewModelFactory by lazy { FavoriteCryptoCoinViewModelFactory() }
    private lateinit var viewModel: FavoriteCryptoCoinViewModel

    private val adapter by lazy {
        FavoriteCryptoCoinAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FavoriteCryptoCoinViewModel::class.java)

        binding.apply {
            toolbar.show("Favorites", showBack = { onBackPressed() })
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@FavoriteCryptoCoinActivity)
        }
        observeData()
    }

    private fun observeData() {
        viewModel.favoriteList.observe(this, Observer {
            if (it.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.textViewEmptyMessage.visibility = View.VISIBLE
                binding.textViewEmptyMessage.text =
                    "You do not have favorite coin yet. You can add it via add button in detail page."
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.textViewEmptyMessage.visibility = View.GONE
                adapter.updateList(it)
            }
        })
    }
}