package co.gov.mincomercio.repository.jpa_repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.gov.mincomercio.model.Subpartida;
import co.gov.mincomercio.repository.SubpartidaRepository;

@Stateless
public class SubpartidaJpaRepository implements SubpartidaRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Subpartida findByNumeroSubpartida(int numeroSubpartida) {
        return em.find(Subpartida.class, numeroSubpartida);
    }
    
}