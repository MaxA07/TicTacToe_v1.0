package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0

    private lateinit var binding : ActivityMainBinding
    var game = Game()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initBoard()
        game.initBoard(binding)
    }



    fun boardTapped(view: View)
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

    private fun match(button: Button, symbol : String): Boolean = button.text == symbol

    private fun result(title: String)
    {
        val message = "\nNoughts $noughtsScore\n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
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

    companion object
    {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

}
