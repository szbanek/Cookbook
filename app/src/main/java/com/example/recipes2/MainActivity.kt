package com.example.recipes2

//import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.recipes2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), RecipeListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecipeListFragment())
                .commit()
        }
    }

    override fun itemClicked(id: Long) {
        val fragmentContainer = findViewById(R.id.detail_fragment) as? View
        if (fragmentContainer != null) {
            val transaction = supportFragmentManager.beginTransaction()
            val frag = RecipeDetailFragment()
            frag.setRecipe(id)
            transaction.replace(R.id.detail_fragment, frag)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            Log.d("MainActivity", "Przypisane id: $id")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_recipe_ID, id)
            startActivity(intent)
        }
    }

}