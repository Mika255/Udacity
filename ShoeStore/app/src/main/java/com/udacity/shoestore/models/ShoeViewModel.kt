package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeViewModel : ViewModel() {


    // list of shoes
    val shoesList = mutableListOf<Shoe>()
    val _shoesListLiveData = MutableLiveData<List<Shoe>>()

    val shoesListLiveData: LiveData<List<Shoe>>
        get() = _shoesListLiveData

    // The current Shoe
    private val _current_shoe = MutableLiveData<Shoe>()
    val current_shoe: LiveData<Shoe>
        get() = _current_shoe


    // adds shoes and triggers all observer
    fun addShoe( shoe: Shoe ) {
        shoesList.add( shoe)
        _shoesListLiveData.value = shoesList
    }


    fun setCurrentShoe( cshoe: Shoe ) {
        Timber.i("Set current shoe to ${cshoe.name}")
        _current_shoe.value = cshoe
    }

    init {
        Timber.i("Init of ShoeViewModel")

        // init current shoe with default values
        _current_shoe.value = Shoe( "NO", 0.0, "NO", "NO", listOf("NO"))
    }
}