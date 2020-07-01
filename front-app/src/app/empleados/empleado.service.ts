import { Injectable } from '@angular/core';
import { Empleado } from './empleado';
import { Departamento } from './departamento'
import { Funciones } from './funciones';
import { Observable, throwError, from } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';


@Injectable()
export class EmpleadoService {
  private urlEndPoint: string = 'http://localhost:8080/api/empleados';
  private urlEndPoint2: string = 'http://localhost:8080/api2/empleados';
  private urlEndPoint3: string = 'http://localhost:8080/api3/empleados';
  private httpHeaders = new HttpHeaders({'Content-Type' : 'application/json'})
  constructor(private http: HttpClient, private router: Router) { }

 getFunciones(): Observable<Funciones[]> {
    return this.http.get<Funciones[]>(this.urlEndPoint3 + '/funciones');
  }

  getDepartamentos(): Observable<Departamento[]> {
    return this.http.get<Departamento[]>(this.urlEndPoint2 + '/departamentos');
  }

  getEmpleados(): Observable<Empleado[]> {
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Empleado[])
    );
  }

  create(empleado: Empleado):Observable<any>{
    return this.http.post<any>(this.urlEndPoint, empleado, {headers: this.httpHeaders}).pipe(
      catchError( e => {
        Swal.fire('Error al crear', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  getEmpleado(id): Observable <Empleado>{
    return this.http.get<Empleado>(`${this.urlEndPoint}/${id}`).pipe(
      catchError( e => {
        this.router.navigate(['/empleados'])
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  update(empleado: Empleado):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${empleado.id}`, empleado,  {headers: this.httpHeaders}).pipe(
      catchError( e => {
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  delete(id: number): Observable<Empleado>{
    return this.http.delete<Empleado>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError( e => {
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }
}
