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
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val pager = findViewById<View>(R.id.pager) as ViewPager
        pager.adapter = pagerAdapter
        val tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(pager)
    }

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
            val stoper = StoperFragment()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
            intent.putExtra(DetailActivity.EXTRA_TAB_ID, tab)
            startActivity(intent)
        }
    }

}