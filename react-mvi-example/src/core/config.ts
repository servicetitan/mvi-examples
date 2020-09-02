import { injectable } from 'inversify';

const ConfigClass = class Config {
    public readonly omdbApiUrl = "https://www.omdbapi.com";
    public readonly omdbApiKey = "eff188ce";
    public readonly omdbApiExtraDelay = 1000;
}

export const Config =
    injectable()(ConfigClass) as typeof ConfigClass;
