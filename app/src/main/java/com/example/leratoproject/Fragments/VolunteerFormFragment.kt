package com.example.leratoproject.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.leratoproject.Classes.Donation
import com.example.leratoproject.Classes.Volunteer
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentVolunteerFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class VolunteerFormFragment : Fragment() {

    private lateinit var volunteerFormBinding: FragmentVolunteerFormBinding
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        volunteerFormBinding = FragmentVolunteerFormBinding.inflate(layoutInflater,container,false)
        return volunteerFormBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser

        volunteerFormBinding.cancelButton.setOnClickListener {
           // it.findNavController().navigate(R.id.action_volunteerFormFragment_to_volunteer_Fragment)
            Toast.makeText(volunteerFormBinding.root.context,"You have cancelled the process",
                Toast.LENGTH_SHORT).show()
        }
        volunteerFormBinding.volunteerButton.setOnClickListener {

            volunteerFormBinding.emailEditText.setText(user?.email)
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