package co.gov.mincomercio.repository;

import javax.ejb.Local;

import co.gov.mincomercio.model.SolicitudImportacion;

@Local
public interface SolicitudImportacionRepository {
    void create(SolicitudImportacion solicitud); 
}