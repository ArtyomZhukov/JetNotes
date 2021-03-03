package com.zhukovartemvl.jetnotes.common_ui.navigation

import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


/*
* https://github.com/airbnb/Showkase/blob/master/showkase/src/main/java/com/airbnb/android/showkase/ui/BackButtonHandler.kt
* */

private val AmbientBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

private class ComposableBackHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }
}

@Composable
internal fun Handler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = (AmbientBackPressedDispatcher.current ?: return).onBackPressedDispatcher
    val handler = remember { ComposableBackHandler(enabled) }
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        onDispose { handler.remove() }
    }
    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
    }
    LaunchedEffect(onBackPressed) {
        handler.onBackPressed = onBackPressed
    }
}

@Composable
fun BackButtonHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit,
) {
    var context = LocalContext.current
    // Inspired from https://cs.android.com/androidx/platform/frameworks/support/+/
    // androidx-master-dev:navigation/navigation-compose/src/main/java/androidx/navigation/
    // compose/NavHost.kt;l=88
    // This was necessary because using Jetpack Navigation does not allow typecasting a
    // NavBackStackEntry to LifecycleOwnerAmbient.
    while (context is ContextWrapper) {
        if (context is OnBackPressedDispatcherOwner) {
            break
        }
        context = context.baseContext
    }
    CompositionLocalProvider(
        AmbientBackPressedDispatcher provides context as ComponentActivity
    ) {
        Handler(enabled = enabled) {
            onBackPressed()
        }
    }
}
