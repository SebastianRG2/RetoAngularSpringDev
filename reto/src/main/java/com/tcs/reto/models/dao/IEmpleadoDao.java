package com.tcs.reto.models.dao;

import com.tcs.reto.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer> {

}
