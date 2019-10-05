package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var currentPlayer = "x"
    private var gameState = "playing"
    private lateinit var allFields: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allFields = arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)

        for(field in allFields){
            field.setOnClickListener{onFieldClick(it as TextView)
            }
        }
    }
    private fun onFieldClick(field: TextView){

        if(gameState != "playing" ){
            resetGame()
            return
        }
        if(field.text == "" && gameState == "playing") {
            field.text = currentPlayer

            if(checkWin()){
                statusText.text = "Spieler $currentPlayer hat gewonnen"
                gameState = "won"
            }else if(allFields.all{it.text !=""}){
                statusText.text = "Unentschieden"
                gameState = "tie"
            }
            else {
                currentPlayer = if (currentPlayer == "x") "o" else "x"
                statusText.text = " Spieler $currentPlayer ist an der Reihe"
            }
        }

    }

    private fun resetGame(){
        currentPlayer = "x"
        statusText.text = "Spieler $currentPlayer ist an der Reihe"
        gameState = "playing"

        for(field in allFields){
            field.text = ""
        }


    }
    private fun checkWin():Boolean{
        val horizontal =  (f0.text == f1.text && f1.text == f2.text && f0.text != "")
                || (f3.text == f4.text && f4.text == f5.text && f3.text != "")
                ||(f6.text == f7.text && f7.text == f8.text && f6.text != "")

        return horizontal
    }
}
