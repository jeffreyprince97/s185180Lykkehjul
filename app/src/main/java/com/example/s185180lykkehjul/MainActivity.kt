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

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val dice = Dice(3)
        val diceRoll = dice.roll()


        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()

        if (diceRoll == 1){
            val toast = Toast.makeText(applicationContext, "+100 points", Toast.LENGTH_SHORT)
            toast.show()
        }
        if (diceRoll == 2){
            val toast = Toast.makeText(applicationContext, "You lost a life", Toast.LENGTH_SHORT)
            toast.show()
        }
        if (diceRoll == 3){
            val toast = Toast.makeText(applicationContext, "Extra turn", Toast.LENGTH_SHORT)
            toast.show()
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