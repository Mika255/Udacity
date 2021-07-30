package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber


/**
 * Instruction screen
 */
class InstructionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.i("Binding Instruction Fragment")
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)

        Timber.i("Setting up navigation onClickListener")
        binding.instructionsButton.setOnClickListener{v: View ->
            v.findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
        }

        return binding.root

    }

}