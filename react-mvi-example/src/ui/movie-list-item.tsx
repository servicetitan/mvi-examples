import React from 'react';
import { getService } from 'src/core/ioc';
import { useObservable } from "rxjs-hooks";
import { RatingRepository } from 'src/repositories/rating-repository';
import { Movie } from 'src/entities/movie';
import { Card, Image } from 'semantic-ui-react';
import { MovieRating } from 'src/ui/movie-rating';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { RatingRequested } from 'src/events/rating-requested';

export interface MovieListItemProps {
    movie: Movie
}

export const MovieListItem: React.FC<MovieListItemProps> = ({
    movie: { imdbId, posterUrl, title, type, year }
}) => {
    const ratingRepository = getService(RatingRepository);
    const eventDispatcher = getService(EventDispatcher);
    const ratings = useObservable(() => ratingRepository.ratings);
    const rating = ratings?.[imdbId] ?? null;
    return (
        <Card>
            <Image wrapped ui={false} src={posterUrl} />
            <Card.Content>
                <Card.Header>{title}</Card.Header>
                <Card.Meta>
                    {type}, released in {year}
                </Card.Meta>
            </Card.Content>
            <Card.Content extra>
                <MovieRating 
                    rating={rating}
                    onSetRating={newRating => eventDispatcher.dispatch(new RatingRequested({ imdbId, rating: newRating }))}
                    onClear={() => eventDispatcher.dispatch(new RatingRequested({ imdbId, rating: null }))}
                />
            </Card.Content>
        </Card>
    );
}
