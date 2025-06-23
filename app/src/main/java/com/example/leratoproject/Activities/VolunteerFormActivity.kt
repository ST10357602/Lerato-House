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
import com.example.leratoproject.Classes.Volunteer
import com.example.leratoproject.R
import com.example.leratoproject.databinding.ActivityVolunteerFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VolunteerFormActivity : AppCompatActivity() {

    private lateinit var volunteerFormBinding: ActivityVolunteerFormBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        volunteerFormBinding = ActivityVolunteerFormBinding.inflate(layoutInflater)
        setContentView(volunteerFormBinding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser

        volunteerFormBinding.cancelButton.setOnClickListener {
            // it.findNavController().navigate(R.id.action_volunteerFormFragment_to_volunteer_Fragment)
            Toast.makeText(volunteerFormBinding.root.context,"You have cancelled the process",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(this, VolunteerActivity::class.java)
            startActivity(intent)
            finish()
        }
        volunteerFormBinding.emailEditText.setText(user?.email)
        volunteerFormBinding.volunteerButton.setOnClickListener {


            val email = user?.email.toString()
            val userId = user?.uid.toString()
            val name = volunteerFormBinding.nameEditTextText.text.toString()
            val age = volunteerFormBinding.ageInput.text.toString().toString()
            val gender = volunteerFormBinding.genderEditText.text.toString()
            val motivation = volunteerFormBinding.motivationEditText.text.toString()
            val availability = volunteerFormBinding.availabilityEditText.text.toString()

            if(age.isNotEmpty()){
                val newVolunteer = Volunteer(userId,email,name,age.toInt(),gender,motivation,availability)
                saveVolunteer(newVolunteer)
                volunteerFormBinding.nameEditTextText.setText("")
                volunteerFormBinding.emailEditText.setText("")
                volunteerFormBinding.ageInput.setText("")
                volunteerFormBinding.genderEditText.setText("")
                volunteerFormBinding.motivationEditText.setText("")
                volunteerFormBinding.availabilityEditText.setText("")
                Toast.makeText(volunteerFormBinding.root.context, "Welcome to the team", Toast.LENGTH_SHORT).show()
                //it.findNavController().navigate(R.id.action_volunteerFormFragment_to_volunteerThanksFragment)
                val intent = Intent(this, VolunteerThankYou::class.java)
                startActivity(intent)
                finish()

            }
            else {
                Toast.makeText(volunteerFormBinding.root.context, "All fields are required", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun saveVolunteer(newVolunteer: Volunteer) {
        val categoryCollection = db.collection("Volunteer")
        categoryCollection.add(newVolunteer)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG,"Volunteer Added with Id: ${documentReference.id}")
                Toast.makeText(volunteerFormBinding.root.context,"Sign Up Successful",Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG,"Error making donation",e)
                Toast.makeText(volunteerFormBinding.root.context,"An error occurred. Pleas try again later",Toast.LENGTH_SHORT).show()
            }
    }
}