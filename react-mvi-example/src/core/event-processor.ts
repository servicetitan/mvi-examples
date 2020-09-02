import { injectable } from 'inversify';
import { EventType, Event } from 'src/core/event';

function getClass() {
    abstract class EventProcessor<T = unknown> {
        public abstract process(event: Event<T>): Promise<void>;
        public abstract get supportedEvent(): EventType;
    }

    return EventProcessor;
}

export const EventProcessor =
    injectable()(getClass()) as ReturnType<typeof getClass>
