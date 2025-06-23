package com.example.leratoproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leratoproject.Activities.AboutActivity
import com.example.leratoproject.Activities.ContactActivity
import com.example.leratoproject.Activities.DonateActivity
import com.example.leratoproject.Activities.VolunteerActivity
import com.example.leratoproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var bottomController: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomController = mainBinding.bottomController
        bottomController.selectedItemId = R.id.home_button
        bottomController.setOnItemSelectedListener { item ->

            when(item.itemId){

                R.id.about_button ->{
                    startActivity(Intent(this, AboutActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.donation_button ->{
                    startActivity(Intent(this, DonateActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.home_button ->{
                    true
                }
                R.id.volunteer_button ->{
                    startActivity(Intent(this, VolunteerActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.contact_button ->{
                    startActivity(Intent(this, ContactActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }

                else -> false
            }

        }

    }
}