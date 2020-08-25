package co.gov.mincomercio.repository;

import javax.ejb.Local;

import co.gov.mincomercio.model.Pais;

@Local
public interface PaisRepository {
    Pais findById(Long id);
}