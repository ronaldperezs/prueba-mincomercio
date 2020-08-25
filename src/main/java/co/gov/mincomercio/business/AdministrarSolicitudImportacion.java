package co.gov.mincomercio.business;

import javax.ejb.Local;

import co.gov.mincomercio.common.SolicitudImportacionDto;

@Local
public interface AdministrarSolicitudImportacion {
    SolicitudImportacionDto radicar(SolicitudImportacionDto solicitudImportacion);
}