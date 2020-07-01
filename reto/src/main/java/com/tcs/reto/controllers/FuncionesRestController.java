package com.tcs.reto.controllers;

import com.tcs.reto.models.entity.Funciones;
import com.tcs.reto.models.services.IFuncionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api3")
public class FuncionesRestController {

    @Autowired
    private IFuncionesService funcionesService;

    @GetMapping("/empleados/funciones")
    public List<Funciones> listaFunciones() {
        return funcionesService.findAllFunciones();
    }
}
