package com.sorokin.gifviewer.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launchWithExceptionHandler(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    catchBlock: Throwable.() -> Boolean = { false },
    finallyBlock: () -> Unit = {},
    tryBlock: suspend CoroutineScope.() -> Unit
) = launchWithExceptionHandler(coroutineContext, tryBlock, catchBlock, finallyBlock)

private fun CoroutineScope.launchWithExceptionHandler(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: Throwable.() -> Boolean = { false },
    finallyBlock: () -> Unit = {}
) = launch(
    Dispatchers.Main + coroutineContext + CoroutineExceptionHandler { _, t -> catchBlock(t) }
) {
    try {
        tryBlock()
    } finally {
        finallyBlock()
    }
}