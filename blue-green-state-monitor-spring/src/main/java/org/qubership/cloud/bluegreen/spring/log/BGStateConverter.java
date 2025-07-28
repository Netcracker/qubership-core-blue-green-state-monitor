package org.qubership.cloud.bluegreen.spring.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;

public class BGStateConverter extends ClassicConverter {
    private static volatile BlueGreenStatePublisher blueGreenStatePublisher;

    static void setHolder(BlueGreenStatePublisher blueGreenStatePublisher) {
        BGStateConverter.blueGreenStatePublisher = blueGreenStatePublisher;
    }

    @Override
    public String convert(ILoggingEvent event) {
        if (blueGreenStatePublisher == null || blueGreenStatePublisher.getBlueGreenState() == null) {
            return "unknown";
        }
        return blueGreenStatePublisher.getBlueGreenState().getCurrent().getState().getName();
    }
}
