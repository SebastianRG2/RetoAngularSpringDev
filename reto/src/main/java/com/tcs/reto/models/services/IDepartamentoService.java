package com.tcs.reto.models.services;

import com.tcs.reto.models.entity.Departamento;

import java.util.List;

public interface IDepartamentoService {
    List<Departamento> findAllDepartamentos();
}
