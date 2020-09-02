import { injectable } from 'inversify';
import { EventProcessor } from 'src/core/event-processor';
import { EventType } from 'src/core/event';
import { getService } from 'src/core/ioc';
import { MoviesRequested, MoviesRequestedPayload } from 'src/events/movies-requested';
import { MoviesReceived } from 'src/events/movies-received';
import { OmdbApi } from 'src/services/omdb-api';
import { MovieRepository } from 'src/repositories/movie-repository';
import { EventDispatcher } from 'src/core/event-dispatcher';

const MoviesRequestedProcessorClass = class MoviesRequestedProcessor extends EventProcessor<MoviesRequestedPayload> {
    public get supportedEvent() { return EventType.MoviesRequested; }
    private get omdbApi() { return getService(OmdbApi); }
    private get movieRepository() { return getService(MovieRepository); }
    private get eventDispatcher() { return getService(EventDispatcher); }

    private getTotalPages(totalResults: number) {
        if (!totalResults) {
            return 1;
        }
        return Math.trunc(((totalResults - 1) / 10) + 1);
    }

    async process(event: MoviesRequested) {
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
        
            this.eventDispatcher.dispatch(new MoviesReceived({
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
            this.eventDispatcher.dispatch(new MoviesReceived({ movies: [], page: 1, totalPages: 1 }));
        }
    }
}

export const MoviesRequestedProcessor =
    injectable()(MoviesRequestedProcessorClass) as typeof MoviesRequestedProcessorClass;
