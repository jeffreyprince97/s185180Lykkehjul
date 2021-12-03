package com.example.s185180lykkehjul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import java.lang.StringBuilder
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var gameScoreTextView: TextView
    private var score = 0
    private lateinit var gameLivesTextView: TextView
    private var lives = 5
    private lateinit var wordToGuess: String
    private lateinit var wordTextView: TextView
    private lateinit var underscoreWord: String
    private lateinit var lettersLayout: ConstraintLayout



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
        wordTextView = findViewById(R.id.wordTextView)
        getRandomWord()
        wordTextView.text = underscoreWord
        lettersLayout = findViewById(R.id.lettersLayout)


        val rollButton: Button = findViewById(R.id.button)

        // for at undgå tomme strings indtil man ruller noget, der ændrer på både liv og score
        // opdaterer jeg begge strings til standardværdi på hver klik
        rollButton.setOnClickListener {
            updateScore()
            updateLives()
            rollDice()
        }
    }


    object GameWords {
        val words = listOf(
            "Activity",
            "Fragment",
            "Manifest",
            "Properties"
        )
    }

    // opdaterer scoren i hjørnet
    private fun updateScore(){
        gameScoreTextView.text = getString(R.string.your_score,score.toString())
    }

    // opdaterer antal liv i hjørnet og tjekker om spilleren har 0 liv
    private fun updateLives(){
        gameLivesTextView.text = getString(R.string.your_lives,lives.toString())
        /* if (lives == 0){
            gamelost()
        } */
    }

    // Inspiration er fundet herfra: https://github.com/usmaanz/Hangman
    private fun getRandomWord(){
        val randomIndex = Random.nextInt(0,GameWords.words.size)
        wordToGuess = GameWords.words[randomIndex]
        generateHiddenWord(wordToGuess)
    }

    // Inspiration er fundet herfra: https://github.com/usmaanz/Hangman
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


        // teksten under Spin-knappen, som fortæller hvad man har rullet.
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()



        if (diceRoll == 1){
            score += 200
            updateScore()
            resultTextView.text = "+200 points"
        }
        if (diceRoll == 2){
            score += 400
            updateScore()
            resultTextView.text = "+400 points"
        }
        if (diceRoll == 3){
            score += 600
            updateScore()
            resultTextView.text = "+600 points"
        }
        if (diceRoll == 4){
            score += 800
            updateScore()
            resultTextView.text = "+800 points"
        }
        if (diceRoll == 5){
            score += 1000
            updateScore()
            resultTextView.text = "+1000 points"
        }
        if (diceRoll == 6){
            lives --
            updateLives()
            resultTextView.text = "You lost a life"
        }
        if (diceRoll == 7){
            lives ++
            updateLives()
            resultTextView.text = "You got an extra life"
        }
        if (diceRoll == 8){
            score = 0
            updateScore()
            resultTextView.text = "You lost all your points"
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
