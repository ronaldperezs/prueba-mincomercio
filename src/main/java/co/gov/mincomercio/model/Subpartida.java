package co.gov.mincomercio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subpartida")
public class Subpartida {
    @Id
    @Column(name = "numeroSubpartida")
    private int numeroSubpartida;
    @Column(name = "porcentajeArancel")
    private double porcentajeArancel;

    protected Subpartida() {

    }
    
    public Subpartida(int numeroSubpartida, double porcentajeArancel) {
        this.numeroSubpartida = numeroSubpartida;
        this.porcentajeArancel = porcentajeArancel;
    }

    public int getNumeroSubpartida() {
        return numeroSubpartida;
    }

    public void setNumeroSubpartida(int numeroSubpartida) {
        this.numeroSubpartida = numeroSubpartida;
    }

    public double getPorcentajeArancel() {
        return porcentajeArancel;
    }

    public void setPorcentajeArancel(double porcentajeArancel) {
        this.porcentajeArancel = porcentajeArancel;
    }
    
}