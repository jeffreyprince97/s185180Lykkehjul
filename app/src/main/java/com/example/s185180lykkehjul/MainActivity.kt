package com.example.s185180lykkehjul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import java.lang.StringBuilder
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*private fun gamewon(){
         findNavController(R.id.nav_host_fragment)
             .navigate(R.id.gamewonfragment)
         } */

       /*private fun gamelost(){
        findNavController(R.id.nav_host_fragment)
            .navigate(R.id.gamelostfragment)
        } */

        gameScoreTextView = findViewById(R.id.gameScoreTextView)
        gameLivesTextView = findViewById(R.id.gameLivesTextView)

        val rollButton: Button = findViewById(R.id.button)


        rollButton.setOnClickListener {
            updateScore()
            updateLives()
            rollDice()
        }
    }

    internal lateinit var gameScoreTextView: TextView
    internal var score = 0
    internal lateinit var gameLivesTextView: TextView
    internal var lives = 5
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String

    object GameWords {
        val words = listOf(
            "Activity",
            "Fragment",
            "Manifest",
            "Properties"
        )
    }

    private fun updateScore(){
        gameScoreTextView.text = getString(R.string.your_score,score.toString())
    }

    private fun updateLives(){
        gameLivesTextView.text = getString(R.string.your_lives,lives.toString())
        /* if (lives == 0){
            gamelost()
        } */
    }

    private fun getRandomWord(){
        val randomIndex = Random.nextInt(0,GameWords.words.size)
        wordToGuess = GameWords.words[randomIndex]
        generateHiddenWord(wordToGuess)
    }

    private fun generateHiddenWord(word: String){
        val sb = StringBuilder()
        word.forEach {
            char -> if(char == '/') {
                sb.append('/')
        } else {
            sb.append("_")
        }
        }
        underscoreWord = sb.toString()
    }

    // baseret på terning-appen har jeg hardcoded udfaldene og givet dem funktioner.
    private fun rollDice() {
        val dice = Dice(8)
        val diceRoll = dice.roll()


        // ??
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()

        if (diceRoll == 1){
            score += 200
            updateScore()
            Toast.makeText(applicationContext, "+200 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 2){
            score += 400
            updateScore()
            Toast.makeText(applicationContext, "+400 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 3){
            score += 600
            updateScore()
            Toast.makeText(applicationContext, "+600 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 4){
            score += 800
            updateScore()
            Toast.makeText(applicationContext, "+800 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 5){
            score += 1000
            updateScore()
            Toast.makeText(applicationContext, "+1000 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 6){
            lives --
            updateLives()
            Toast.makeText(applicationContext, "You lost a life", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 7){
            lives ++
            updateLives()
            Toast.makeText(applicationContext, "You got an extra life", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 8){
            score = 0
            updateScore()
            Toast.makeText(applicationContext, "You lost all your points", Toast.LENGTH_SHORT).show()
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
