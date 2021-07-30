package com.example.recipe_manager.popup

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.recipe_manager.database.Recipe
import com.example.recipe_manager.databinding.RecipesPopupCreateBinding
import com.example.recipe_manager.recyclerView.RecipeViewModel

class RecipeCreate : DialogFragment() {
    companion object{
        const val TAG_DIALOG = "CREATE"
    }

    private lateinit var binding: RecipesPopupCreateBinding
    private val ViewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        Log.d("popupCreate", "create")

        binding = RecipesPopupCreateBinding.inflate(inflater, container, false)

        // Beautiful magic scroll that removes addition background for window UI
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = ViewModel
            popup = this@RecipeCreate

        }

        return binding.root
    }


    fun createRecipe(){
        Log.d("popupCreate", "attempt creating recipe")
        with(binding){
            val name = nameEditText.text.toString()
            val url = urlEditText.text.toString()
            val type = typeSpinner.selectedItem.toString()
            val time = timeSpinner.selectedItem.toString()

            if (URLUtil.isValidUrl(url)) {
                val recipe = Recipe(name = name, url = url, type = type, time = time)
                viewModel?.addRecipe(recipe)
                viewModel?.filterRecipes()

                dismiss()
            }else {
                Toast.makeText(binding.root.context, "Incorrect URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d("popupCreate", "dismiss")
    }



}