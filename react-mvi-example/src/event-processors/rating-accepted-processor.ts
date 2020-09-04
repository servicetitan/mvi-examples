import { EventType } from 'src/core/event';
import { RatingAccepted } from 'src/events/rating-accepted';
import { getService } from 'src/core/ioc';
import { RatingRepository } from 'src/repositories/rating-repository';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { filter } from 'rxjs/operators';

export class RatingAcceptedProcessor {
    private get ratingRepository() { return getService(RatingRepository); }

    constructor(private readonly dispatcher: EventDispatcher){
        dispatcher.eventStream.pipe(
            filter(e => e.eventType === EventType.RatingAccepted)
        ).subscribe({
            complete: () => this.onComplete(),
            next: event => this.onNext(event as RatingAccepted)
        });
    }

    private async onNext(event: RatingAccepted) {
        const { imdbId, rating } = event.payload;
        if (rating) {
            this.ratingRepository.setRating(imdbId, rating);
        } else {
            this.ratingRepository.clearRating(imdbId);
        }
        this.ratingRepository.clearPending(imdbId);
    }

    private onComplete() {
        // called when the app shuts down
    }
}
