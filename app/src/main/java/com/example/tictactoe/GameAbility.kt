package com.example.tictactoe

import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

interface GameAbility {
    fun match(button: Button, symbol : String): Boolean
    fun checkForVictory(binding: ActivityMainBinding, s: String): Boolean
    fun initBoard(binding: ActivityMainBinding)
}