package com.example.recipe_manager.popup

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.recipe_manager.databinding.RecipesPopupFilterBinding
import com.example.recipe_manager.recyclerView.RecipeViewModel

class RecipeFilter : DialogFragment() {
    companion object{
        const val TAG_DIALOG = "FILTER"
    }

    private lateinit var binding: RecipesPopupFilterBinding
    private val viewmodel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = RecipesPopupFilterBinding.inflate(inflater, container, false)

        // Beautiful magic scroll that removes addition background for window UI
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewmodel
            popup = this@RecipeFilter
        }

        view
        return binding.root
    }


    fun filter(){
        with(binding){
            val type = spinnerTaste.selectedItem.toString()
            val time = spinnerType.selectedItem.toString()
            viewModel?.setFilter(type, time)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        filter()
        Toast.makeText(binding.root.context, "${viewmodel.type.value} ${viewmodel.time.value} ", Toast.LENGTH_SHORT).show()
    }

}