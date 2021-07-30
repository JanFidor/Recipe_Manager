package com.example.recipe_manager.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe_manager.database.RecipeDataBase
import com.example.recipe_manager.databinding.FragmentRecipeBinding
import com.example.recipe_manager.popup.PopUpFactory
import com.example.recipe_manager.popup.RecipeCreate

class RecipeFragment : Fragment(){
    private lateinit var binding: FragmentRecipeBinding

    private val viewModel: RecipeViewModel by activityViewModels()
    lateinit var database: RecipeDataBase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {

        //viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            recipeFragment = this@RecipeFragment
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        val adapter = RecipeListAdapter(viewModel.recipes.value ?: listOf(), requireActivity())
        recyclerView.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) {recipes ->
            adapter.reload(recipes ?: listOf())
        }

    }
    fun addRecipe(){
        val newDialogFragment = PopUpFactory.getPopUp(RecipeCreate.TAG_DIALOG)
        newDialogFragment?.show(parentFragmentManager, RecipeCreate.TAG_DIALOG)
        //findNavController().navigate(R.id.action_recipeFragment_to_recipeCreate)
    }

}
