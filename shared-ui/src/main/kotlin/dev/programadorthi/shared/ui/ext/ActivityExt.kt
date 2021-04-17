package dev.programadorthi.shared.ui.ext

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun ComponentActivity.runScoped(
    action: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated {
    action.invoke(this)
}
