package com.bekmnsrw.elmslie.presentation.elm

data class SampleState(
    val isLoading: Boolean = false,
    val value: Int? = null
)

sealed class SampleEvent {
    sealed class Ui : SampleEvent() {
        data object Init : Ui()
        data object OnButtonGetValueClicked : Ui()
    }

    sealed class Internal : SampleEvent() {
        data class ValueLoadingSucceeded(val value: Int) : Internal()
        data class ValueLoadingFailed(val error: Throwable) : Internal()
    }
}

sealed class SampleEffect {
    data class ShowError(val errorMessage: String) : SampleEffect()
}

sealed class SampleCommand {
    data object LoadNewValue : SampleCommand()
}
