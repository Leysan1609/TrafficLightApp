package com.example.trafficlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private var imSemafor: ImageView? = null
    var counter: Int = 0
    private var timer: Timer? = null
    private var isRun = false
    var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imSemafor = findViewById(R.id.ivSemafor)
    }
    fun onClickStartStop(view: View) {
        view as ImageButton
        if (!isRun){
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
        } else {
            imSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            isRun = false
            counter = 0
        }
    }
    private fun startStop() {
        timer = Timer()
        timer?.schedule(object : TimerTask(){
            override fun run() {
                runOnUiThread {
                    imSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) counter = 0
                }
            }

        }, 0, 1000)
    }
}