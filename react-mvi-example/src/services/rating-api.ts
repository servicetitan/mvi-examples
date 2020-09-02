import { injectable } from 'inversify';

const RatingApiClass = class RatingApi {
    // this API sucks - it's laggy and unreilable.
    private readonly baseRequestDelay: number = 1;
    private readonly requestDelayVariance: number = 6;
    private readonly failureRate: number = 0.3

    private async delay(): Promise<void> {
        const timeout = (this.baseRequestDelay + Math.random() * this.requestDelayVariance) * 1000;
        return new Promise((resolve) => {
            setTimeout(resolve, timeout);
        });
    }

    private isFailed(): boolean {
        return false;// Math.random() < this.failureRate;
    }

    public async setRating(imdbId: string, rating: number) {
        await this.delay();
        if (this.isFailed()) {
            throw new Error("whoa");
        }
    }

    public async removeRating(imdbId: string) {
        await this.delay();
        if (this.isFailed()) {
            throw new Error("oops");
        }
    }
}

export const RatingApi =
    injectable()(RatingApiClass) as typeof RatingApiClass;
