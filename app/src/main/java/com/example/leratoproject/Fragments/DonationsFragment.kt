package com.example.leratoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentDonationsBinding


class DonationsFragment : Fragment() {

    private lateinit var donationsBinding: FragmentDonationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        donationsBinding = FragmentDonationsBinding.inflate(layoutInflater,container,false)
        return donationsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donationsBinding.visaButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_donationsFragment_to_eftDonations)
        }
    }


}