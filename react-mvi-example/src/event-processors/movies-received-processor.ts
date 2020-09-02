import { injectable } from 'inversify';
import { EventProcessor } from 'src/core/event-processor';
import { EventType } from 'src/core/event';
import { MovieRepository } from 'src/repositories/movie-repository';
import { getService } from 'src/core/ioc';
import { MoviesReceived, MoviesReceivedPayload } from 'src/events/movies-received';

const MoviesReceivedProcessorClass = class MoviesReceivedProcessor extends EventProcessor<MoviesReceivedPayload> {
    public get supportedEvent() { return EventType.MoviesReceived; }

    private get movieRepository() { return getService(MovieRepository); }

    async process(event: MoviesReceived) {
        const { movies, page, totalPages } = event.payload;
        this.movieRepository.setTotalPages(totalPages);
        this.movieRepository.setCurrentPage(page);
        this.movieRepository.setMovies(movies);
        this.movieRepository.clearLoading();
    }
}

export const MoviesReceivedProcessor = 
    injectable()(MoviesReceivedProcessorClass) as typeof MoviesReceivedProcessorClass;
