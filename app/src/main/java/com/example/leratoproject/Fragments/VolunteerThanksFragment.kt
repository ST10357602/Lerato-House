package com.example.leratoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentVolunteerThanksBinding


class VolunteerThanksFragment : Fragment() {

    private lateinit var volunteerThanksBinding: FragmentVolunteerThanksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        volunteerThanksBinding = FragmentVolunteerThanksBinding.inflate(layoutInflater,container,false)
        return volunteerThanksBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        volunteerThanksBinding.backToHome.setOnClickListener {
            //it.findNavController().navigate(R.id.action_volunteerThanksFragment_to_volunteer_Fragment)
        }

    }
}