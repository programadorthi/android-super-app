package dev.programadorthi.shared.ui.ext

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

val View.lifecycleScope: LifecycleCoroutineScope
    get() =
        (context as? LifecycleOwner)?.lifecycleScope
            ?: throw IllegalStateException("View context is not a LifecycleOwner. Context: $context")
