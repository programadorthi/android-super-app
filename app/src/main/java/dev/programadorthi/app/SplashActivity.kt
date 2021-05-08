package dev.programadorthi.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dev.programadorthi.norris.ui.activity.FactsActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Simulating a splash screen. Don't judge me! :P
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashActivity, FactsActivity::class.java))
            },
            DELAY_IN_MILLIS
        )
    }

    private companion object {
        private const val DELAY_IN_MILLIS = 500L
    }
}
