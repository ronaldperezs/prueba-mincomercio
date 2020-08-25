package co.gov.mincomercio.common;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoSolicitudImportacionDto {
    @NotBlank
    private String producto;
    @NotNull
    private int numeroSubpartida;
    @NotNull
    private double cantidad;
    @NotNull
    private long idUnidadMedida;
    @NotNull
    private double precioUnidad;
    private double valorArancel;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getNumeroSubpartida() {
        return numeroSubpartida;
    }

    public void setNumeroSubpartida(int numeroSubpartida) {
        this.numeroSubpartida = numeroSubpartida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(long idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getValorArancel() {
        return valorArancel;
    }

    public void setValorArancel(double valorArancel) {
        this.valorArancel = valorArancel;
    }
    
}