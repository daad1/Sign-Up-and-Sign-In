package com.example.signupandsignin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.signupandsignin.databinding.ItemRowBinding

class RVAdapter ( private var userList: ArrayList<Users>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return  ItemViewHolder(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.apply {
            tvEmail.text = user.email
            tvName.text = user.name
            tvMobile.text = user.mobile
            tvPassword.text = user.password

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun update(userList: ArrayList<Users>){
        this.userList = userList
        notifyDataSetChanged()
    }
}