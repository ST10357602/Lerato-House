package com.example.leratoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentThankYouDonationBinding
import kotlin.random.Random


class ThankYouDonation : Fragment() {

    private lateinit var thankYouDonationBinding: FragmentThankYouDonationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        thankYouDonationBinding = FragmentThankYouDonationBinding.inflate(layoutInflater,container,false)
        return thankYouDonationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thankYouDonationBinding.donationButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_thankYouDonation_to_donationsFragment)
        }
        thankYouDonationBinding.newVisaButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_thankYouDonation_to_eftDonations)
        }
    }
}