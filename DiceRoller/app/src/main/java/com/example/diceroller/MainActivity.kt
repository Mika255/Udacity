package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rollButton : Button = findViewById( R.id.roll_button)
        rollButton.text = "Let's roll!"
        rollButton.setOnClickListener{
            rollDice()
        }

        diceImage = findViewById(R.id.dice_image)

    }

    private fun rollDice() {
        val ran = Random.Default.nextInt(6) + 1
        val drawableRes =  when( ran ) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            2 -> R.drawable.dice_3
            2 -> R.drawable.dice_4
            2 -> R.drawable.dice_5
            2 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource( drawableRes)
    }
}