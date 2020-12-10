package com.example.cryptocoinloodos.modules.favorite


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinloodos.databinding.RowMainBinding
import com.example.cryptocoinloodos.repo.CryptoCoin

class FavoriteCryptoCoinAdapter() :
    RecyclerView.Adapter<MainActivityViewHolder>() {


    private val list = mutableListOf<CryptoCoin>()

    fun updateList(cryptoCoin: List<CryptoCoin>) {
        list.clear()
        list.addAll(cryptoCoin)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val binding = RowMainBinding.inflate(LayoutInflater.from(parent.context))
        return MainActivityViewHolder(binding)

    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bindItem(list[position])

    }


}

class MainActivityViewHolder(private val binding: RowMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(cryptoCoin: CryptoCoin) {
        binding.textViewName.text = cryptoCoin.name
        binding.textViewSymbol.text = cryptoCoin.symbol

    }

}