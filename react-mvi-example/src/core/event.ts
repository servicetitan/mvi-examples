export class Event<T = unknown> {
    public readonly timestamp: Date;

    protected constructor(
        public readonly eventType: EventType,
        public readonly payload: T
    ) {
        this.timestamp = new Date();
    }
}

export enum EventType {
    MoviesRequested,
    MoviesReceived,
    RatingRequested
}
