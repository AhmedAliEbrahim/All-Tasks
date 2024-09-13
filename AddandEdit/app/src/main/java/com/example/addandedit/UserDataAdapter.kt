package com.example.addandedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.addandedit.databinding.ItemUserBinding

class UserDataAdapter(
    private val list: MutableList<User>,
    private val onItemClick: (User, Int) -> Unit, // Update the lambda to include position
    private val onRemoveClick: (User) -> Unit // No change needed here
) : RecyclerView.Adapter<UserDataAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindData(list[position], position)
    }

    override fun getItemCount() = list.size

    inner class MyHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: User, position: Int) {
            binding.personName.text = user.name
            binding.personMail.text = user.mail
            binding.personPhone.text = user.phone
            binding.editButton.setOnClickListener {
                onItemClick(user,adapterPosition)
            }
            binding.removeButton.setOnClickListener {
                onRemoveClick(user)
            }
        }
    }

    fun updateList(newList: List<User>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
