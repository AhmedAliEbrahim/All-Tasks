package com.example.projectone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projectone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ScoreViewModel::class.java)

        viewModel.teamAScore.observe(viewLifecycleOwner) { score ->
            binding.textTeamA.text = "Team A: $score"
        }

        viewModel.teamBScore.observe(viewLifecycleOwner) { score ->
            binding.textTeamB.text = "Team B: $score"
        }

        binding.buttonIncreaseTeamA.setOnClickListener {
            viewModel.increaseScoreForTeamA()
        }

        binding.buttonIncreaseTeamB.setOnClickListener {
            viewModel.increaseScoreForTeamB()
        }

        binding.buttonShowWinner.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_winnerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
