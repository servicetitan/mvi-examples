import { injectable } from 'inversify';
import * as ls from 'local-storage';
import { Observable, Subject, defer } from 'rxjs';
import { scan, publishReplay, refCount, startWith } from 'rxjs/operators';

export type Rating = "pending" | number;
export type Ratings = Record<string, Rating>;
export type RatingUpdate = { imdbId: string; rating: Rating | null };
type StoredRatings = Record<string, number>;

const storageKey = "ratings";

const RatingRepositoryClass = class RatingRepository {
    private readonly _pendingMovieIds: Set<string>;
    private readonly _ratingUpdates: Subject<RatingUpdate>;
    
    public readonly ratings: Observable<Ratings>;
    public get ratingUpdates(): Observable<RatingUpdate> {
        return this._ratingUpdates;
    }

    constructor() {
        this._pendingMovieIds = new Set<string>();
        this._ratingUpdates = new Subject<RatingUpdate>();

        // defer: execute the code at the moment of subscription instead of doing it immediately
        this.ratings = defer(() => {
            // fetch initial state
            const initialValue = this._getAllRatingsFromStorage();
            
            // emit the initial state first, then emit an updated state every time the ratingUpdates observable emits a value,
            // emit a new, combined result here
            return this._ratingUpdates.pipe(
                scan<RatingUpdate, Ratings>(
                    (state, event) => {
                        const newState = { ...state };
                        if (event.rating === null) {
                            delete newState[event.imdbId];
                        } else {
                            newState[event.imdbId] = event.rating;
                        }
                        return newState;
                    }, 
                    initialValue
                ),
                startWith(initialValue)
            );
        }).pipe(
            // replay the latest value to any new subscriber
            publishReplay(1),
            // instead of creating a new observable for each subscriber, multicast the initial observable
            // to all subscribers. The observable will be disposed when the last observer unsubscribes.
            refCount()
        );
    }

    public async setRating(imdbId: string, rating: number) {
        this._setPending(imdbId, false);
        this._setRating(imdbId, rating);
        this._emitRatingUpdate(imdbId, rating);
    }

    public setPending(imdbId: string) {
        this._setPending(imdbId, true);
        this._emitRatingUpdate(imdbId, "pending");
    }

    public clearPending(imdbId: string) {
        this._setPending(imdbId, false);
        const ratingValue = this._getRatingFromStorage(imdbId);
        this._emitRatingUpdate(imdbId, ratingValue);
    }

    public async clearRating(imdbId: string) {
        this._setPending(imdbId, false);
        this._setRating(imdbId, null);
        this._emitRatingUpdate(imdbId, null);
    }

    private _emitRatingUpdate(imdbId: string, rating: Rating | null) {
        this._ratingUpdates.next({ imdbId, rating });
    }

    private _setRating(imdbId: string, rating: number | null) {
        // normally we'd do this with sql update
        const currentRatings = this._getAllRatingsFromStorage();
        if (rating === null) {
            delete currentRatings[imdbId];
        } else {
            currentRatings[imdbId] = rating;
        }
        ls.set(storageKey, currentRatings);
    }

    private _getRatingFromStorage(imdbId: string) : number | null {
        var ratingValue = ls.get<StoredRatings>(storageKey)[imdbId];
        if (ratingValue === undefined) {
            return null;
        }
        return ratingValue;
    }

    private _getAllRatingsFromStorage(): StoredRatings {
        return ls.get<StoredRatings>(storageKey) || {};
    }

    private _setPending(imdbId: string, isPending: boolean) {
        if (isPending) {
            this._pendingMovieIds.add(imdbId);
        } else {
            this._pendingMovieIds.delete(imdbId);
        }
    }
}

export const RatingRepository = 
    injectable()(RatingRepositoryClass) as typeof RatingRepositoryClass;
