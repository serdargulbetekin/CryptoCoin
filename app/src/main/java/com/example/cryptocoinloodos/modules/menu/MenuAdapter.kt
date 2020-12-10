package com.example.cryptocoinloodos.modules.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinloodos.databinding.RowMenuBinding

class MenuAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<MenuViewHolder>() {

    var itemList: List<String> = mutableListOf()
        set(value) {
            (field as? MutableList)?.apply {
                clear()
                addAll(value)
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = RowMenuBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding)

    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindItem(itemList[position], onClick)
    }


}

class MenuViewHolder(private val binding: RowMenuBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(name: String, onClick: (String) -> Unit) {

        binding.apply {
            textViewName.text = name
            linearLayoutContainer.setOnClickListener { onClick(name) }
        }
    }

}

