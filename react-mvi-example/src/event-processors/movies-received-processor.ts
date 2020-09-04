import { EventType } from 'src/core/event';
import { MovieRepository } from 'src/repositories/movie-repository';
import { getService } from 'src/core/ioc';
import { MoviesReceived } from 'src/events/movies-received';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { filter } from 'rxjs/operators';

export class MoviesReceivedProcessor {
    private get movieRepository() { return getService(MovieRepository); }

    constructor(private readonly dispatcher: EventDispatcher){
        dispatcher.eventStream.pipe(
            filter(e => e.eventType === EventType.MoviesReceived)
        ).subscribe({
            complete: () => this.onComplete(),
            next: event => this.onNext(event as MoviesReceived)
        });
    }

    private async onNext(event: MoviesReceived) {
        const { movies, page, totalPages } = event.payload;
        this.movieRepository.setTotalPages(totalPages);
        this.movieRepository.setCurrentPage(page);
        this.movieRepository.setMovies(movies);
        this.movieRepository.clearLoading();
    }

    private onComplete() {
        // called when the app shuts down
    }
}
