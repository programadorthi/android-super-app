package dev.programadorthi.norris.ui.ext

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

// TODO: move to an UI module
val View.lifecycleScope: LifecycleCoroutineScope
    get() =
        (context as? LifecycleOwner)?.lifecycleScope
            ?: throw IllegalStateException("View context is not a LifecycleOwner. Context: $context")
