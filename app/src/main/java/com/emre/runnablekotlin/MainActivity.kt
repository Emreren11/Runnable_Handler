package com.emre.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.emre.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var number = 0
    var runnable: Runnable = Runnable {} // arka planda işlemler yapar
    var handler: Handler = Handler(Looper.getMainLooper()) // Runnable'ı kullanmak için kullanılır

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun start(view: View) {
        number = 0
        runnable = object : Runnable {
            override fun run() {
                number++
                binding.textView.text = "Time: ${number}"
                handler.postDelayed(runnable /*this*/,1000) // 1000 milisaniyede bir runnable'ı çalıştırır

            }
        }

        handler.post(runnable) // runnable'ı başlatır
        binding.button.isEnabled = false
    }

    fun stop(view: View) {
        binding.button.isEnabled = true
        number = 0
        binding.textView.text = "Time: ${number}"
        handler.removeCallbacks(runnable) // runnable işlemini durdurur
    }
}