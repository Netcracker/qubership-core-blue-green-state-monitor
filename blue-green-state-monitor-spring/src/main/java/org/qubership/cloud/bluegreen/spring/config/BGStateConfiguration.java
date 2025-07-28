package org.qubership.cloud.bluegreen.spring.config;

import org.qubership.cloud.bluegreen.spring.log.BGStateConverterInitializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({InMemoryCondition.class, BlueGreenStatePublisherConfiguration.class})
public class BGStateConfiguration {

    @Bean
    public BGStateConverterInitializer BGStateConverterInitializer() {
        return new BGStateConverterInitializer();
    }
}
