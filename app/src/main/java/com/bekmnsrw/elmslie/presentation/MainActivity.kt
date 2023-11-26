package com.bekmnsrw.elmslie.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import com.bekmnsrw.elmslie.presentation.elm.SampleEffect
import com.bekmnsrw.elmslie.presentation.elm.SampleEvent
import com.bekmnsrw.elmslie.presentation.elm.SampleState
import com.bekmnsrw.elmslie.presentation.elm.sampleStoreFactory
import com.bekmnsrw.elmslie.ui.theme.ElmslieTheme
import vivid.money.elmslie.android.storeholder.LifecycleAwareStoreHolder
import vivid.money.elmslie.compose.ElmComponentActivity

class MainActivity : ElmComponentActivity<SampleEvent, SampleEffect, SampleState>() {

    override val initEvent: SampleEvent = SampleEvent.Ui.Init

    override val storeHolder = LifecycleAwareStoreHolder<SampleEvent, SampleEffect, SampleState>(
        lifecycle = lifecycle,
        storeProvider = { sampleStoreFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElmslieTheme {
                val screenState by state()
                val screenEffect by effect()

                SampleScreen(
                    screenState = screenState,
                    screenEffect = screenEffect,
                    onButtonGetValueClicked = {
                        storeHolder.store.accept(SampleEvent.Ui.OnButtonGetValueClicked)
                    }
                )
            }
        }
    }
}
