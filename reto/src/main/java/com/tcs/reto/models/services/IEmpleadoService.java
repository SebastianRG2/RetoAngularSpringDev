package com.tcs.reto.models.services;

import com.tcs.reto.models.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> findAll();

    Empleado findById(Integer id);

    Empleado save(Empleado empleado);

    void delete(Integer id);
}
