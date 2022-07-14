package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.screens.StartScreen

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment(StartScreen.newInstance(), R.id.fragmentContainer)
    }

    private fun initFragment(fragment: Fragment, placeHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .add(placeHolder, fragment)
            .commit()
    }
}
