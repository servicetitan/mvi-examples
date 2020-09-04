import { EventType } from 'src/core/event';
import { getService } from 'src/core/ioc';
import { MoviesRequested } from 'src/events/movies-requested';
import { MoviesReceived } from 'src/events/movies-received';
import { OmdbApi } from 'src/services/omdb-api';
import { MovieRepository } from 'src/repositories/movie-repository';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { filter } from 'rxjs/operators';

export class MoviesRequestedProcessor {
    private get omdbApi() { return getService(OmdbApi); }
    private get movieRepository() { return getService(MovieRepository); }

    constructor(private readonly dispatcher: EventDispatcher){
        dispatcher.eventStream.pipe(
            filter(e => e.eventType === EventType.MoviesRequested)
        ).subscribe({
            complete: () => this.onComplete(),
            next: event => this.onNext(event as MoviesRequested)
        });
    }

    private getTotalPages(totalResults: number) {
        if (!totalResults) {
            return 1;
        }
        return Math.trunc(((totalResults - 1) / 10) + 1);
    }

    async onNext(event: MoviesRequested) {
        const { searchString, page, type, year } = event.payload;
        this.movieRepository.setLoading();
        this.movieRepository.setCurrentPage(page ?? 1);
        try {
            const searchResult = await this.omdbApi.search({
                title: searchString,
                page,
                type,
                year
            });
        
            this.dispatcher.dispatch(new MoviesReceived({
                movies: searchResult.Search.map(x => ({
                    title: x.Title,
                    year: Number(x.Year),
                    imdbId: x.imdbID,
                    posterUrl: x.Poster,
                    type: x.Type
                })),
                page: page ?? 1,
                totalPages: this.getTotalPages(Number(searchResult.totalResults))
            }));
        } catch(err) {
            this.dispatcher.dispatch(new MoviesReceived({ movies: [], page: 1, totalPages: 1 }));
        }
    }

    private onComplete() {
        // called when the app shuts down
    }
}
