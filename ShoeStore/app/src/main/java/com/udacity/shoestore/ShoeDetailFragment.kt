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
        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        // set current shoe from view model - would also work, why use data binding?
        //binding.shoeNameEdit.setText( viewModel.current_shoe.value?.name)
        //binding.shoeSizeEdit.setText( viewModel.current_shoe.value?.size.toString())
        //binding.shoeCompanyEdit.setText( viewModel.current_shoe.value?.company)
        //binding.shoeDescEdit.setText( viewModel.current_shoe.value?.description)


        // if SAVE is pressed: create new shoe if needed and go back to list
        binding.saveButton.setOnClickListener { v: View ->
            Timber.i("Saving new shoe...")
            Timber.i("....name: ${binding.shoeNameEdit.text}")
            Timber.i("....name vm: ${viewModel.current_shoe.value?.name}")
            Timber.i("....size: ${binding.shoeSizeEdit.text}")
            Timber.i("....size vm: ${viewModel.current_shoe.value?.size}")
            Timber.i("....company: ${binding.shoeCompanyEdit.text}")
            Timber.i("....company vm: ${viewModel.current_shoe.value?.company}")
            Timber.i("....desc: ${binding.shoeDescEdit.text}")
            Timber.i("....desc vm: ${viewModel.current_shoe.value?.description}")

            // if it's a new shoe, adds shoe to view model
            if (viewModel.isShoeNew) {
                viewModel.addShoe(
                    Shoe(
                        viewModel.current_shoe.value!!.name,
                        viewModel.current_shoe.value!!.size,
                        viewModel.current_shoe.value!!.company,
                        viewModel.current_shoe.value!!.description,
                        listOf("IMG")
                    )
                )
            }


            // go back to list
            v.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }



        return binding.root
    }


}