package co.gov.mincomercio.business.exceptions;

public class SubpartidaNoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SubpartidaNoEncontradaException(int numeroSubpartida) {
        super(new StringBuilder().append("El numero subpartida ").append(numeroSubpartida).append(" no fue encontrada")
                .toString());
    }

}