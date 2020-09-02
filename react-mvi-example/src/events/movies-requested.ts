import { Event, EventType } from 'src/core/event';

export interface MoviesRequestedPayload {
    searchString: string;
    year?: number;
    type?: "movie" | "series" | "episode";
    page?: number;
}

export class MoviesRequested extends Event<MoviesRequestedPayload> {
    constructor(payload: MoviesRequestedPayload) {
        super(EventType.MoviesRequested, payload);
    }
}
