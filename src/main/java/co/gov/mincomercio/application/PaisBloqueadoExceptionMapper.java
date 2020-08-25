package co.gov.mincomercio.application;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.gov.mincomercio.business.exceptions.PaisBloqueadoException;

@Provider
public class PaisBloqueadoExceptionMapper implements ExceptionMapper<PaisBloqueadoException> {

    @Override
    public Response toResponse(PaisBloqueadoException exception) {
        return Response.status(Response.Status.CONFLICT).header("Conflict-Reason", exception.getMessage()).build();
    }

}