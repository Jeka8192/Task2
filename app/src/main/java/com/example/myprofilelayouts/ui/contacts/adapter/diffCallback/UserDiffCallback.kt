package com.example.myprofilelayouts.ui.contacts.adapter.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.myprofilelayouts.model.User

class UserDiffCallback() : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}