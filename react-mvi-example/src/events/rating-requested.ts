import { Event, EventType } from 'src/core/event';

export interface RatingRequestedPayload {
    imdbId: string;
    rating: number | null;
}

export class RatingRequested extends Event<RatingRequestedPayload> {
    constructor(payload: RatingRequestedPayload) {
        super(EventType.RatingRequested, payload);
    }
}
