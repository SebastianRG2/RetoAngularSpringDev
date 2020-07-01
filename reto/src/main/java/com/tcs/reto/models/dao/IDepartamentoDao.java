package com.tcs.reto.models.dao;

import com.tcs.reto.models.entity.Departamento;
import com.tcs.reto.models.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDepartamentoDao extends JpaRepository<Empleado, Integer> {

    @Query("from Departamento")
    List<Departamento> findAllDepartamentos();
}
