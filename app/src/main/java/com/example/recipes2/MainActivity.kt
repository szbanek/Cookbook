package com.example.recipes2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(),  Tab1Fragment.ItemListenerActivity, Tab2Fragment.ItemListenerActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, RecipeListFragment())
//                .commit()
//        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val pager = findViewById<View>(R.id.pager) as ViewPager
        pager.adapter = pagerAdapter
        val tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(pager)
    }

//    override fun itemClicked(id: Long) {
//        val fragmentContainer = findViewById(R.id.detail_fragment) as? View
//        if (fragmentContainer != null) {
//            val transaction = supportFragmentManager.beginTransaction()
//            val frag = RecipeDetailFragment()
//            frag.setRecipe(id, 0)
//            transaction.replace(R.id.detail_fragment, frag)
//            transaction.replace(R.id.stoper_container, StoperFragment())
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        } else {
//            Log.d("MainActivity", "Przypisane id: $id")
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
//            startActivity(intent)
//        }
//    }

    override fun onItemClicked(id: Long, tab: Int) {
        Log.i("RECIPES", id.toString())
        val detailsFragment = findViewById<FrameLayout>(R.id.container)
        if(detailsFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            val recipeDetailFragment = RecipeDetailFragment()
            recipeDetailFragment.setRecipe(id, tab)
            transaction.replace(R.id.container, recipeDetailFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
            intent.putExtra(DetailActivity.EXTRA_TAB_ID, tab)
            startActivity(intent)
        }
    }

}