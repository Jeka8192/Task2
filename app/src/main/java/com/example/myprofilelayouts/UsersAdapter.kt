package com.example.myprofilelayouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myprofilelayouts.databinding.ActivityUserBinding
import com.example.myprofilelayouts.model.User

private const val TAG = "UsersAdapter"

class UsersAdapter(usersLiveData: LiveData<List<User>>) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: View)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    var users: List<User> = usersLiveData.value.orEmpty()


    class UsersViewHolder(
        val binding: ActivityUserBinding, listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.basket.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition, binding.basket)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityUserBinding.inflate(inflater, parent, false)


        return UsersViewHolder(binding, this.listener)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            name.text = user.name
            profession.text = user.profession
            if (user.photo.isNotBlank()) {
                Glide.with(photoUser.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(photoUser)
            } else {
                photoUser.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun getItemCount() = users.size

    fun swap(users: List<User>) {
        val diffCallback = UserDiffCallback(this.users, users)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.users = users
        diffResult.dispatchUpdatesTo(this)
    }

}