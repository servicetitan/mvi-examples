import { injectable } from 'inversify'
import { Observable, BehaviorSubject } from 'rxjs';
import { Movie } from 'src/entities/movie';

export interface Paging {
    pageNumber: number;
    pageCount: number;
}

const MovieRepositoryClass = class MovieRepository {
    public get movies(): Observable<Movie[]> { return this._movies; }
    public get isLoading(): Observable<boolean> { return this._isLoading; }
    public get totalPages(): Observable<number> {return this._totalPages; }
    public get currentPage(): Observable<number> { return this._currentPage; }

    public readonly _movies: BehaviorSubject<Movie[]>
    public readonly _isLoading: BehaviorSubject<boolean>;
    public readonly _totalPages: BehaviorSubject<number>;
    public readonly _currentPage: BehaviorSubject<number>;

    constructor() {
        this._movies = new BehaviorSubject<Movie[]>([]);
        this._isLoading = new BehaviorSubject<boolean>(false);
        this._totalPages = new BehaviorSubject<number>(1);
        this._currentPage = new BehaviorSubject<number>(1);
    }

    public setLoading() {
        this._isLoading.next(true);
    }

    public clearLoading() {
        this._isLoading.next(false);
    }

    public setMovies(movies: Movie[]) {
        this._movies.next(movies);
    }

    public setCurrentPage(pageNumber: number) {
        this._currentPage.next(pageNumber);
    }

    public setTotalPages(totalPages: number) {
        this._totalPages.next(totalPages);
    }

    public reset() {
        this._movies.next([]);
        this._currentPage.next(1);
        this._totalPages.next(1);
        this._isLoading.next(false);
    }
}

export const MovieRepository = 
    injectable()(MovieRepositoryClass) as typeof MovieRepositoryClass;
