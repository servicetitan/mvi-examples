import { Container } from 'inversify';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { Config } from 'src/core/config';

// services
import { OmdbApi } from 'src/services/omdb-api';
import { RatingApi } from 'src/services/rating-api';

// repositories
import { MovieRepository } from 'src/repositories/movie-repository';
import { RatingRepository } from 'src/repositories/rating-repository';

type Constructor<T> = Function & { prototype: T }

let container: Container;

export function getService<T>(service: Constructor<T>): T {
    return container.get<T>(service);
}

export function initIoc(eventDispatcher: EventDispatcher) {
    container = new Container({ defaultScope: "Singleton" });

    container.bind<EventDispatcher>(EventDispatcher).toConstantValue(eventDispatcher);

    const services: any[] = [
        Config,
        OmdbApi,
        RatingApi,
        MovieRepository,
        RatingRepository
    ];
    
    for (const service of services) {
        container.bind(service).to(service);
    }
    
    return container;
}
