package com.example.myprofilelayouts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprofilelayouts.databinding.ActivityAuthBinding

private const val incorrectEntry: String = "Incorrect entry"

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

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
            binding.eMail.helperText = incorrectEntry
        } else {
            binding.eMail.helperText = ""
        }

        if (password == incorrectEntry) {
            binding.password.helperText = incorrectEntry
        } else {
            binding.password.helperText = ""
        }

        if (mail != incorrectEntry && password != incorrectEntry) {
            createIntentMainActivity(mail)
        }
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
}