package co.gov.mincomercio.business.business_logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.gov.mincomercio.business.AdministrarSolicitudImportacion;
import co.gov.mincomercio.business.exceptions.PaisBloqueadoException;
import co.gov.mincomercio.business.exceptions.SubpartidaNoEncontradaException;
import co.gov.mincomercio.common.ProductoSolicitudImportacionDto;
import co.gov.mincomercio.common.SolicitudImportacionDto;
import co.gov.mincomercio.model.EstadoPais;
import co.gov.mincomercio.model.Pais;
import co.gov.mincomercio.model.ProductoSolicitudImportacion;
import co.gov.mincomercio.model.SolicitudImportacion;
import co.gov.mincomercio.model.Subpartida;
import co.gov.mincomercio.model.UnidadMedida;
import co.gov.mincomercio.repository.PaisRepository;
import co.gov.mincomercio.repository.SolicitudImportacionRepository;
import co.gov.mincomercio.repository.SubpartidaRepository;
import co.gov.mincomercio.repository.UnidadMedidaRepository;

@Stateless
public class AdministradorSolicitudImportacion implements AdministrarSolicitudImportacion, Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private PaisRepository paisRepository;
    @EJB
    private UnidadMedidaRepository unidadMedidaRepository;
    @EJB
    private SubpartidaRepository subpartidaRepository;
    @EJB
    private SolicitudImportacionRepository solicitudImportacionRepository;

    @Override
    public SolicitudImportacionDto radicar(SolicitudImportacionDto solicitudImportacion) {
        Pais paisOrigen = validarProcedenciaAutorizada(solicitudImportacion.getIdPaisOrigen());
        Pais paisEmbarque = validarProcedenciaAutorizada(solicitudImportacion.getIdPaisEmbarque());
        List<ProductoSolicitudImportacion> productos = new ArrayList<ProductoSolicitudImportacion>();
        solicitudImportacion
                .setValorTotalArancel(calcularArancel(solicitudImportacion.getProductosSolicitud(), productos));
        SolicitudImportacion solicitudRadicada = new SolicitudImportacion(solicitudImportacion.getNit(), paisOrigen,
                paisEmbarque, productos);
        solicitudImportacionRepository.create(solicitudRadicada);
        solicitudImportacion.setFechaRadicacion(solicitudRadicada.getFechaRadicacion());
        solicitudImportacion.setConsecutivo(solicitudRadicada.getConsecutivo());
        return solicitudImportacion;
    }

    protected Pais validarProcedenciaAutorizada(long idPais) {
        Pais pais = paisRepository.findById(idPais);
        Objects.requireNonNull(pais,
                new StringBuilder().append("idPais ").append(idPais).append(" no encontrado").toString());
        if (pais.getEstado().equals(EstadoPais.BLOQUEADO)) {
            throw new PaisBloqueadoException(pais.getNombre());
        }
        return pais;
    }

    protected double calcularArancel(List<ProductoSolicitudImportacionDto> productosDto,
            List<ProductoSolicitudImportacion> productos) {
        double valorTotalArancel = 0.0;
        for (ProductoSolicitudImportacionDto productoDto : productosDto) {
            ProductoSolicitudImportacion producto = crearProducto(productoDto);
            calcularArancelParcial(producto);
            productos.add(producto);
            valorTotalArancel += producto.getValorArancel();
        }
        return valorTotalArancel;
    }

    protected void calcularArancelParcial(ProductoSolicitudImportacion producto) {
        producto.setValorArancel(producto.getCantidad() * producto.getPrecioUnidad()
                * (producto.getSubpartida().getPorcentajeArancel() / 100));
    }

    protected ProductoSolicitudImportacion crearProducto(ProductoSolicitudImportacionDto productoDto) {
        Subpartida subpartida = consultarSubpartida(productoDto);
        UnidadMedida unidadMedida = consultarUnidadMedida(productoDto);
        ProductoSolicitudImportacion producto = new ProductoSolicitudImportacion(productoDto.getProducto(), subpartida,
                productoDto.getCantidad(), unidadMedida, productoDto.getPrecioUnidad());
        return producto;
    }

    protected Subpartida consultarSubpartida(ProductoSolicitudImportacionDto producto) {
        Subpartida subpartida = subpartidaRepository.findByNumeroSubpartida(producto.getNumeroSubpartida());
        if (subpartida == null) {
            throw new SubpartidaNoEncontradaException(producto.getNumeroSubpartida());
        }
        return subpartida;
    }

    protected UnidadMedida consultarUnidadMedida(ProductoSolicitudImportacionDto producto) {
        UnidadMedida subpartida = unidadMedidaRepository.findById(producto.getIdUnidadMedida());
        if (subpartida == null) {
            throw new IllegalArgumentException();
        }
        return subpartida;
    }
}