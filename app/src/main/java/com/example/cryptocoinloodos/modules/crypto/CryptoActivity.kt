package com.example.cryptocoinloodos.modules.crypto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinloodos.databinding.ActivityCryptoBinding
import com.example.cryptocoinloodos.modules.cryptodetail.CryptoDetailActivity
import com.example.cryptocoinloodos.modules.menu.MenuActivity
import com.example.cryptocoinloodos.repo.CryptoCoin

class CryptoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCryptoBinding.inflate(layoutInflater) }
    private val adapter by lazy {
        CryptoActivityAdapter {
            redirectToCryptoDetail(it)
        }
    }
    private lateinit var viewModel: CryptoActivityViewModel
    private val viewModelFactory by lazy { CryptoActivityViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CryptoActivityViewModel::class.java)
        binding.apply {
            toolbar.show(
                title = "Crypto Coin",
                showMenu = { startActivity(Intent(this@CryptoActivity, MenuActivity::class.java)) })
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@CryptoActivity)
            ediTextSearch.addTextChangedListener {
                adapter.filter.filter(it.toString())
            }
        }
        observeData()
    }

    private fun observeData() {
        viewModel.coinList.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    private fun redirectToCryptoDetail(cryptoCoin: CryptoCoin) {
        startActivity(CryptoDetailActivity.createIntent(this, cryptoCoin.id, cryptoCoin.name))
    }

}