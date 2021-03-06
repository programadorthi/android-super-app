package dev.programadorthi.norris.ui.fake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.programadorthi.norris.ui.fake.databinding.ActivityEmptyFakeBinding

class EmptyActivityFake : AppCompatActivity() {
    private val binding by lazy {
        ActivityEmptyFakeBinding.inflate(layoutInflater)
    }

    val root by lazy { binding.root }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
