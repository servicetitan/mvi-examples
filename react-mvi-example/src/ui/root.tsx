import React, { useState } from 'react';
import { getService } from 'src/core/ioc';
import { useObservable } from "rxjs-hooks";
import { MovieRepository } from 'src/repositories/movie-repository';
import { Button, Icon, Dimmer, Input, Loader, Container, Menu } from 'semantic-ui-react';
import { EventDispatcher } from 'src/core/event-dispatcher';
import { MoviesRequested } from 'src/events/movies-requested';
import { MovieList } from 'src/ui/movie-list';




export const Root: React.FC = () => {
    const movieRepository = getService(MovieRepository);
    const eventDispatcher = getService(EventDispatcher);
    const [searchValue, setSearchValue] = useState("");

    const loading = useObservable(() => movieRepository.isLoading, false);
    const movies = useObservable(() => movieRepository.movies, []);
    return (
        <Container textAlign="justified">
            <Menu size="huge" fluid>
                <Input
                    placeholder="Search..."
                    value={searchValue} 
                    onChange={(e, data) => setSearchValue(data.value || "")}
                />
                <Button 
                    onClick={() => eventDispatcher.dispatch(new MoviesRequested({
                        searchString: searchValue
                    }))}
                    disabled={loading || !searchValue}
                    icon
                    labelPosition="left"
                >
                    <Icon name="search" />
                    Search
                </Button>
            </Menu>
            <div>
                <Dimmer active={loading} >
                    <Loader>Loading movies...</Loader>
                </Dimmer>
                <MovieList movies={movies} />
            </div>
        </Container>
    );
}
