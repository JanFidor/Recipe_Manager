package com.example.recipe_manager.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room


class RecipeRepository(application: Application) {

    // gives weird error
    //val db: RecipeDataBase = RecipeDataBase.getDatabase(application)!!

    private val db = Room.databaseBuilder(application, RecipeDataBase::class.java, "recipe")
    .allowMainThreadQueries()
    .build()

    private val _recipeDao: RecipeDAO = db.recipeDao()
    private val _allRecipes = MutableLiveData<List<Recipe>> (_recipeDao.getAll())
    val allRecipes: LiveData<List<Recipe>>
        get() =_allRecipes

    fun filter(type: String, time: String){
        _allRecipes.value = _recipeDao.get(type, time)
    }
    fun insert(recipe: Recipe) = _recipeDao.createRecipe(recipe)
    fun delete(recipe: Recipe) = _recipeDao.deleteRecipe(recipe)

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    //fun insert(word: Word?) = WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.insert(word) }
}