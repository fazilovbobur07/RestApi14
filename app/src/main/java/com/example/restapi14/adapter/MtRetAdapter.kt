package com.example.restapi14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi14.databinding.ItemRvBinding
import com.example.restapi14.models.MyTodo


class MyRetAdapter (var list: ArrayList<MyTodo>): RecyclerView.Adapter<MyRetAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myTodo: MyTodo) {
            itemRvBinding.matn.text = myTodo.matn
            itemRvBinding.holat.text = myTodo.holat
            itemRvBinding.sarlavha.text = myTodo.sarlavha
            itemRvBinding.ohirgiMuddat.text = myTodo.oxirgi_muddat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}