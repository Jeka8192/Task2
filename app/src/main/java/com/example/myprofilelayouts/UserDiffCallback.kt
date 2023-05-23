package com.example.myprofilelayouts

import androidx.recyclerview.widget.DiffUtil
import com.example.myprofilelayouts.model.User

class UserDiffCallback() : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}