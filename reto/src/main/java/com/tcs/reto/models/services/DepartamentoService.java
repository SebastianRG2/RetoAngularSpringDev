package com.tcs.reto.models.services;

import com.tcs.reto.models.dao.IDepartamentoDao;
import com.tcs.reto.models.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private IDepartamentoDao departamentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> findAllDepartamentos() {
        return departamentoDao.findAllDepartamentos();
    }
}
