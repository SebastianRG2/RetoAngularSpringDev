import {Departamento} from './departamento';

export class Empleado {
  id: number;
  nombre: string;
  apellido:string;
  numero_documento:string;
  correo:string;
  telefono:string;
  activo:boolean;
  salario:DoubleRange;
  departamento: Departamento;
}
