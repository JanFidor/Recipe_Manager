package com.example.recipe_manager.popup

import androidx.fragment.app.DialogFragment

object PopUpFactory {
    fun getPopUp(type: String): DialogFragment?{
        return when(type){
            RecipeCreate.TAG_DIALOG -> RecipeCreate()
            RecipeFilter.TAG_DIALOG -> RecipeFilter()
            RecipeDelete.TAG_DIALOG -> RecipeDelete()
            else -> null
        }
    }
}