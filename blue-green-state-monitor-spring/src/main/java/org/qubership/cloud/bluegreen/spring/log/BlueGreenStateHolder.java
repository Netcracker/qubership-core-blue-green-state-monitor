package org.qubership.cloud.bluegreen.spring.log;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.qubership.cloud.bluegreen.api.model.BlueGreenState;
import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Singleton bean for holding the current BlueGreenState in memory and updating it on changes.
 */
@Component
@DependsOn("blueGreenStatePublisher")
@Slf4j
public class BlueGreenStateHolder {

    private final BlueGreenStatePublisher blueGreenStatePublisher;
    private final AtomicReference<BlueGreenState> state = new AtomicReference<>();

    public BlueGreenStateHolder(BlueGreenStatePublisher blueGreenStatePublisher) {
        this.blueGreenStatePublisher = blueGreenStatePublisher;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        log.debug("VLLA afterPropertiesSet");
        state.set(blueGreenStatePublisher.getBlueGreenState());
        blueGreenStatePublisher.subscribe(state::set);
    }

    public String getState() {
        return state.get().getCurrent().getState().getName();
    }
}
