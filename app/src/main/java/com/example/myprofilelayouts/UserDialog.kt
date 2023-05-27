package com.example.myprofilelayouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.myprofilelayouts.model.User

class UserDialog : DialogFragment() {
    private val userRepository = UserRepository.get()

    companion object {
        const val TAG = "UserDialog"
        fun newInstance(): UserDialog {
            return UserDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupClickListeners(view: View) {
        val button: Button = view.findViewById(R.id.save)
        button.setOnClickListener {
            val photoEditText: EditText? = dialog?.findViewById(R.id.user_name_text)
            val nameEditText: EditText? = dialog?.findViewById(R.id.user_name_text)
            val professionEditText: EditText? = dialog?.findViewById(R.id.user_name_text)
            val addressEditText: EditText? = dialog?.findViewById(R.id.user_name_text)

            val user = User(
                id = null,
                photo = photoEditText?.text.toString(),
                name = nameEditText?.text.toString(),
                profession = professionEditText?.text.toString(),
                address = addressEditText?.text.toString()
            )
            userRepository.addUser(user)
            dismiss()
        }
    }

}
