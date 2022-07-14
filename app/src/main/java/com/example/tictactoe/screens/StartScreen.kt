package com.example.tictactoe.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentStartScreenBinding

class StartScreen : Fragment() {
    private lateinit var binding: FragmentStartScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartScreenBinding.inflate(layoutInflater, container, false)

        binding.startGame.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, ActionFragment.newInstance())
                .commit()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartScreen()
    }
}