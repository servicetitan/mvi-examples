import { injectable } from 'inversify';
import { Event } from 'src/core/event';
import { EventProcessor } from 'src/core/event-processor';
import { getAllServices } from 'src/core/ioc';

const EventDispatcherClass = class EventDispatcher {
    private eventQueue: Event[];


    constructor() {
        this.eventQueue = [];
    }

    public dispatch(event: Event) {
        if (event) {
            const processors = this.getProcessors(event);
            for (const processor of processors) {
                processor.process(event);
            }
        }
    }

    private getProcessors(event: Event) {
        return getAllServices(EventProcessor).filter(x => x.supportedEvent === event.eventType);
    }
}

export const EventDispatcher =
    injectable()(EventDispatcherClass) as typeof EventDispatcherClass;
