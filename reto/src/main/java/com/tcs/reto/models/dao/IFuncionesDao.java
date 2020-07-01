package com.tcs.reto.models.dao;

import com.tcs.reto.models.entity.Empleado;
import com.tcs.reto.models.entity.Funciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFuncionesDao extends JpaRepository<Empleado, Integer> {

    @Query("from Funciones")
    List<Funciones> findAllFunciones();
}
