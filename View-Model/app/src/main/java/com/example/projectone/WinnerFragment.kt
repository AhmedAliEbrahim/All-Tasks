package com.example.projectone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.databinding.FragmentWinnerBinding

class WinnerFragment : Fragment() {

    private var _binding: FragmentWinnerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ScoreViewModel::class.java)

        val winner = viewModel.getWinner()
        binding.textWinner.text = "Winner: $winner"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
