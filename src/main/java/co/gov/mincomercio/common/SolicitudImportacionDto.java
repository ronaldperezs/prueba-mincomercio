package co.gov.mincomercio.common;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitudImportacionDto {
    private Long consecutivo;
    private Date fechaRadicacion;
    @NotBlank
    private String nit;
    @NotNull
    private long idPaisOrigen;
    @NotNull
    private long idPaisEmbarque;
    @NotNull
    @Valid
    private List<ProductoSolicitudImportacionDto> productosSolicitud;
    private double valorTotalArancel;

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Long getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(long idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    public Long getIdPaisEmbarque() {
        return idPaisEmbarque;
    }

    public void setIdPaisEmbarque(long idPaisEmbarque) {
        this.idPaisEmbarque = idPaisEmbarque;
    }

    public List<ProductoSolicitudImportacionDto> getProductosSolicitud() {
        return productosSolicitud;
    }

    public void setProductosSolicitud(List<ProductoSolicitudImportacionDto> productosSolicitud) {
        this.productosSolicitud = productosSolicitud;
    }

    public double getValorTotalArancel() {
        return valorTotalArancel;
    }

    public void setValorTotalArancel(double valorTotalArancel) {
        this.valorTotalArancel = valorTotalArancel;
    }

}