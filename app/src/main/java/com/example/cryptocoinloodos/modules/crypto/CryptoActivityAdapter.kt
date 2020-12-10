package com.example.cryptocoinloodos.modules.crypto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinloodos.databinding.RowMainBinding
import com.example.cryptocoinloodos.repo.CryptoCoin

class CryptoActivityAdapter(private val onClick: (CryptoCoin) -> Unit) :
    RecyclerView.Adapter<MainActivityViewHolder>(), Filterable {


    private val list = mutableListOf<CryptoCoin>()
    private val allCryptoList = mutableListOf<CryptoCoin>()

    fun updateList(cryptoCoin: List<CryptoCoin>) {
        list.clear()
        list.addAll(cryptoCoin)
        allCryptoList.addAll(cryptoCoin)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val binding = RowMainBinding.inflate(LayoutInflater.from(parent.context))
        return MainActivityViewHolder(binding)

    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bindItem(list[position], onClick)

    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val filteredList = mutableListOf<CryptoCoin>()
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(allCryptoList)
            } else {
                allCryptoList.forEach {
                    if (it.symbol.toLowerCase().contains(
                            charSequence.toString().toLowerCase()
                        ) || it.name.toLowerCase().contains(charSequence.toString().toLowerCase())
                    ) {
                        filteredList.add(it)
                    }
                }

            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults

        }

        override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
            list.clear()
            list.addAll(filterResults?.values as List<CryptoCoin>)
            notifyDataSetChanged()
        }

    }


}

class MainActivityViewHolder(private val binding: RowMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(cryptoCoin: CryptoCoin, onClick: (CryptoCoin) -> Unit) {
        binding.textViewName.text = cryptoCoin.name
        binding.textViewSymbol.text = cryptoCoin.symbol

        binding.linearLayoutContainer.setOnClickListener {
            onClick(cryptoCoin)
        }


    }

}