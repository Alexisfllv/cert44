package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.service.ICRUD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class ICRUDServiceImpl <T,ID> implements ICRUD<T,ID> {

    //
    protected abstract JpaRepository<T, ID> getRepo();

    @Override
    public T registrar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
