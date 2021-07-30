package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber


/**
 * Login screen
 */
class LoginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.i("Binding Login Fragment")
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        Timber.i("Setting up navigation onClickListener")
        binding.existloginButton.setOnClickListener{v: View ->
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        binding.newloginButton.setOnClickListener{v: View ->
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }


        return binding.root
    }


}