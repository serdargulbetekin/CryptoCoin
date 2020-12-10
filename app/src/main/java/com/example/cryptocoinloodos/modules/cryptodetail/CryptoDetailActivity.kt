package com.example.cryptocoinloodos.modules.cryptodetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cryptocoinloodos.databinding.ActivityCryptoDetailBinding

class CryptoDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCryptoDetailBinding.inflate(layoutInflater) }

    private val symbolId by lazy {
        intent?.extras?.getString(EXTRAS_SYMBOL_ID) ?: ""
    }
    private val symbolName by lazy {
        intent?.extras?.getString(EXTRAS_SYMBOL_NAME) ?: ""
    }

    private lateinit var viewModelFactory: CryptoDetailViewModelFactory
    private lateinit var viewModel: CryptoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModelFactory = CryptoDetailViewModelFactory(symbolId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CryptoDetailViewModel::class.java)
        binding.toolbar.show(symbolName, showBack = { onBackPressed() })
        viewModel.coinDetail.observe(this, Observer {
            binding.apply {

                Glide.with(this@CryptoDetailActivity)
                    .load(it.cryptoCoinImage.small)
                    .centerCrop()
                    .into(binding.imageView)

                textViewName.text = it.name
                textViewSymbol.text = it.symbol
                textViewPrice.text = it.priceChange
                textViewPricePercentage.text = it.priceChangePercentage
            }
        })
        binding.textViewAddFav.setOnClickListener {
            viewModel.saveCryptoCoin()
        }

        binding.textViewSaveInterval.setOnClickListener {
            viewModel.timeInterval(binding.editTextInterval.text.toString())
        }
    }

    companion object {
        private const val EXTRAS_SYMBOL_ID = "EXTRAS_SYMBOL_ID"
        private const val EXTRAS_SYMBOL_NAME = "EXTRAS_SYMBOL_NAME"
        fun createIntent(context: Context, id: String, symbolName: String) =
            Intent(context, CryptoDetailActivity::class.java).apply {
                putExtra(EXTRAS_SYMBOL_ID, id)
                putExtra(EXTRAS_SYMBOL_NAME, symbolName)
            }
    }
}