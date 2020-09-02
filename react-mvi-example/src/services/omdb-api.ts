import { injectable } from 'inversify';
import axios from 'axios'
import { Config } from 'src/core/config'
import { getService } from 'src/core/ioc';
import { delay } from 'src/utils/delay'

type MovieType = "movie" | "series" | "episode"

export interface SearchResult {
    Title: string;
    Year: string;
    imdbID: string;
    Type: MovieType;
    Poster: string;
}

export interface OmdbApiSearchResponse {
    Response: "True";
    Search: SearchResult[];
    totalResults: string;
}

interface OmdbApiErrorResponse {
    Response: "False";
    Error: string;
}

interface SearchParams {
    title: string;
    page?: number;
    year?: number;
    type?: MovieType
}



function prepareParams(params: SearchParams) {
    var result: Record<string, string> = { };
    if (params.title) {
        result.s = params.title;
    }
    if (params.year) {
        result.y = `${params.year}`;
    }
    if (params.type) {
        result.type = params.type;
    }
    if (params.page) {
        result.page = `${params.page}`;
    }
    return result;
}

type OmdbApiResponse  = OmdbApiSearchResponse | OmdbApiErrorResponse;

const OmdbApiClass = class OmdbApi {
    private get config() { return getService(Config); }

    public async search(params: SearchParams) : Promise<OmdbApiSearchResponse> {
        const requestParams = prepareParams(params);
        await delay(this.config.omdbApiExtraDelay);
        const result = await axios.get<OmdbApiResponse>(this.config.omdbApiUrl, {
            params: {
                apikey: this.config.omdbApiKey,
                ...requestParams
            }
        });
        if (result.status === 200 && result.data.Response === "True") {
            return result.data
        } else {
            const err = (result.data as OmdbApiErrorResponse).Error;
            throw new Error(`OMDB API error [${result.status} ${result.statusText}]: ${err}`);
        }
    }
}

export const OmdbApi =
    injectable()(OmdbApiClass) as typeof OmdbApiClass;
