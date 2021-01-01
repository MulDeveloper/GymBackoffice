export class Membership {
    idmatricula: number;
    idcliente: number;
    idtarifa: number;
    fechaAlta: Date;
    fechaAbonadoHasta: Date;

    public constructor(init?: Partial<Membership>) {
        Object.assign(this, init);
    }

}
