import React, { useState } from 'react';
import { getService } from 'src/core/ioc';
import { useObservable } from "rxjs-hooks";
import { MovieRepository } from 'src/repositories/movie-repository';
import { Button, Icon, Form, Container, Grid } from 'semantic-ui-react';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { MoviesRequested } from 'src/events/movies-requested';
import { MovieList } from 'src/ui/movie-list';


function handleSearch(dispatcher: EventDispatcher, searchString: string) {
    dispatcher.dispatch(new MoviesRequested({
        searchString
    }));
}

export const Root: React.FC = () => {
    const movieRepository = getService(MovieRepository);
    const eventDispatcher = getService(EventDispatcher);
    const [searchValue, setSearchValue] = useState("");

    const loading = useObservable(() => movieRepository.isLoading, false);
    const movies = useObservable(() => movieRepository.movies, []);
    return (
        <Container textAlign="justified">
            <Grid size="huge" fluid>
                <Grid.Column width={13}>
                    <Form onSubmit={() => handleSearch(eventDispatcher, searchValue)}>
                        <Form.Input
                            fluid
                            placeholder="Search..."
                            value={searchValue} 
                            disabled={loading}
                            onChange={(e, data) => setSearchValue(data.value || "")}
                            
                        />
                    </Form>
                </Grid.Column>
                <Grid.Column>
                    <Button 
                        onClick={() => handleSearch(eventDispatcher, searchValue)}
                        disabled={loading || !searchValue}
                        icon
                        labelPosition="left"
                    >
                        <Icon name={loading ? "circle notch" : "search"} loading={loading} />
                        Search
                    </Button>
                </Grid.Column>
            </Grid>
            <MovieList movies={movies} />
        </Container>
    );
}
