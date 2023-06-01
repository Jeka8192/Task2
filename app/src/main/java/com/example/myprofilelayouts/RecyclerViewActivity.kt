package com.example.myprofilelayouts

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myprofilelayouts.databinding.ActivityRecyclerviewBinding
import com.example.myprofilelayouts.model.User

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var adapter: UsersAdapter
    private val userRepository = UserRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setHasFixedSize(true)
        binding.addContacts.setOnClickListener() {
            addContacts()
        }
        binding.arrowBack.setOnClickListener() {
            finish()
        }

        adapter = UsersAdapter(userRepository.getUsers())

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        updateUsers(userRepository.getUsers())
        clickBasket()
    }

    private fun clickBasket() {
        adapter.setOnItemClickListener(object : UsersAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: View) {
                if (position >= 0) {
                    userRepository.deleteUsers(adapter.users[position])
                    showToast()
                }
            }
        })
    }

    private fun updateUsers(usersLiveData: LiveData<List<User>>) {
        usersLiveData.observe(this, Observer { users ->
            users?.let {
                adapter.submitList(users)
                adapter.users = users
            }
        })
    }

    private fun showToast() {
        val text = "contact has been removed"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.NO_GRAVITY, 0, 0)
        toast.show()
    }

    private fun addContacts() {
        val dialog = UserDialog.newInstance()
        dialog.show(supportFragmentManager, UserDialog.TAG)
    }
}