import { Event, EventType } from 'src/core/event';

export interface RatingAcceptedPayload {
    imdbId: string;
    rating: number | null;
}

export class RatingAccepted extends Event<RatingAcceptedPayload> {
    constructor(payload: RatingAcceptedPayload) {
        super(EventType.RatingAccepted, payload);
    }
}
