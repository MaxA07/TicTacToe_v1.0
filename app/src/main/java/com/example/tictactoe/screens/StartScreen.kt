package com.example.tictactoe.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentStartScreenBinding

class StartScreen : Fragment() {

    private lateinit var binding: FragmentStartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartScreenBinding.inflate(layoutInflater, container, false)

        binding.startGame.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.slide_in, R.anim.slide_out)
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