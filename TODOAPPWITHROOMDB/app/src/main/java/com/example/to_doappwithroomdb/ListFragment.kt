package com.example.to_doappwithroomdb


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_doappwithroomdb.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: TaskDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TaskDataAdapter(
            mutableListOf(),
            { task, position ->
                findNavController().navigate(
                    R.id.addEditFragment,
                    bundleOf(
                        "id" to task.id,
                        "title" to task.title,
                        "description" to task.description
                    )
                )
            },
            { task ->
                taskViewModel.removeTask(task)
            }
        )
        binding.myList.layoutManager = LinearLayoutManager(requireContext())
        binding.myList.adapter = adapter

        taskViewModel.taskList.observe(viewLifecycleOwner) { tasks ->
            adapter.updateList(tasks)
        }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.addEditFragment)
        }
    }
}