package com.example.recipe_manager.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.recipe_manager.database.Recipe
import com.example.recipe_manager.databinding.RecipesPopupDeleteBinding
import com.example.recipe_manager.recyclerView.RecipeViewModel

class RecipeDelete() : DialogFragment() {

    companion object{
        const val TAG_DIALOG = "DELETE"
    }

    private lateinit var binding: RecipesPopupDeleteBinding
    private val viewmodel: RecipeViewModel by activityViewModels()
    private var _recipe: Recipe? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = RecipesPopupDeleteBinding.inflate(inflater, container, false)

        // Beautiful magic scroll that removes addition background for window UI
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewmodel
            popup = this@RecipeDelete
        }
        return binding.root
    }
    fun setRecipe(recipe: Recipe?){
        _recipe = recipe
    }

    fun deleteRecipe(){
         _recipe?.let{viewmodel.deleteRecipe(it)}
         dismiss()
    }
}