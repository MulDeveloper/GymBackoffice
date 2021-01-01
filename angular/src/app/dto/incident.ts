export class Incident {
    id: number;
    dateIncident: Date;
    status: Boolean;
    cost: number;
    description: string;

    public constructor(init?: Partial<Incident>) {
        Object.assign(this, init);
    }

}
