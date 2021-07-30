package com.example.recipe_manager.recyclerView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipe_manager.database.Recipe
import com.example.recipe_manager.database.RecipeRepository
import kotlin.random.Random

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val _repository = RecipeRepository(application)
    val recipes = _repository.allRecipes

    val v = Random.nextInt()

    private val _type = MutableLiveData<String>("Any")
    val type: LiveData<String>
        get() = _type

    private val  _time = MutableLiveData<String>("Any")
    val time: LiveData<String>
        get() = _time


    fun filterRecipes() = _repository.filter(_type.value ?: "Any", _time.value ?: "Any")
    fun addRecipe(recipe: Recipe) {
        _repository.insert(recipe)
        filterRecipes()
    }
    fun deleteRecipe(recipe: Recipe) {
        _repository.delete(recipe)
        filterRecipes()
    }

    fun setFilter(type: String, time: String){
        if (_type.value != type || _time.value != time) {
            _type.value = type
            _time.value = time
            filterRecipes()
        }
    }
}