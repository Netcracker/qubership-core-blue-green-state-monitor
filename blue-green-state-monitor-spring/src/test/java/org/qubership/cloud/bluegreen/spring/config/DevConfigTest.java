package org.qubership.cloud.bluegreen.spring.config;

import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;
import org.qubership.cloud.bluegreen.api.service.GlobalMutexService;
import org.qubership.cloud.bluegreen.api.service.MicroserviceMutexService;
import org.qubership.cloud.bluegreen.impl.service.InMemoryBlueGreenStatePublisher;
import org.qubership.cloud.bluegreen.impl.service.InMemoryGlobalMutexService;
import org.qubership.cloud.bluegreen.impl.service.InMemoryMicroserviceMutexService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {InMemoryConfig.class,
        BlueGreenGlobalMutexConfiguration.class,
        BlueGreenMicroserviceMutexConfiguration.class,
        BlueGreenStatePublisherConfiguration.class},
        properties = {"blue-green.state-monitor.dev.enabled=true"},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DevConfigTest {

    @Autowired
    BlueGreenStatePublisher blueGreenStatePublisher;
    @Autowired
    GlobalMutexService globalMutexService;
    @Autowired
    MicroserviceMutexService microserviceMutexService;

    @Test
    void testBlueGreenStatePublisherBean() {
        Assertions.assertNotNull(blueGreenStatePublisher);
        Assertions.assertTrue(blueGreenStatePublisher instanceof InMemoryBlueGreenStatePublisher);
    }

    @Test
    void testGlobalMutexServiceBean() {
        Assertions.assertNotNull(globalMutexService);
        Assertions.assertTrue(globalMutexService instanceof InMemoryGlobalMutexService);
    }

    @Test
    void testMicroserviceMutexServiceBean() {
        Assertions.assertNotNull(microserviceMutexService);
        Assertions.assertTrue(microserviceMutexService instanceof InMemoryMicroserviceMutexService);
    }

}
