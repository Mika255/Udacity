package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber


/**
 * TODO list
 * DONE - All the layouts are missing code comments to justify the usage of Linear/ConstraintLayout
 * DONE - After log out from the log-in page user can go back to the list page.
 * DONE - From shoe detail page if user press cancel and then from list page press back button it goes back to the Shoe detail page
 * DONE - Nested layouts used in various layout files
 * DONE - You have many nested layouts in fragment_shoe_detail, try to flatten the UI. You can simply use the Constraint layout to arrange all the elements. Learn here, How layouts impact the performance of the application.
 * DONE - For saving shoe details you should not access the text property using EditText. You can use the `ViewModel's property to save the shoe details.
 * DONE - You can access the dimension values from your code like
 * DONE - You can use Kotlin scope functions to reduce the repetition and write more concise code
 * DONE - You can extract these values to res/values/dimens.xml to re-use across all the layouts
 * DONE - You can use FragmentContainerView because fragment is deprecated
 *
 */

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
        // from https://knowledge.udacity.com/questions/523342
        // from https://knowledge.udacity.com/questions/632572
        val navHostFragment = supportFragmentManager .findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

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
