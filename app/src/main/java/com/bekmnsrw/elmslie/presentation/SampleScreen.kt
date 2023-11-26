package com.bekmnsrw.elmslie.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.bekmnsrw.elmslie.presentation.elm.SampleEffect
import com.bekmnsrw.elmslie.presentation.elm.SampleState
import vivid.money.elmslie.compose.EffectWithKey

@Composable
fun SampleScreen(
    screenState: SampleState,
    screenEffect: EffectWithKey<SampleEffect>?,
    onButtonGetValueClicked: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        ScreenEffect(
            snackbarHostState = snackbarHostState,
            screenEffect = screenEffect?.value,
            key = screenEffect?.key
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(screenState.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            Text(
                text = "${screenState.value}",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Default
                )
            )

            Button(onClick = onButtonGetValueClicked) {
                Text(text = "Get value")
            }
        }
    }
}

@Composable
private fun ScreenEffect(
    screenEffect: SampleEffect?,
    snackbarHostState: SnackbarHostState,
    key: Any?
) {

    LaunchedEffect(key) {
        when (screenEffect) {
            null -> Unit
            is SampleEffect.ShowError -> snackbarHostState.showSnackbar(screenEffect.errorMessage)
        }
    }
}
