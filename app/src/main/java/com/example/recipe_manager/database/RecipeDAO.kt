package com.example.recipe_manager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDAO {
    @Insert
    fun createRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipe WHERE :type IN (type, :any) AND :time IN (time, :any)")
    fun get(type: String, time: String, any: String ="Any"): List<Recipe>




}