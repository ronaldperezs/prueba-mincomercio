package co.gov.mincomercio.business.exceptions;

public class PaisBloqueadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaisBloqueadoException(String nombrePais) {
        super(new StringBuilder().append("El pais ").append(nombrePais)
                .append(" se encuentra bloqueado para importacion").toString());
    }

}