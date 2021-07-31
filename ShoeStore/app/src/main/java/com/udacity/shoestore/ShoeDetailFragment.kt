package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

/**
 * Shoe Detail Screen where new shoes can be created
 */
class ShoeDetailFragment : Fragment() {

    // Get a reference to the ViewModel scoped to its Activity
    val viewModel by activityViewModels<ShoeViewModel>()

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.i("Binding Shoe Detail Fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        // data binding
        binding.vm = viewModel

        Timber.i("Setting up navigation onClickListener")

        // cancel goes back to list without saving
        binding.cancelButton.setOnClickListener{v: View ->
            v.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        // set current shoe from view model - would also work, why use data binding?
        //binding.shoeNameEdit.setText( viewModel.current_shoe.value?.name)
        //binding.shoeSizeEdit.setText( viewModel.current_shoe.value?.size.toString())
        //binding.shoeCompanyEdit.setText( viewModel.current_shoe.value?.company)
        //binding.shoeDescEdit.setText( viewModel.current_shoe.value?.description)


        // this saves all information about the shoe
        binding.saveButton.setOnClickListener{v: View ->
            Timber.i("Saving new shoe...")
            Timber.i("....name: ${binding.shoeNameEdit.text}")
            Timber.i("....name: ${viewModel.current_shoe.value?.name}")
            Timber.i("....size: ${binding.shoeSizeEdit.text}")
            Timber.i("....size: ${viewModel.current_shoe.value?.size}")
            Timber.i("....company: ${binding.shoeCompanyEdit.text}")
            Timber.i("....company: ${viewModel.current_shoe.value?.company}")
            Timber.i("....desc: ${binding.shoeDescEdit.text}")
            Timber.i("....desc: ${viewModel.current_shoe.value?.description}")

            // adds shoe to view model
/*
            viewModel.addShoe(
                Shoe(
                    viewModel.current_shoe.value.name

                    ))
*/
/*
            viewModel.addShoe(
                Shoe(
                    binding.shoeNameEdit.text.toString(),
                    binding.shoeSizeEdit.text.toString().toDouble(),
                    binding.shoeCompanyEdit.text.toString(),
                    binding.shoeDescEdit.text.toString(),
                    listOf("IMG"))

            )
*/

            // go back to list
            v.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }



        return binding.root
    }


}