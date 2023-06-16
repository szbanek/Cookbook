package com.example.recipes2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0
    private var tabId: Int = 0

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            recipeId = savedInstanceState.getLong("recipeId")
            tabId = savedInstanceState.getInt("tabId")
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle){
        savedInstanceState.putLong("recipeId", recipeId)
        savedInstanceState.putInt("tabId", tabId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("recipeDetailFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    fun setRecipe(Id: Long, tab: Int)
    {
        recipeId = Id
        tabId = tab
        Log.d("recipeDetailFragment", "Received recipe ID: $recipeId")
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            val recipe = Recipe.recipes[tabId][recipeId.toInt()]
            title.text = recipe.fetchName()
            val image = view.findViewById<ImageView>(R.id.recipe_image)
            image?.setImageResource(recipe.getImageId())
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.text = recipe.getRecipe()
        }
        else{
            Log.d("recipeDetailFragment", "View = null")
        }
    }
}