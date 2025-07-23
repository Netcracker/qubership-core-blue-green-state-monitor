package org.qubership.cloud.bluegreen.spring.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class BGStateConverter extends ClassicConverter {
    private static volatile BlueGreenStateHolder blueGreenStateHolder;

    static void setHolder(BlueGreenStateHolder blueGreenStateHolder) {
        BGStateConverter.blueGreenStateHolder = blueGreenStateHolder;
    }

    @Override
    public String convert(ILoggingEvent event) {
        if (blueGreenStateHolder == null || blueGreenStateHolder.getState() == null) {
            return "unknown";
        }
        return blueGreenStateHolder.getState();
    }
}
