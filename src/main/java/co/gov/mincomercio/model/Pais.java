package co.gov.mincomercio.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pais")
public class Pais {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private EstadoPais estado;

    protected Pais() {
        
    }

    public Pais(String nombre, EstadoPais estado) {
        this.nombre = Objects.requireNonNull(nombre);
        this.estado = Objects.requireNonNull(estado);
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoPais getEstado() {
        return estado;
    }

    public void setEstado(EstadoPais estado) {
        this.estado = estado;
    }
    
}