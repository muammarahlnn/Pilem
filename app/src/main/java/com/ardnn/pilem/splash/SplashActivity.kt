package com.ardnn.pilem.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ardnn.pilem.MainActivity
import com.ardnn.pilem.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // set timer for 1 second
        Handler(Looper.getMainLooper()).postDelayed({
            // to main activity
            val toMain = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(toMain)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 1000)
    }
}