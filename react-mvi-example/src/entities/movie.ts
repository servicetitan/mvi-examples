export interface Movie {
    title: string;
    year: number;
    imdbId: string;
    type: "movie" | "series" | "episode";
    posterUrl: string;
}
