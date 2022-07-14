package com.example.tictactoe

import android.widget.Button
import com.example.tictactoe.databinding.FragmentActionBinding

interface GameAbility {
    fun match(button: Button, symbol : String): Boolean
    fun checkForVictory(binding: FragmentActionBinding, s: String): Boolean
    fun initBoard(binding: FragmentActionBinding)
}