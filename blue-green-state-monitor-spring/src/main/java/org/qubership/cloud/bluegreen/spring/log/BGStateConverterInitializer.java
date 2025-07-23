package org.qubership.cloud.bluegreen.spring.log;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BGStateConverterInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        BGStateConverter.setHolder(
                event.getApplicationContext().getBean(BlueGreenStateHolder.class)
        );
    }
}
