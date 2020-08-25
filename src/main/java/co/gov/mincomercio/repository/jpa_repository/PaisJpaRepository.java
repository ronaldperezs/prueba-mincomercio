package co.gov.mincomercio.repository.jpa_repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.gov.mincomercio.model.Pais;
import co.gov.mincomercio.repository.PaisRepository;

@Stateless
public class PaisJpaRepository implements PaisRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pais findById(Long id) {
        return em.find(Pais.class, id);
    }

}