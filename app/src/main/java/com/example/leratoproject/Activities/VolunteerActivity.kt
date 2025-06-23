package com.example.leratoproject.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leratoproject.MainActivity
import com.example.leratoproject.R
import com.example.leratoproject.databinding.ActivityVolunteerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class VolunteerActivity : AppCompatActivity() {

    private lateinit var volunteerBinding: ActivityVolunteerBinding
    private lateinit var bottomController: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        volunteerBinding = ActivityVolunteerBinding.inflate(layoutInflater)
        setContentView(volunteerBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomController = volunteerBinding.bottomController
        bottomController.selectedItemId = R.id.volunteer_button
        bottomController.setOnItemSelectedListener { item ->

            when(item.itemId){

                R.id.about_button ->{
                    startActivity(Intent(this, AboutActivity::class.java))
                    overridePendingTransition(100,0)
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
        volunteerBinding.volunteerButton.setOnClickListener {
            val intent = Intent(this, VolunteerFormActivity::class.java)
            startActivity(intent)
        }


    }
}