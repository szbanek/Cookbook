package com.example.recipes2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class StoperFragment : Fragment(), View.OnClickListener {
    private var seconds: Int = 60
    private var running: Boolean = false
    private var wasRunning: Boolean = false
    lateinit var hoursView: TextView
    lateinit var minutesView: TextView
    lateinit var secondsView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning= savedInstanceState.getBoolean("wasRunning")
        }
    }

    @SuppressLint("ResourceType", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStoper(layout)
        hoursView = layout.findViewById<View>(R.id.time_hours) as TextView
        minutesView = layout.findViewById<View>(R.id.time_minutes) as TextView
        secondsView = layout.findViewById<View>(R.id.time_seconds) as TextView
        layout.findViewById<View>(R.id.start_button).setOnClickListener(this)
        layout.findViewById<View>(R.id.stop_button).setOnClickListener(this)
        layout.findViewById<View>(R.id.reset_button).setOnClickListener(this)
        layout.findViewById<View>(R.id.time_hours).setOnClickListener(this)
        layout.findViewById<View>(R.id.time_minutes).setOnClickListener(this)
        layout.findViewById<View>(R.id.time_seconds).setOnClickListener(this)
        return layout
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running;
        running = false;
    }

    override fun onResume() {
        super.onResume()
        if(wasRunning){
            running = true;
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
        hoursView.setTextColor(Color.BLACK)
        minutesView.setTextColor(Color.BLACK)
        secondsView.setTextColor(Color.BLACK)
    }

    private fun increaseTime(value: Int) {
        seconds += value;
    }

    private fun runStoper(view: View) {

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                hoursView.text = String.format("%d:", hours)
                minutesView.text = String.format("%02d:", minutes)
                secondsView.text = String.format("%02d", secs)
                if (running) {
                    seconds--
                    if (seconds < 0) {
                        hoursView.setTextColor(Color.RED)
                        minutesView.setTextColor(Color.RED)
                        secondsView.setTextColor(Color.RED)
                    }
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.time_seconds -> increaseTime(1)
            R.id.time_minutes -> increaseTime(60)
            R.id.time_hours -> increaseTime(3600)
        }
    }

}