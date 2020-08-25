package co.gov.mincomercio.application;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.gov.mincomercio.business.exceptions.SubpartidaNoEncontradaException;

@Provider
public class SubpartidaNoEncontradaExceptionMapper implements ExceptionMapper<SubpartidaNoEncontradaException> {

    @Override
    public Response toResponse(SubpartidaNoEncontradaException exception) {
        return Response.status(Response.Status.CONFLICT).header("Conflict-Reason", exception.getMessage()).build();
    }
}
