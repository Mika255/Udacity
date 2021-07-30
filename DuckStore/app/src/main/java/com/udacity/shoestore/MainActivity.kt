package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        Timber.i("setContentView")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.i("fetching NavController")
        val navController = findNavController(R.id.myNavHostFragment)

        Timber.i("fetches AppBarConfiguration")
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // setup action bar using our navcontroller
        Timber.i("setupActionBar")
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)


        // fetches specified view-model "ShoeViewModel", creates it if doesn't exist
        // associates with UI controller "this" Activity
        Timber.i("Called ViewModelProvider for ShoeViewModel")
        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        // create some fake shoes with fake images
        viewModel.addShoe( Shoe("Nike Air", 12.0, "Nike", "This is a very nice shoe!", listOf("PNG")))
        viewModel.addShoe( Shoe("Converse 530", 46.0, "Adidas", "Not so nice shoe!", listOf("PNG")))
        viewModel.addShoe( Shoe("Vans Skate", 22.0, "VAN", "Awesome so nice shoe!", listOf("PNG")))
    }


    // back button should now work
    override fun onSupportNavigateUp(): Boolean {
        Timber.i("onSupportNavigateUp()")
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

}
