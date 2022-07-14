package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.AnimRes
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
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.slide_in, R.anim.slide_out)
            .add(placeHolder, fragment)
            .commit()



    }
}
