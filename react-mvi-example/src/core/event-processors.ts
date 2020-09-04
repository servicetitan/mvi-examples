import { EventDispatcher } from 'src/core/event-dispatcher';
import { MoviesRequestedProcessor } from 'src/event-processors/movies-requested-processor';
import { MoviesReceivedProcessor } from 'src/event-processors/movies-received-processor';
import { RatingRequestedProcessor } from 'src/event-processors/rating-requested-processor';

export function instantiateEventProcessors(dispatcher: EventDispatcher): any[] {
    return [
        MoviesRequestedProcessor,
        MoviesReceivedProcessor,
        RatingRequestedProcessor
    ].map(x => new x(dispatcher));
}
