package com.example.leratoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentVolunteerBinding


class Volunteer_Fragment : Fragment() {

    private lateinit var volunteerBinding: FragmentVolunteerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        volunteerBinding = FragmentVolunteerBinding.inflate(layoutInflater,container,false)
        return volunteerBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        volunteerBinding.volunteerButton.setOnClickListener {
           // it.findNavController().navigate(R.id.action_volunteer_Fragment_to_volunteerFormFragment)
        }
    }

}