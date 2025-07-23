package org.qubership.cloud.bluegreen.spring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qubership.cloud.bluegreen.api.model.BlueGreenState;
import org.qubership.cloud.bluegreen.api.model.NamespaceVersion;
import org.qubership.cloud.bluegreen.api.model.State;
import org.qubership.cloud.bluegreen.api.model.Version;
import org.qubership.cloud.bluegreen.api.service.BlueGreenStatePublisher;
import org.qubership.cloud.bluegreen.spring.log.BlueGreenStateHolder;

import java.time.OffsetDateTime;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlueGreenStateHolderTest {
    BlueGreenStatePublisher publisher;
    BlueGreenState initialState = new BlueGreenState(
            new NamespaceVersion("ns", State.ACTIVE, new Version("1")),
            OffsetDateTime.now()
    );
    BlueGreenState updatedState = new BlueGreenState(
            new NamespaceVersion("ns", State.CANDIDATE, new Version("2")),
            OffsetDateTime.now().plusMinutes(1)
    );
    Consumer<BlueGreenState> subscriber;

    @BeforeEach
    void setUp() {
        publisher = mock(BlueGreenStatePublisher.class);
        when(publisher.getBlueGreenState()).thenReturn(initialState);
    }

    @Test
    void initializesWithCurrentStateAndUpdatesOnChange() {
        doAnswer(invocation -> {
            subscriber = invocation.getArgument(0);
            subscriber.accept(initialState);
            return null;
        }).when(publisher).subscribe(any());

        BlueGreenStateHolder holder = new BlueGreenStateHolder(publisher);
        holder.afterPropertiesSet();
        assertEquals(initialState.getCurrent().getState().getName(), holder.getState());

        subscriber.accept(updatedState);
        assertEquals(updatedState.getCurrent().getState().getName(), holder.getState());
    }
}
