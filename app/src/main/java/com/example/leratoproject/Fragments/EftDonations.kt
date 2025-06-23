package com.example.leratoproject.Fragments

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.leratoproject.Classes.Donation
import com.example.leratoproject.R
import com.example.leratoproject.databinding.FragmentDonationsBinding
import com.example.leratoproject.databinding.FragmentEftDonationsBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate


class EftDonations : Fragment() {

    private lateinit var eftDonationsBinding: FragmentEftDonationsBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        eftDonationsBinding = FragmentEftDonationsBinding.inflate(layoutInflater,container,false)
        return eftDonationsBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        eftDonationsBinding.donateButton.setOnClickListener {

            val email = eftDonationsBinding.emailEditText.text.toString()
            val name = eftDonationsBinding.nameEditTextText.text.toString()
            val amount = eftDonationsBinding.amountEditText.text.toString()
            val cardNumber = eftDonationsBinding.cardNumber.text.toString()
            val date = LocalDate.now()

            if(amount.isEmpty() && cardNumber.isEmpty()){
                Toast.makeText(eftDonationsBinding.root.context,"All fields are required", Toast.LENGTH_SHORT).show()
            }
            else{
                val donation = Donation(name,email,cardNumber,amount.toDouble(),date.toString())
                saveDonation(donation)
                eftDonationsBinding.nameEditTextText.setText("")
                eftDonationsBinding.emailEditText.setText("")
                eftDonationsBinding.cardNumber.setText("")
                eftDonationsBinding.cvvEditText.setText("")
                eftDonationsBinding.expiryEditText.setText("")
                eftDonationsBinding.amountEditText.setText("")
                it.findNavController().navigate(R.id.action_eftDonations_to_thankYouDonation)
            }

        }

    }

    private fun saveDonation(newDonation: Donation) {
        val categoryCollection = db.collection("EFT Donations")
        categoryCollection.add(newDonation)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG,"Donation Added wi Id: ${documentReference.id}")
                Toast.makeText(eftDonationsBinding.root.context,"Donation Successful",Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG,"Error making donation",e)
                Toast.makeText(eftDonationsBinding.root.context,"Donation Failed",Toast.LENGTH_SHORT).show()
            }
    }

}