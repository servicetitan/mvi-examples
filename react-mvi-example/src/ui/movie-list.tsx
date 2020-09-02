import React from 'react';
import { Button, Card, Image } from 'semantic-ui-react'
import { Movie } from 'src/entities/movie';
import { MovieListItem } from 'src/ui/movie-list-item';

export interface MovieListProps {
    movies: Movie[]
}

export const MovieList: React.FC<MovieListProps> = ({ movies }) => {

    return (
        <Card.Group itemsPerRow={4}>
            {movies.map(m => <MovieListItem key={m.imdbId} movie={m} />)}
        </Card.Group>
    );
};
