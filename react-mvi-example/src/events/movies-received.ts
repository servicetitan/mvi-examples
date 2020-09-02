import { Event, EventType } from 'src/core/event';
import { Movie } from 'src/entities/movie';

export interface MoviesReceivedPayload {
    movies: Movie[];
    page: number;
    totalPages: number;
}

export class MoviesReceived extends Event<MoviesReceivedPayload> {
    constructor(payload: MoviesReceivedPayload) {
        super(EventType.MoviesReceived, payload);
    }
}
