package com.example.myprofilelayouts.ui.contacts

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myprofilelayouts.databinding.ActivityContactsBinding
import com.example.myprofilelayouts.model.User
import com.example.myprofilelayouts.ui.addContact.AddContactDialog
import com.example.myprofilelayouts.ui.contacts.adapter.UsersAdapter
import com.example.myprofilelayouts.ui.contacts.adapter.listener.OnItemClickListener

class ContactsActivity : AppCompatActivity() {
    private val contactsViewModel: ContactsViewModel by viewModels()
    private val binding: ActivityContactsBinding by lazy {
        ActivityContactsBinding.inflate(layoutInflater)
    }
    private val usersAdapter: UsersAdapter by lazy {
        UsersAdapter(
            object : OnItemClickListener {
                override fun onItemClick(user: User) {
                    contactsViewModel.deleteUser(user)
                    showToast()
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        with(binding) {
            addContacts.setOnClickListener() {
                addContacts()
            }
            arrowBack.setOnClickListener() {
                finish()
            }

            recyclerView.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@ContactsActivity)
                adapter = usersAdapter
            }
        }

        updateUsers()
    }

    private fun updateUsers() {
        contactsViewModel.users.observe(this) { users ->
            users?.let {
                usersAdapter.submitList(users.toMutableList())
            }
        }
    }

    private fun showToast() {
        val text = "contact has been removed"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.NO_GRAVITY, 0, 0)
        toast.show()
    }

    private fun addContacts() {
        val dialog = AddContactDialog.newInstance()
        dialog.show(supportFragmentManager, AddContactDialog.TAG)
    }
}