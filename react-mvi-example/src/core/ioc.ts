import { Container } from 'inversify';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { EventProcessor } from 'src/core/event-processor';
import { Config } from 'src/core/config';

// services
import { OmdbApi } from 'src/services/omdb-api';
import { RatingApi } from 'src/services/rating-api';

// repositories
import { MovieRepository } from 'src/repositories/movie-repository';
import { RatingRepository } from 'src/repositories/rating-repository';

// event processors
import { MoviesRequestedProcessor } from 'src/event-processors/movies-requested-processor';
import { MoviesReceivedProcessor } from 'src/event-processors/movies-received-processor';
import { RatingRequestedProcessor } from 'src/event-processors/rating-requested-processor';

function createContainer() {
    var container = new Container({ defaultScope: "Singleton" });
    const services: any[] = [
        Config,
        EventDispatcher,
        OmdbApi,
        RatingApi,
        MovieRepository,
        RatingRepository
    ];
    
    for (const service of services) {
        container.bind(service).to(service);
    }

    const processors: any[] = [
        MoviesRequestedProcessor,
        MoviesReceivedProcessor,
        RatingRequestedProcessor
    ];

    for (const processor of processors) {
        container.bind(EventProcessor).to(processor);
    }
    
    return container;
}

type Constructor<T> = Function & { prototype: T }

const container = createContainer();
(window as any).container = container;

export function getService<T>(service: Constructor<T>): T {
    return container.get<T>(service);
}

export function getAllServices<T>(service: Constructor<T>): T[] {
    return container.getAll<T>(service);
}
