package com.bekmnsrw.elmslie.presentation.elm

import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer

object SampleReducer : ScreenDslReducer<SampleEvent, SampleEvent.Ui, SampleEvent.Internal, SampleState, SampleEffect, SampleCommand>(
    SampleEvent.Ui::class,
    SampleEvent.Internal::class
) {

    private const val ERROR_MESSAGE = "Error! Please, try again"
    private val VALUE_ON_ERROR = null

    override fun Result.ui(event: SampleEvent.Ui) = when (event) {
        SampleEvent.Ui.Init -> {
            state { copy(isLoading = false) }
            commands { +SampleCommand.LoadNewValue }
        }

        SampleEvent.Ui.OnButtonGetValueClicked -> {
            state { copy(isLoading = true) }
            commands { +SampleCommand.LoadNewValue }
        }
    }

    override fun Result.internal(event: SampleEvent.Internal) = when (event) {
        is SampleEvent.Internal.ValueLoadingFailed -> {
            state { copy(isLoading = false, value = VALUE_ON_ERROR) }
            effects { +SampleEffect.ShowError(errorMessage = ERROR_MESSAGE) }
        }

        is SampleEvent.Internal.ValueLoadingSucceeded -> {
            state { copy(isLoading = false, value = event.value) }
        }
    }
}
