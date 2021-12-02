package com.example.s185180lykkehjul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView = findViewById(R.id.gameScoreTextView)

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    internal lateinit var gameScoreTextView: TextView
    internal var score = 0
    


    private fun increaseScore(){
        gameScoreTextView.text = getString(R.string.your_score,score.toString())
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
            increaseScore()
            Toast.makeText(applicationContext, "+200 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 2){
            score += 400
            increaseScore()
            Toast.makeText(applicationContext, "+400 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 3){
            score += 600
            increaseScore()
            Toast.makeText(applicationContext, "+600 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 4){
            score += 800
            increaseScore()
            Toast.makeText(applicationContext, "+800 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 5){
            score += 1000
            increaseScore()
            Toast.makeText(applicationContext, "+1000 points", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 6){
            Toast.makeText(applicationContext, "You lost a life", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 7){
            Toast.makeText(applicationContext, "You got an extra life", Toast.LENGTH_SHORT).show()
        }
        if (diceRoll == 8){
            score = 0
            increaseScore()
            Toast.makeText(applicationContext, "You lost all your points", Toast.LENGTH_SHORT).show()
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
/*
    enum class Possibilities {
        ONE, MISS, EXTRA
    }

    val possibility = Possibilities.ONE


    when (possibility) {
        Possibilities.ONE -> {

        }
        Possibilities.MISS -> {

        }
        Possibilities.EXTRA -> {

        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
}

 */