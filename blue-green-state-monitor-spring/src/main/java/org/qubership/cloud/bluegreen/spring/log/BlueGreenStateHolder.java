package org.qubership.cloud.bluegreen.spring.log;

import jakarta.annotation.PostConstruct;
import org.qubership.cloud.bluegreen.api.model.BlueGreenState;
import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Singleton bean for holding the current BlueGreenState in memory and updating it on changes.
 */
public class BlueGreenStateHolder {

    private final BlueGreenStatePublisher blueGreenStatePublisher;
    private final AtomicReference<BlueGreenState> state = new AtomicReference<>();

    public BlueGreenStateHolder(BlueGreenStatePublisher blueGreenStatePublisher) {
        this.blueGreenStatePublisher = blueGreenStatePublisher;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        state.set(blueGreenStatePublisher.getBlueGreenState());
        blueGreenStatePublisher.subscribe(state::set);
    }

    public String getState() {
        return state.get().getCurrent().getState().getName();
    }
}
