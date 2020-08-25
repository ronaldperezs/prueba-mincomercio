package co.gov.mincomercio.repository.jpa_repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.gov.mincomercio.model.UnidadMedida;
import co.gov.mincomercio.repository.UnidadMedidaRepository;

@Stateless
public class UnidadMedidaJpaRepository implements UnidadMedidaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UnidadMedida findById(Long id) {
        return em.find(UnidadMedida.class, id);
    }

}