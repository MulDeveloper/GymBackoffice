export class Personal {
    idpersonal: number;
    nombrePersonal: string;
    apellidoPersonal: string;
    emailPersonal: string;
    nifPersonal: string;
    telPersonal: number;
    direccionPersonal: string;
    sueldoBruto: number;
    fechaNacimiento: Date;
    fechaAlta: Date;
    rol: string;

    public constructor(init?: Partial<Personal>) {
        Object.assign(this, init);
    }

}
