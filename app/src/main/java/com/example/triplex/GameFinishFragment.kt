package com.example.triplex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.triplex.databinding.FragmentGameFinishBinding


class GameFinishFragment : Fragment() {
    private var _binding: FragmentGameFinishBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.playAgainBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.gameFragment)
        }

        binding.returnBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_gameFinishFragment_to_titleFragment)
        }
        return view
    }


}