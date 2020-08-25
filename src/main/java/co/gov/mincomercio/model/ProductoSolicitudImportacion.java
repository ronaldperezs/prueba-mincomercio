package co.gov.mincomercio.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProductoSolicitudImportacion")
public class ProductoSolicitudImportacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String producto;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subpartida subpartida;
    private double cantidad;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UnidadMedida unidadMedida;
    private double precioUnidad;
    private double valorArancel;

    protected ProductoSolicitudImportacion() {

    }
    
    public ProductoSolicitudImportacion(String producto, Subpartida subpartida, double cantidad,
            UnidadMedida unidadMedida, double precioUnidad) {
        this.producto = Objects.requireNonNull(producto);
        this.subpartida = Objects.requireNonNull(subpartida);
        this.cantidad = cantidad;
        this.unidadMedida = Objects.requireNonNull(unidadMedida);
        this.precioUnidad = precioUnidad;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Subpartida getSubpartida() {
        return subpartida;
    }

    public void setSubpartida(Subpartida subpartida) {
        this.subpartida = subpartida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
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