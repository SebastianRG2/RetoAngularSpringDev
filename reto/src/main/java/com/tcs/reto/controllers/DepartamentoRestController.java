package com.tcs.reto.controllers;

import com.tcs.reto.models.entity.Departamento;
import com.tcs.reto.models.services.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api2")
public class DepartamentoRestController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/empleados/departamentos")
    public List<Departamento> listaDepartamentos(){
        return departamentoService.findAllDepartamentos();
    }
}
