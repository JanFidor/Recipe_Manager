package com.example.recipe_manager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 2)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO

    companion object{
        private var INSTANCE: RecipeDataBase? = null

        fun getDatabase(context: Context): RecipeDataBase? {
            if (INSTANCE == null) {
                synchronized(RecipeDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RecipeDataBase::class.java, "recipe"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}
