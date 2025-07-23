package org.qubership.cloud.bluegreen.spring.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class BGStateConverter extends ClassicConverter {
    private volatile BlueGreenStateHolder blueGreenStateHolder;

    private void initBean() {
        try {
            if (blueGreenStateHolder == null) {
                synchronized (BGStateConverter.class) {
                    if (blueGreenStateHolder == null) {
                        blueGreenStateHolder = SpringContextHolder.getBean(BlueGreenStateHolder.class);
                    }
                }
            }
        } catch (Exception e) {
            blueGreenStateHolder = null;
        }
    }

    @Override
    public String convert(ILoggingEvent event) {
        initBean();

        var holder = blueGreenStateHolder;
        if (holder == null || holder.getState() == null) {
            return "unknown";
        }
        return holder.getState();
    }
}
