package com.example.tictactoe.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.transition.TransitionInflater
import com.example.tictactoe.Game
import com.example.tictactoe.R
import com.example.tictactoe.Turn
import com.example.tictactoe.databinding.FragmentActionBinding

class ActionFragment : Fragment() {

    private lateinit var binding: FragmentActionBinding
    private var game = Game()

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game.initBoard(binding)


        for (button in game.boardList) {
            button.setOnClickListener {
                boardTapped(button)
            }
        }
    }

    private fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)

        if(game.checkForVictory(binding, NOUGHT))
        {
            noughtsScore++
            result("Noughts Win!")
        }
        else if(game.checkForVictory(binding, CROSS))
        {
            crossesScore++
            result("Crosses Win!")
        }

        if(fullBoard())
        {
            result("Draw")
        }

    }


    private fun result(title: String)
    {
        val message = "\nNoughts $noughtsScore\n\nCrosses $crossesScore"

        context?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Reset")
                { _,_ ->
                    resetBoard()
                }
                .setCancelable(false)
                .show()
        }

    }

    private fun resetBoard()
    {
        for(button in game.boardList)
        {
            button.text = ""
        }

        if(firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean
    {
        for(button in game.boardList)
        {
            if(button.text == "")
                return false
        }
        return true
    }


     @SuppressLint("ResourceAsColor")
     private fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currentTurn == Turn.NOUGHT)
        {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }
        else if(currentTurn == Turn.CROSS)
        {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel()
    {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text = turnText

    }

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"

        @JvmStatic
        fun newInstance() = ActionFragment()

    }
}
