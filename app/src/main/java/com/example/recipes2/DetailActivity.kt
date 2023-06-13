package com.example.recipes2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetailActivity", "Before setContentView")
        setContentView(R.layout.activity_detail)
        val recipeId = intent.getLongExtra(EXTRA_recipe_ID,0)

        Log.d("DetailActivity", "Odczytane id: $recipeId")
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as? RecipeDetailFragment
        frag?.setRecipe(recipeId)

        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }

    companion object {
        const val EXTRA_recipe_ID = "id"
    }
}