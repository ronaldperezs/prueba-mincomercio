package co.gov.mincomercio.repository;

import javax.ejb.Local;

import co.gov.mincomercio.model.UnidadMedida;

@Local
public interface UnidadMedidaRepository {
    UnidadMedida findById(Long id);
}