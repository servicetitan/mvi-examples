import { v4 as uuid } from 'uuid';

export class Event<T = unknown> {
    public readonly id: string;
    public readonly timestamp: Date;

    protected constructor(
        public readonly eventType: EventType,
        public readonly payload: T
    ) {
        this.id = uuid();
        this.timestamp = new Date();
    }
}

export enum EventType {
    MoviesRequested,
    MoviesReceived,
    RatingRequested
}
