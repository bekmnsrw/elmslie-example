package com.bekmnsrw.elmslie.presentation.elm

import com.bekmnsrw.elmslie.data.ValueRepository
import io.reactivex.rxjava3.core.Observable
import vivid.money.elmslie.rx3.Actor

class SampleActor : Actor<SampleCommand, SampleEvent> {

    override fun execute(command: SampleCommand): Observable<SampleEvent> = when (command) {
        SampleCommand.LoadNewValue -> ValueRepository.loadValue()
            .mapEvents(
                eventMapper = SampleEvent.Internal::ValueLoadingSucceeded,
                errorMapper = SampleEvent.Internal::ValueLoadingFailed
            )
    }
}
