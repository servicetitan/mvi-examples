import { injectable } from 'inversify';
import { EventProcessor } from 'src/core/event-processor';
import { EventType } from 'src/core/event';
import { RatingRequested, RatingRequestedPayload } from 'src/events/rating-requested';
import { getService } from 'src/core/ioc';
import { RatingRepository } from 'src/repositories/rating-repository';
import { RatingApi } from 'src/services/rating-api';

const RatingRequestedProcessorClass = class RatingRequestedProcessor extends EventProcessor<RatingRequestedPayload> {
    public get supportedEvent() { return EventType.RatingRequested; }
    private get ratingRepository() { return getService(RatingRepository); }
    private get ratingApi() { return getService(RatingApi); }

    async process(event: RatingRequested) {
        const { imdbId, rating } = event.payload;
        try {
            this.ratingRepository.setPending(imdbId);
            if (rating) {
                await this.ratingApi.setRating(imdbId, rating);
                this.ratingRepository.setRating(imdbId, rating);
            } else {
                await this.ratingApi.removeRating(imdbId);
                this.ratingRepository.clearRating(imdbId);
            }
        } catch(err) {
            this.ratingRepository.clearPending(imdbId);
        }
    }
}

export const RatingRequestedProcessor =
    injectable()(RatingRequestedProcessorClass) as typeof RatingRequestedProcessorClass;
