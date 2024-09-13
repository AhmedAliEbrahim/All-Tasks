package com.example.addandedit

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.addandedit.databinding.FragmentAddEditBinding

class AddEditFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private var userPosition: Int? = null
    private lateinit var binding: FragmentAddEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPosition = arguments?.getInt("position")
        val userName = arguments?.getString("name")
        val userMail = arguments?.getString("mail")
        val userPhone = arguments?.getString("phone")
        binding.nameInput.setText(userName)
        binding.mailInput.setText(userMail)
        binding.phoneInput.setText(userPhone)

        binding.buttonSave.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.mailInput.text.toString().trim()
            val phone = binding.phoneInput.text.toString().trim()

            if (validateInputs(name, email, phone)) {
                val updatedUser = User(name, email, phone)

                if (userPosition != null) {
                    userViewModel.updateUser(userPosition!!, updatedUser)
                } else {

                    userViewModel.addUser(updatedUser)
                }

                findNavController().popBackStack()
            }
        }
    }

    private fun validateInputs(name: String, email: String, phone: String): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            binding.nameInputLayout.error = "Name is required"
            isValid = false
        } else if (!name.matches(Regex("^[a-zA-Z\\s]+$"))) {
            binding.nameInputLayout.error = "Name must contain only letters and spaces"
            isValid = false
        } else {
            binding.nameInputLayout.error = null
        }

        if (email.isEmpty()) {
            binding.mailInputLayout.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.mailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            binding.mailInputLayout.error = null
        }

        if (phone.isEmpty()) {
            binding.phoneInputLayout.error = "Phone number is required"
            isValid = false
        } else if (!phone.matches(Regex("^[0-9]+$"))) {
            binding.phoneInputLayout.error = "Phone number must contain only digits"
            isValid = false
        } else {
            binding.phoneInputLayout.error = null
        }

        if (!isValid) {
            Toast.makeText(requireContext(), "Please fix the errors", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }
}

