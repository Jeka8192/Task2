package com.example.myprofilelayouts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myprofilelayouts.databinding.ActivityMainBinding
import java.util.*

const val MAIL_KEY = "mail"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contacts.setOnClickListener() {
            contacts()
        }

        val arguments: Bundle? = intent.extras
        binding.name.text = extractName(arguments)
    }

    private fun extractName(arguments: Bundle?): String {
        var userName = ""
        if (arguments != null) {

            val mail = arguments.getString(MAIL_KEY).orEmpty()
            val substringMail: String = mail.substringBefore('@')
            val listMail: List<String> = substringMail.split(".")

            if (listMail.isNotEmpty()) {

                for (str: String in listMail) {

                    userName += str.replaceFirstChar {

                        if (it.isLowerCase()) {
                            it.titlecase(Locale.getDefault())
                        } else {
                            it.toString()
                        }

                    }

                    userName += " "
                }

            }

        }
        return userName
    }

    private fun contacts() {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right, R.anim.left)
    }

}