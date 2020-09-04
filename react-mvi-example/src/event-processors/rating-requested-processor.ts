import { EventType } from 'src/core/event';
import { RatingRequested } from 'src/events/rating-requested';
import { RatingAccepted } from 'src/events/rating-accepted';
import { getService } from 'src/core/ioc';
import { RatingRepository } from 'src/repositories/rating-repository';
import { RatingApi } from 'src/services/rating-api';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { filter } from 'rxjs/operators';

export class RatingRequestedProcessor {
    private get ratingRepository() { return getService(RatingRepository); }
    private get ratingApi() { return getService(RatingApi); }

    constructor(private readonly dispatcher: EventDispatcher){
        dispatcher.eventStream.pipe(
            filter(e => e.eventType === EventType.RatingRequested)
        ).subscribe({
            complete: () => this.onComplete(),
            next: event => this.onNext(event as RatingRequested)
        });
    }

    private async onNext(event: RatingRequested) {
        const { imdbId, rating } = event.payload;
        this.ratingRepository.setPending(imdbId);
        try {
            if (rating) {
                await this.ratingApi.setRating(imdbId, rating);
            } else {
                await this.ratingApi.removeRating(imdbId);
            }
            this.dispatcher.dispatch(new RatingAccepted({ imdbId, rating }));
        } catch(err) {
            this.ratingRepository.clearPending(imdbId);
        }
    }

    private onComplete() {
        // called when the app shuts down
    }
}
