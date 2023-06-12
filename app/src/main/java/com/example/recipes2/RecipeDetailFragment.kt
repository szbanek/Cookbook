package com.example.recipes2

//import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("recipeDetailFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    fun setRecipe(Id: Long)
    {
        recipeId = Id
        Log.d("recipeDetailFragment", "Received recipe ID: $recipeId")
        onResume()
    }

    override fun onResume() {
        super.onResume()
        val view = view
        Log.d("recipeDetailFragment", "OnStart ready: $recipeId")
        if (view != null) {
            Log.d("recipeDetailFragment", "--Received recipe ID: $recipeId")
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            val recipe = Recipe.recipes[recipeId.toInt()]
            title.text = recipe.fetchName()
            Log.d("recipeDetailFragment", "Title: ${title.text}")
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            Log.d("recipeDetailFragment", "Description: ${description.text}")
            description.text = recipe.getRecipe()
        }
        else{
            Log.d("recipeDetailFragment", "View = null")
        }
    }
}