package com.example.cryptocoinloodos.modules.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinloodos.databinding.ActivityMenuBinding
import com.example.cryptocoinloodos.modules.favorite.FavoriteCryptoCoinActivity

class MenuActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        MenuAdapter {
            onMenuItemClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            toolbar.show(
                title = "Crypto Coin",
                showBack = { onBackPressed() })
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MenuActivity)
            adapter.itemList = fillMenu()
        }
    }

    private fun onMenuItemClick(item: String) {
        when (item) {
            "Favorites" -> {
                startActivity(Intent(this, FavoriteCryptoCoinActivity::class.java))
            }
        }
    }

    private fun fillMenu() = mutableListOf<String>().apply {
        add("Favorites")
    }

}