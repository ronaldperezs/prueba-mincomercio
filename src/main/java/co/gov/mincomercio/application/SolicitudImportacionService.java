package co.gov.mincomercio.application;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.gov.mincomercio.business.AdministrarSolicitudImportacion;
import co.gov.mincomercio.common.SolicitudImportacionDto;

@Path("/solicitud-importacion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitudImportacionService {
    @EJB
    private AdministrarSolicitudImportacion administrarSolicitudImportacion;

    @POST
    public Response radicar(@Valid SolicitudImportacionDto solicitudImportacion) {
        SolicitudImportacionDto solicitudRadicada = administrarSolicitudImportacion.radicar(solicitudImportacion);
        return Response.ok(solicitudRadicada).build();
    }
}