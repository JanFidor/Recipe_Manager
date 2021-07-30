package com.example.recipe_manager.recyclerView

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_manager.database.Recipe
import com.example.recipe_manager.databinding.ViewRecipeBinding
import com.example.recipe_manager.popup.PopUpFactory
import com.example.recipe_manager.popup.RecipeDelete


class RecipeListAdapter(private var items: List<Recipe>, val activity: FragmentActivity) : RecyclerView.Adapter<RecipeListAdapter.RecipeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewRecipeBinding.inflate(inflater, parent, false)
        return RecipeHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int)  = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun reload(recipes: List<Recipe>) {
        items = recipes
        notifyDataSetChanged()
        notifyItemRangeChanged(0, items.size)
    }

    inner class RecipeHolder(private val binding: ViewRecipeBinding, val a: FragmentActivity) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            with(binding){
                recipeModel = recipe
                recipeHolder = this@RecipeHolder
                cardLayout.setOnLongClickListener { openWindow() }
            }
        }
        private fun openWindow(): Boolean{
            val newDialogFragment = PopUpFactory.getPopUp(RecipeDelete.TAG_DIALOG)
            val fm = a.supportFragmentManager
            with(newDialogFragment as RecipeDelete) {
                arguments
                setRecipe(binding.recipeModel)
                show(fm, RecipeDelete.TAG_DIALOG)
            }
            return true
        }
        fun shareRecipe(recipe: Recipe){
            val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "TODO")
                .putExtra(Intent.EXTRA_TEXT, "TODO")
            binding.root.context.startActivity(intent)
        }
        fun getRecipe(recipe: Recipe){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.url))
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            a.startActivity(intent);
        }

    }

}
