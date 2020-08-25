package co.gov.mincomercio.repository;

import javax.ejb.Local;

import co.gov.mincomercio.model.Subpartida;

@Local
public interface SubpartidaRepository {
    Subpartida findByNumeroSubpartida(int numeroSubpartida);
}