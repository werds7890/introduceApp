package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private val imageArr=arrayOf(
        R.drawable.bear2,
        R.drawable.bear3,
        R.drawable.bear4,
        R.drawable.bear5,
        R.drawable.bear6
    )
    private val randomIndex=Random.nextInt(imageArr.size)
    private val randomImg=imageArr[randomIndex]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val idData=intent.getStringExtra("dataFromId")
        val homeData=findViewById<TextView>(R.id.homeId)
        val homeImage=findViewById<ImageView>(R.id.homeImage)
        homeImage.setImageResource(randomImg)

        homeData.setText("아이디 : ${idData}")

        val closeHome=findViewById<Button>(R.id.close_home)
        closeHome.setOnClickListener {
            finish()
        }
    }
}