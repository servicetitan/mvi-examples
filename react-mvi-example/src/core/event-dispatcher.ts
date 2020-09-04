import { Event } from 'src/core/event';
import { Observable, Subject } from 'rxjs';

export class EventDispatcher {
    private readonly eventSubject: Subject<Event>;
    public get eventStream(): Observable<Event> { return this.eventSubject; }

    constructor() {
        this.eventSubject = new Subject<Event>();
    }

    public dispatch(event: Event) {
        if (event) {
            console.log(`Event dispatched: ${event.eventType}`)
            this.eventSubject.next(event);
        }
    }
}
