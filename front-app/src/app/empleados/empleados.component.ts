import { Component, OnInit } from '@angular/core';
import { Empleado } from './empleado';
import { EmpleadoService } from './empleado.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './empleados.component.html'
})
export class EmpleadosComponent implements OnInit {

  empleados: Empleado[];

  constructor(private empleadoService: EmpleadoService) { }

  ngOnInit() {
    this.empleadoService.getEmpleados().subscribe(
      empleados => this.empleados = empleados
    );
  }

  delete(empleado: Empleado): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar al empleado ${empleado.nombre} ${empleado.apellido}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar empleado',
      cancelButtonText: 'No, cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.empleadoService.delete(empleado.id).subscribe(
          response =>{
            this.empleados = this.empleados.filter(emp => emp !== empleado)
            swalWithBootstrapButtons.fire(
              'Empleado eliminado!',
              `Empleado ${empleado.nombre} eliminado con exito`,
              'success'
            )
          }
        )
        }
      } 
    );
  }
}
