package com.tcs.reto.controllers;

import com.tcs.reto.models.entity.Empleado;
import com.tcs.reto.models.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> index() {
        return empleadoService.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Empleado empleado = null;
        Map<String, Object> response = new HashMap<>();
        try {
            empleado = empleadoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (empleado == null) {
            response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> create(@RequestBody Empleado empleado) {
        Empleado empleadoNuevo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            empleadoNuevo = empleadoService.save(empleado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar insert a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "El empleado ha sido creado con exito");
        response.put("empleado", empleadoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> update(@RequestBody Empleado empleado, @PathVariable Integer id) {
        Empleado empleadoActual = empleadoService.findById(id);
        Empleado empleadoActualizado = null;
        Map<String, Object> response = new HashMap<>();
        if (empleadoActual == null) {
            response.put("mensaje", "Error: no se pudo editar ,el empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setApellido(empleado.getApellido());
            empleadoActual.setNumero_documento(empleado.getNumero_documento());
            empleadoActual.setCorreo(empleado.getCorreo());
            empleadoActual.setTelefono(empleado.getTelefono());
            empleadoActual.setActivo(empleado.getActivo());
            empleadoActual.setSalario(empleado.getSalario());
            empleadoActual.setDepartamento(empleado.getDepartamento());
            empleadoActualizado = empleadoService.save(empleadoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El empleado  ha sido actualizado con exito");
        response.put("empleado", empleadoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            empleadoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El empleado eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
