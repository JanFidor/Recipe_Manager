package com.example.recipe_manager

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.recipe_manager.database.RecipeDataBase
import com.example.recipe_manager.popup.PopUpFactory
import com.example.recipe_manager.popup.RecipeFilter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var navController: NavController? = null
    lateinit var dataBase: RecipeDataBase
    val context: Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // show 'UP' button, when not top fragment
        // change app title on app bar to fragment label
        //setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.filter -> {
                val newDialogFragment = PopUpFactory.getPopUp(RecipeFilter.TAG_DIALOG)
                newDialogFragment?.show(supportFragmentManager, RecipeFilter.TAG_DIALOG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}