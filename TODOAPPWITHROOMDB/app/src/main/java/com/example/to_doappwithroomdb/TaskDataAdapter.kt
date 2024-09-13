package com.example.to_doappwithroomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doappwithroomdb.databinding.ItemTaskBinding

class TaskDataAdapter(
    private val tasks: MutableList<Task>,
    private val onEditClick: (Task, Int) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskDataAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], position)
    }

    override fun getItemCount() = tasks.size

    fun updateList(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, position: Int) {
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description

            binding.editButton.setOnClickListener {
                onEditClick(task, position)
            }

            binding.removeButton.setOnClickListener {
                onDeleteClick(task)
            }
        }
    }
}
