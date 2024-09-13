package com.example.lifecycle
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("check", "onCreate: " )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onStart() {
        Log.d("check", "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d("check", "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d("check", "onPause: ")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("check", "onRestart: ")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("check", "onstop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("check", "onDestroy: ")
        super.onDestroy()
    }

}