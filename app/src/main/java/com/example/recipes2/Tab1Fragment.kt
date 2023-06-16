package com.example.recipes2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab1Fragment : Fragment() {

    interface ItemListenerActivity {
        fun onItemClicked(id: Long, tab: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipeRecycler = inflater.inflate(R.layout.fragment_tab1, container, false) as RecyclerView
        val recipeNames = arrayOfNulls<String>(Recipe.recipes[0].size)
        for (i in recipeNames.indices) {
            recipeNames[i] = Recipe.recipes[0][i].fetchName()
        }

        val recipeImages = IntArray(Recipe.recipes[0].size)
        for (i in recipeImages.indices) {
            recipeImages[i] = Recipe.recipes[0][i].getImageId()
        }
        val adapter = CaptionedImagesAdapter(recipeNames, recipeImages)
        recipeRecycler.adapter = adapter
        val layoutManager = GridLayoutManager(activity, 2)
        recipeRecycler.layoutManager = layoutManager

        adapter.onClickListener = object : CaptionedImagesAdapter.OnClickListener {
            override fun onClick(position: Int) {
                try {
                    val a = this@Tab1Fragment.activity as ItemListenerActivity
                    a.onItemClicked(position.toLong(), 0)
                } catch (_: Exception){
                    Log.i("RECIPES", "Activity doesn't implement interface")
                }
            }
        }
        return recipeRecycler
    }
}