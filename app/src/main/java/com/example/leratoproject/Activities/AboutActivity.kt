package com.example.leratoproject.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leratoproject.MainActivity
import com.example.leratoproject.R
import com.example.leratoproject.databinding.ActivityAboutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutActivity : AppCompatActivity() {

    private lateinit var aboutBinding: ActivityAboutBinding
    private lateinit var bottomController: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomController = aboutBinding.bottomController
        bottomController.selectedItemId = R.id.about_button
        bottomController.setOnItemSelectedListener { item ->

            when(item.itemId){

                R.id.about_button ->{

                    true
                }
                R.id.donation_button ->{
                    startActivity(Intent(this, DonateActivity::class.java))
                    overridePendingTransition(100,0)
                    true
                }
                R.id.home_button ->{
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(100,0)
                    true
                }
                R.id.volunteer_button ->{
                    startActivity(Intent(this, VolunteerActivity::class.java))
                    overridePendingTransition(100,0)
                    true
                }
                R.id.contact_button ->{
                    startActivity(Intent(this, ContactActivity::class.java))
                    overridePendingTransition(100,0)
                    true
                }

                else -> false
            }

        }
    }
}