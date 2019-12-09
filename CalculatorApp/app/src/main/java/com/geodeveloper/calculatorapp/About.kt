package com.geodeveloper.calculatorapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        twitter_icon.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://twitter.com/ejilolahmed?s=09")
            startActivity(openURL)
        }

        whatsapp_icon.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://wa.me/2348080357062")
            startActivity(openURL)
        }
        insta_icon.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://wwww.instagram.com/ejilola_hammed_ejitomiwa")
            startActivity(openURL)
        }

    }



}
