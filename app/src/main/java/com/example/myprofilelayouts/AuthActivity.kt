package com.example.myprofilelayouts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprofilelayouts.databinding.ActivityAuthBinding
import com.example.myprofilelayouts.model.User
import com.github.javafaker.Faker

private const val incorrectEntry: String = "Incorrect entry"

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val userRepository = UserRepository.get()

    private var users = mutableListOf<User>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        users = (1..4).map {
            User(

                id = null,
                photo = IMAGES[it % IMAGES.size],
                name = faker.name().name(),
                profession = faker.company().name(),
                address = faker.address().cityName(),
            )
        }.toMutableList()
        for (user: User in users) {
            userRepository.addUser(user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rememberMe.setOnClickListener {
        }
        binding.register.setOnClickListener() {
            register()
        }
    }

    private fun register() {
        val mail: String = mailExtraction()
        val password: String = passwordExtraction()

        if (mail == incorrectEntry) {
            binding.email.error = incorrectEntry
        } else {
            binding.email.error = null
        }

        if (password == incorrectEntry) {
            binding.password.error = incorrectEntry
        } else {
            binding.password.error = null
        }

        if (mail != incorrectEntry && password != incorrectEntry) {
            createIntentMainActivity(mail)
        }
        createIntentMainActivity(mail)
    }

    private fun createIntentMainActivity(mail: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MAIL_KEY, mail)
        startActivity(intent)
        overridePendingTransition(R.anim.right, R.anim.left)
        finish()
    }


    private fun passwordExtraction(): String {
        val password: String = binding.passwordText.text.toString()
        return if (isPasswordValid(password)) password
        else incorrectEntry
    }

    private fun mailExtraction(): String {
        val mail: String = binding.emailText.text.toString()
        return if (isEmailValid(mail)) mail
        else incorrectEntry
    }

    private fun isEmailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val reg: Regex = "[1-9a-zA-Z]+".toRegex()
        return if (password.length < 8 || password.length > 48) false
        else password.matches(reg)
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://images.unsplash.com/photo-1600267185393-e158a98703de?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NjQ0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1579710039144-85d6bdffddc9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0Njk1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODE0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1620252655460-080dbec533ca?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzQ1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1613679074971-91fc27180061?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzUz&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1485795959911-ea5ebf41b6ae?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzU4&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1545996124-0501ebae84d0?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzY1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/flagged/photo-1568225061049-70fb3006b5be?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0Nzcy&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1567186937675-a5131c8a89ea?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODYx&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1546456073-92b9f0a8d413?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODY1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800"
        )
    }
}