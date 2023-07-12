package com.example.myprofilelayouts.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myprofilelayouts.R
import com.example.myprofilelayouts.databinding.ActivityUserBinding
import com.example.myprofilelayouts.model.User
import com.example.myprofilelayouts.ui.contacts.adapter.diffCallback.UserDiffCallback
import com.example.myprofilelayouts.ui.contacts.adapter.listener.OnItemClickListener
import com.example.myprofilelayouts.utils.ext.loadImage


class UsersAdapter(private val listener: OnItemClickListener) :
    ListAdapter<User, UsersAdapter.UsersViewHolder>(UserDiffCallback()) {

    inner class UsersViewHolder(
        private val binding: ActivityUserBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(user: User) {
            with(binding) {
                name.text = user.name
                profession.text = user.profession
                if (user.photo.isNotBlank()) {
                    photoUser.loadImage(user.photo)
                } else {
                    photoUser.setImageResource(R.drawable.ic_user_avatar)
                }
            }
            setListeners(user)
        }

        private fun setListeners(user: User) {
            binding.basket.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)
        holder.bindTo(user)
    }
}