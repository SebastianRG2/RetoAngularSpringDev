import { Component, OnInit } from '@angular/core';
import { Empleado } from './empleado';
import {EmpleadoService} from './empleado.service';
import {Router, ActivatedRoute} from '@angular/router'
import swal from 'sweetalert2';
import { Departamento } from './departamento';
import { Funciones } from './funciones';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  empleado: Empleado = new Empleado();
  departamentos: Departamento[];
  funciones: Funciones[];
  titulo:string = "Crear empleado";
  checked: boolean = true;
  constructor(
    private empleadoService: EmpleadoService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarEmpleado();
    this.cargarDepartamento();
    this.cargarFunciones();
  }

  cargarFunciones(): void {
   this.empleadoService.getFunciones().subscribe((funciones) => this.funciones = funciones);
  }

  cargarDepartamento(): void {
   this.empleadoService.getDepartamentos().subscribe((departamentos) => this.departamentos = departamentos);
  }

  cargarEmpleado(): void {
    this.activatedRoute.params.subscribe(params =>{
      let id = params [`id`]
      if(id){
        this.empleadoService.getEmpleado(id).subscribe((empleado) => this.empleado = empleado);
      }
    });
  }

  create(): void{
    this.empleadoService.create(this.empleado).subscribe(
      json => {
      this.router.navigate(['/empleados'])
      swal.fire('Nuevo empleado',  `${json.mensaje}: ${json.empleado.nombre}`,'success');
      });
  }

  update():void {
    this.empleadoService.update(this.empleado).subscribe(
      json => {
        this.router.navigate(['/empleados'])
        swal.fire('Empleado actualizado', `${json.mensaje}: ${json.empleado.nombre}`, 'success');
      });
  }

  compararDepartamento(o1:Departamento , o2:Departamento): boolean{
    return o1 == null || o2 == null || o1 == undefined || o2 == undefined? false: o1.id == o2.id;
  }

  compararFunciones(o1:Funciones , o2:Funciones): boolean{
    return o1 == null || o2 == null || o1 == undefined || o2 == undefined? false: o1.id == o2.id;
  }

  changeValue(value){
    this.checked = !value;
  }
}
