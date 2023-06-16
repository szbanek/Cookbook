package com.example.recipes2

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment

class MeatballFragment : Fragment() {
    private lateinit var meatballView: View
    private var isCollapsed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meatball, container, false)
        meatballView = view.findViewById(R.id.meatball)

        view.setOnClickListener {
            startAnimation()
        }
        return view
    }

    private fun makeAnimator(target: View, start: Float, end: Float, interpolator: TimeInterpolator): Animator {
        val scaleXAnimator: ObjectAnimator = ObjectAnimator
            .ofFloat(target, "scaleX", start, end)
            .setDuration(1200)
        scaleXAnimator.interpolator = interpolator
        val scaleYAnimator: ObjectAnimator = ObjectAnimator
            .ofFloat(target, "scaleY", start, end)
            .setDuration(1200)
        scaleYAnimator.interpolator = interpolator

        val animator = AnimatorSet()
        animator.playTogether(
            scaleXAnimator,
            scaleYAnimator
        )
        return animator
    }

    private fun startAnimation()
    {
        val start = if(isCollapsed) 0f else 1f
        val end = if(isCollapsed) 1f else 0f
        val interpolator = if(isCollapsed) DecelerateInterpolator() else AccelerateInterpolator()

        val meatballAnimator = makeAnimator(meatballView, start, end, interpolator)

        meatballAnimator.start()

        isCollapsed = !isCollapsed
    }

}