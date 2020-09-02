import React from 'react';
import { Rating } from 'src/repositories/rating-repository';
import { Button, Segment, Icon } from 'semantic-ui-react';

export interface MovieRatingProps {
    rating: Rating | null;
    onClear?: () => void;
    onSetRating?: (rating: number) => void;
}

function renderStar(index: number, rating: Rating | null, onSetRating?: (rating: number) => void) {
    const onClick = () => {
        if (onSetRating) {
            onSetRating(index + 1);
        }
    }
    if (!rating) {
        return <Icon key={index} name="star outline" color="grey" link onClick={onClick} />;
    }
    if (rating === "pending") {
        return <Icon key={index} disabled name="star outline" color="yellow" loading />;
    }
    const iconName = (index >= rating) ? "star outline" : "star";
    return <Icon key={index} name={iconName} color="yellow" link onClick={onClick} />
}

export const MovieRating: React.FC<MovieRatingProps> = ({
    rating,
    onClear,
    onSetRating
}) => {
    return (
        <Segment.Inline>
            {[0,1,2,3,4].map(i => renderStar(i, rating, onSetRating))}
            {rating && 
                <Button icon onClick={onClear} disabled={rating === "pending"}>
                    <Icon name="dont" />
                    Clear
                </Button>
            }
        </Segment.Inline>
    );
};
