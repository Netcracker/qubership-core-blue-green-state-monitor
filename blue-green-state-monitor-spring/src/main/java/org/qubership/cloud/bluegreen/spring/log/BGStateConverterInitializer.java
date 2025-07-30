package org.qubership.cloud.bluegreen.spring.log;

import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Initializes the {@link BGStateConverter} with a {@link BlueGreenStatePublisher} instance
 * once the Spring application context is fully refreshed.
 */
@Component
public class BGStateConverterInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        BGStateConverter.setHolder(
                event.getApplicationContext().getBean(BlueGreenStatePublisher.class)
        );
    }
}
