package com.tcs.reto.models.services;

import com.tcs.reto.models.dao.IFuncionesDao;
import com.tcs.reto.models.entity.Funciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionesService implements IFuncionesService {

    @Autowired
    private IFuncionesDao funcionesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Funciones> findAllFunciones() {
        return funcionesDao.findAllFunciones();
    }
}
