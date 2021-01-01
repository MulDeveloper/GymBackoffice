export class Gymclass {
    idclass: number;
    dateClass: Date;
    timeClass: string;
    duration: string;
    intensity: number;
    description: string;
    staff: number;
    clients: number[];

    public constructor(init?: Partial<Gymclass>) {
        Object.assign(this, init);
    }

}
