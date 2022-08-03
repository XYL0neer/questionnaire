// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import ui.App


fun main() = application {
    var isOpen by remember { mutableStateOf(true) }

    val closeApp = {
        isOpen = false
    }

    if (isOpen) {
        Window(onCloseRequest = ::exitApplication) {
            App(closeApp)
        }
    }
}
