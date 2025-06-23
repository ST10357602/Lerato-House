package com.example.leratoproject.Activities

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leratoproject.Classes.Message
import com.example.leratoproject.Classes.Volunteer
import com.example.leratoproject.MainActivity
import com.example.leratoproject.R
import com.example.leratoproject.databinding.ActivityContactBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ContactActivity : AppCompatActivity() {

    private lateinit var contactBinding: ActivityContactBinding
    private lateinit var bottomController: BottomNavigationView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        contactBinding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(contactBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        contactBinding.emailEditText.setText(user?.email)

        contactBinding.signUpButton.setOnClickListener {
            val email = contactBinding.emailEditText.text.toString()
            val name = contactBinding.nameEditText.text.toString()
            val message = contactBinding.passwordEditText.text.toString()

            if(email.isNotEmpty() && name.isNotEmpty() && message.isNotEmpty()){
                val newMessage = Message(user?.uid.toString(),email,name,message)
                Toast.makeText(this,"Sending Message...", Toast.LENGTH_SHORT).show()
                saveMessage(newMessage)
                contactBinding.emailEditText.setText("")
                contactBinding.passwordEditText.setText("")
                contactBinding.nameEditText.setText("")
            }else{
                Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show()
            }

        }

        bottomController = contactBinding.bottomController
        bottomController.selectedItemId = R.id.contact_button
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
                    startActivity(Intent(this, VolunteerActivity::class.java))
                    overridePendingTransition(100,0)
                    true
                }
                R.id.contact_button ->{

                    true
                }

                else -> false
            }

        }

    }
    private fun saveMessage(newMessage: Message) {
        val categoryCollection = db.collection("Contact Message")
        categoryCollection.add(newMessage)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG,"Contact Message Added with Id: ${documentReference.id}")
                Toast.makeText(this,"Message Sent Successfully",Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG,"Error making donation",e)
                Toast.makeText(this,"An error occurred. Pleas try again later",Toast.LENGTH_SHORT).show()
            }
    }
}