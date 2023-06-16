package com.example.recipes2


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetailActivity", "Before setContentView")
        setContentView(R.layout.activity_detail)
        val recipeId = intent.getLongExtra(EXTRA_RECIPE_ID,0)
        val tabId = intent.getIntExtra(EXTRA_TAB_ID, 0)

        Log.d("DetailActivity", "Odczytane id: $recipeId")
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as? RecipeDetailFragment
        frag?.setRecipe(recipeId, tabId)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val recipe = Recipe.recipes[tabId][recipeId.toInt()]
        val recipeImage = findViewById<ImageView>(R.id.recipe_image)
        recipeImage.setImageResource(recipe.getImageId())
        this.title = recipe.fetchName()

        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }

    fun onClickDone(view: View?) {
        val text: CharSequence = "Dodano"
        val duration = Snackbar.LENGTH_SHORT
        val snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration)
        snackbar.show()
    }

    companion object {
        const val EXTRA_RECIPE_ID = "id"
        const val EXTRA_TAB_ID = "tab"
    }
}