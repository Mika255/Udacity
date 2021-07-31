package com.udacity.shoestore

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import kotlinx.android.synthetic.main.fragment_shoe_list.view.*
//import kotlinx.android.synthetic.main.fragment_shoe_list.view.*
import timber.log.Timber


/**
 * Shoe list which displays all shoes in view model
 */
class ShoeListFragment : Fragment() {

    // Get a reference to the ViewModel scoped to its Activity
    val viewModel by activityViewModels<ShoeViewModel>()

    // stores binding to xml
    private lateinit var binding: FragmentShoeListBinding


    /**
     * Creates at runtime shoe list based on ViewModel
     */
    fun createShoeList() {
        for (shoe in viewModel.shoesList) {
            Timber.i("Creating entry for Shoe ${shoe.name}")

            // fetches default padding from resources and converts to INT
            val p = getResources().getDimension(R.dimen.padding_big).toInt()
            val p2 = getResources().getDimension(R.dimen.padding_default).toInt()

            // hlayout - without apply
            // val hLayout = LinearLayout(activity)
            // hLayout.orientation = HORIZONTAL
            // hLayout.setPadding(p, p, p, p)
            // hLayout.setBackgroundResource(R.color.colorList)
            // hLayout.setLayoutParams(
            //     LinearLayout.LayoutParams(
            //       LinearLayout.LayoutParams.MATCH_PARENT,
            //       LinearLayout.LayoutParams.MATCH_PARENT
            //   )
            // )

            // create horizontal layout for one single shoe
            val hLayout = LinearLayout(activity).apply {
                orientation = HORIZONTAL
                setPadding(p, p, p, p)
                setBackgroundResource(R.color.colorList)
                setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                )
            }

            // show shoe icon inside horizontal layout
            val myButton = FloatingActionButton(activity).apply {
                setImageResource(R.drawable.ic_launcher_foreground)
                contentDescription = shoe.name
                setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                )
            }

            // show shoe name inside horizontal layout
            val myText = TextView(activity).apply {
                text = shoe.name
                gravity = Gravity.CENTER_VERTICAL
                setPadding(p2, 0, 0, 0)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.name_text_size)
                )
                setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                )
            }


            Timber.i("Setting up shoeList onClickListener for ${shoe.name}")
            hLayout.setOnClickListener { v: View ->

                // set selected shoe
                viewModel.setCurrentShoe(shoe)

                // navigate to detail
                v.findNavController()
                    .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
            }

            // adds text+button to layout
            hLayout.addView(myButton)
            hLayout.addView(myText)

            binding.shoelistLayout.addView(hLayout)

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.i("Binding Shoe List Fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // set up on click listener for adding a new shoe
        binding.shoelistLayout.shoe_add_layout.setOnClickListener { v: View ->
            viewModel.setCurrentShoe(Shoe("", 0.0, "", "", listOf<String>()))
            v.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        // creating dynamic shoe list below add button
        Timber.i("Creating dynamic shoe list")
        createShoeList()


        // this fragment has now a menu
        setHasOptionsMenu(true)


        return binding.root
    }

    // MENU Logout - goes back to login screen and pops the history
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_menu) {
            Timber.i("Found logout menu, navigating back to login.")
            NavHostFragment.findNavController(this)
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // inflates options menu containing logout
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

}