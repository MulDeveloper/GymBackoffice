export class ClientesGym {
  idcliente: number;
  nombreCliente: string;
  apellidoCliente: string;
  emailCliente: string;
  nifCliente: string;
  telCliente: number;
  direccionCliente: string;
  idMatricula: number;
  fechaNacimiento: Date;

  public constructor(init?: Partial<ClientesGym>) {
      Object.assign(this, init);
  }


}
