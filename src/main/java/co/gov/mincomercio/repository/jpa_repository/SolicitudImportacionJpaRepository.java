package co.gov.mincomercio.repository.jpa_repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.gov.mincomercio.model.SolicitudImportacion;
import co.gov.mincomercio.repository.SolicitudImportacionRepository;

@Stateless
public class SolicitudImportacionJpaRepository implements SolicitudImportacionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(SolicitudImportacion solicitud) {
        em.persist(solicitud);
    }
    
}