package co.gov.mincomercio.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SolicitudImportacion")
public class SolicitudImportacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consecutivo;
    @Column(name = "fechaRadicacion")
    private Date fechaRadicacion;
    private String nit;
    private Pais paisOrigen;
    private Pais paisEmbarque;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ProductoSolicitudImportacion> productosSolicitud;
    @Column(name = "valorTotalArancel")
    private double valorTotalArancel;

    protected SolicitudImportacion() {

    }
    
    public SolicitudImportacion(String nit, Pais paisOrigen, Pais paisEmbarque,
            List<ProductoSolicitudImportacion> productosSolicitud) {
        this.fechaRadicacion = new Date();
        this.nit = Objects.requireNonNull(nit);
        this.paisOrigen = Objects.requireNonNull(paisOrigen);
        this.paisEmbarque = Objects.requireNonNull(paisEmbarque);
        this.productosSolicitud = Objects.requireNonNull(productosSolicitud);
    }

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

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Pais getPaisEmbarque() {
        return paisEmbarque;
    }

    public void setPaisEmbarque(Pais paisEmbarque) {
        this.paisEmbarque = paisEmbarque;
    }

    public List<ProductoSolicitudImportacion> getProductosSolicitud() {
        return productosSolicitud;
    }

    public void setProductosSolicitud(List<ProductoSolicitudImportacion> productosSolicitud) {
        this.productosSolicitud = productosSolicitud;
    }

    public double getValorTotalArancel() {
        return valorTotalArancel;
    }

    public void setValorTotalArancel(double valorTotalArancel) {
        this.valorTotalArancel = valorTotalArancel;
    }    
    
}