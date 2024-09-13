package com.example.addandedit

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addandedit.databinding.FragmentListBinding
class ListFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: UserDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (userViewModel.userList.value.isNullOrEmpty()) {
            userViewModel.addUser(User("Ahmed", "Ahmed123@gmail.com", "011122233455"))
            userViewModel.addUser(User("Ali", "Ali123@gmail.com", "0115347386647"))
            userViewModel.addUser(User("Mostafa", "Mostafa123@gmail.com", "011235182837"))
            userViewModel.addUser(User("Jana", "Jana123@gmail.com", "0117468236543"))
            userViewModel.addUser(User("Ebrahim", "Ebrahim123@gmail.com", "011122233455"))
            userViewModel.addUser(User("Omar", "Omar123@gmail.com", "011122233455"))
        }


        adapter = UserDataAdapter(
            mutableListOf(),
            { user, position ->
                findNavController().navigate(
                    R.id.addEditFragment,
                    bundleOf(
                        "name" to user.name,
                        "mail" to user.mail,
                        "phone" to user.phone,
                        "position" to position
                    )
                )
            },
            { user ->
                userViewModel.removeUser(user)
            }
        )
        binding.myList.layoutManager = LinearLayoutManager(requireContext())
        binding.myList.adapter = adapter

        userViewModel.userList.observe(viewLifecycleOwner) { users ->
            adapter.updateList(users)
        }

        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(R.id.addEditFragment)
        }
    }
}

