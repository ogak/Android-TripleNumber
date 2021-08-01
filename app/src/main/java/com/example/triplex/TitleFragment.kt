package com.example.triplex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.triplex.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.playButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

        binding.aboutButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_titleFragment_to_aboutFragment)
        }

        return view
    }

}