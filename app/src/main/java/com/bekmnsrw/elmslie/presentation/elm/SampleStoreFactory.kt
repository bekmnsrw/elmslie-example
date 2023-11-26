package com.bekmnsrw.elmslie.presentation.elm

import vivid.money.elmslie.rx3.ElmStoreCompat

fun sampleStoreFactory() = ElmStoreCompat(
    initialState = SampleState(),
    reducer = SampleReducer,
    actor = SampleActor()
)
