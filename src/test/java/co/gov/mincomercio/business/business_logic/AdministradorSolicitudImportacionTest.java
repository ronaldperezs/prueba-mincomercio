package co.gov.mincomercio.business.business_logic;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.gov.mincomercio.business.exceptions.PaisBloqueadoException;
import co.gov.mincomercio.business.exceptions.SubpartidaNoEncontradaException;
import co.gov.mincomercio.common.ProductoSolicitudImportacionDto;
import co.gov.mincomercio.common.SolicitudImportacionDto;
import co.gov.mincomercio.model.EstadoPais;
import co.gov.mincomercio.model.Pais;
import co.gov.mincomercio.model.ProductoSolicitudImportacion;
import co.gov.mincomercio.model.Subpartida;
import co.gov.mincomercio.model.UnidadMedida;
import co.gov.mincomercio.repository.PaisRepository;
import co.gov.mincomercio.repository.SolicitudImportacionRepository;
import co.gov.mincomercio.repository.SubpartidaRepository;
import co.gov.mincomercio.repository.UnidadMedidaRepository;

@ExtendWith(MockitoExtension.class)
public class AdministradorSolicitudImportacionTest {

    @Mock
    PaisRepository paisRepository;
    @Mock
    UnidadMedidaRepository unidadMedidaRepository;
    @Mock
    SubpartidaRepository subpartidaRepository;
    @Mock
    SolicitudImportacionRepository solicitudImportacionRepository;
    @InjectMocks
    private AdministradorSolicitudImportacion administradorSolicitudImportacion;

    @Test
    public void validarProcedenciaPaisBloqueado() {
        Pais embarque = new Pais("Ecuador", EstadoPais.BLOQUEADO);
        Pais origen = new Pais("Taiwan", EstadoPais.BLOQUEADO);
        List<ProductoSolicitudImportacionDto> productos = new ArrayList<ProductoSolicitudImportacionDto>();
        SolicitudImportacionDto solicitudImportacion = new SolicitudImportacionDto();
        solicitudImportacion.setNit("9003390499");
        solicitudImportacion.setIdPaisOrigen(1L);
        solicitudImportacion.setIdPaisEmbarque(2L);
        solicitudImportacion.setProductosSolicitud(productos);
        List<Pais> paises = new ArrayList<Pais>();
        paises.add(origen);
        paises.add(embarque);
        when(paisRepository.findById(1L)).thenReturn(origen);
        when(paisRepository.findById(2L)).thenReturn(embarque);

        Assertions.assertThrows(PaisBloqueadoException.class, () -> administradorSolicitudImportacion
                .validarProcedenciaAutorizada(solicitudImportacion.getIdPaisOrigen()));
                Assertions.assertThrows(PaisBloqueadoException.class, () -> administradorSolicitudImportacion
                .validarProcedenciaAutorizada(solicitudImportacion.getIdPaisEmbarque()));
    }

    @Test
    public void validarProcedenciaExitosa() {
        Pais embarque = new Pais("Ecuador", EstadoPais.ACTIVO);
        Pais origen = new Pais("Taiwan", EstadoPais.ACTIVO);
        List<ProductoSolicitudImportacionDto> productos = new ArrayList<ProductoSolicitudImportacionDto>();
        SolicitudImportacionDto solicitudImportacion = new SolicitudImportacionDto();
        solicitudImportacion.setNit("9003390499");
        solicitudImportacion.setIdPaisOrigen(1L);
        solicitudImportacion.setIdPaisEmbarque(2L);
        solicitudImportacion.setProductosSolicitud(productos);
        List<Pais> paises = new ArrayList<Pais>();
        paises.add(origen);
        paises.add(embarque);
        when(paisRepository.findById(1L)).thenReturn(origen);
        when(paisRepository.findById(2L)).thenReturn(origen);

        Assertions.assertDoesNotThrow(() -> administradorSolicitudImportacion
                .validarProcedenciaAutorizada(solicitudImportacion.getIdPaisEmbarque()));
        Assertions.assertDoesNotThrow(() -> administradorSolicitudImportacion
                .validarProcedenciaAutorizada(solicitudImportacion.getIdPaisOrigen()));
    }

    @Test
    public void consultarSubpartidaExitosa() {
        Subpartida subpartida = new Subpartida(12345678, 10);
        ProductoSolicitudImportacionDto producto = new ProductoSolicitudImportacionDto();
        producto.setProducto("TV");
        producto.setNumeroSubpartida(12345678);
        producto.setCantidad(4);
        producto.setIdUnidadMedida(1);
        producto.setPrecioUnidad(600000);
        when(subpartidaRepository.findByNumeroSubpartida(12345678)).thenReturn(subpartida);
        Assertions.assertDoesNotThrow(() -> administradorSolicitudImportacion.consultarSubpartida(producto));
    }

    @Test
    public void consultarSubpartidaNoEncontrada() {
        ProductoSolicitudImportacionDto producto = new ProductoSolicitudImportacionDto();
        producto.setProducto("TV");
        producto.setNumeroSubpartida(12345678);
        producto.setCantidad(4);
        producto.setIdUnidadMedida(1);
        producto.setPrecioUnidad(600000);
        when(subpartidaRepository.findByNumeroSubpartida(12345678)).thenReturn(null);
        Assertions.assertThrows(SubpartidaNoEncontradaException.class,
                () -> administradorSolicitudImportacion.consultarSubpartida(producto));
    }

    @Test
    public void calcularArancelParcialExitoso() {
        Subpartida subpartida = new Subpartida(12345678, 10);
        UnidadMedida unidadMedida = new UnidadMedida("Kg");
        ProductoSolicitudImportacion producto = new ProductoSolicitudImportacion("TV", subpartida, 5, 
                unidadMedida, 700000);
        administradorSolicitudImportacion.calcularArancelParcial(producto);
        Assertions.assertEquals(350000, producto.getValorArancel());
    }

    @Test
    public void calcularArancelExitoso() {
        Subpartida subpartida = new Subpartida(12345678, 10);
        UnidadMedida unidadMedida = new UnidadMedida("cajas");
        Subpartida subpartida2 = new Subpartida(123456789, 15);
        ProductoSolicitudImportacionDto producto = new ProductoSolicitudImportacionDto();
        producto.setProducto("TV");
        producto.setNumeroSubpartida(12345678);
        producto.setCantidad(5);
        producto.setIdUnidadMedida(1L);
        producto.setPrecioUnidad(700000);
        ProductoSolicitudImportacionDto producto2 = new ProductoSolicitudImportacionDto();
        producto2.setProducto("AIRE ACONDICIONADO");
        producto2.setNumeroSubpartida(123456789);
        producto2.setCantidad(10);
        producto2.setIdUnidadMedida(1L);
        producto2.setPrecioUnidad(900000);
        List<ProductoSolicitudImportacion> productos = new ArrayList<ProductoSolicitudImportacion>();
        List<ProductoSolicitudImportacionDto> productosDto = new ArrayList<ProductoSolicitudImportacionDto>();
        productosDto.add(producto);
        productosDto.add(producto2);
        when(subpartidaRepository.findByNumeroSubpartida(12345678)).thenReturn(subpartida);
        when(subpartidaRepository.findByNumeroSubpartida(123456789)).thenReturn(subpartida2);
        when(unidadMedidaRepository.findById(1L)).thenReturn(unidadMedida);
        double valorTotal = administradorSolicitudImportacion.calcularArancel(productosDto, productos);
        Assertions.assertEquals(1700000, valorTotal);
    }

    // @Test
    // public void radicarExitoso() {
    // Subpartida subpartida = new Subpartida(12345678, 10);
    // Subpartida subpartida2 = new Subpartida(123456789, 15);
    // ProductoSolicitudImportacionDto productoDto = new
    // ProductoSolicitudImportacionDto();
    // productoDto.setProducto("TV");
    // productoDto.setNumeroSubpartida(12345678);
    // productoDto.setCantidad(5);
    // productoDto.setIdUnidadMedida(1);
    // productoDto.setPrecioUnidad(700000);
    // ProductoSolicitudImportacionDto productoDto2 = new
    // ProductoSolicitudImportacionDto();
    // productoDto2.setProducto("AIRE ACONDICIONADO");
    // productoDto2.setNumeroSubpartida(123456789);
    // productoDto2.setCantidad(10);
    // productoDto2.setIdUnidadMedida(1);
    // productoDto2.setPrecioUnidad(900000);
    // List<ProductoSolicitudImportacionDto> productosDto = new
    // ArrayList<ProductoSolicitudImportacionDto>();
    // productosDto.add(productoDto);
    // productosDto.add(productoDto2);
    // List<ProductoSolicitudImportacion> productos = new
    // ArrayList<ProductoSolicitudImportacion>();
    // ProductoSolicitudImportacion producto = new
    // ProductoSolicitudImportacion("TV", subpartida, 5, 1, 700000);
    // ProductoSolicitudImportacion producto2 = new
    // ProductoSolicitudImportacion("AIRE ACONDICIONADO", subpartida2, 10,
    // 1, 900000);
    // productos.add(producto);
    // productos.add(producto2);
    // SolicitudImportacionDto solicitudImportacionDto = new
    // SolicitudImportacionDto();
    // solicitudImportacionDto.setNit("900339049");
    // solicitudImportacionDto.setIdPaisOrigen(1L);
    // solicitudImportacionDto.setIdPaisEmbarque(2L);
    // solicitudImportacionDto.setProductosSolicitud(productosDto);
    // Pais embarque = new Pais("Ecuador", EstadoPais.ACTIVO);
    // Pais origen = new Pais("TAIWAN", EstadoPais.ACTIVO);
    // List<Pais> paises = new ArrayList<Pais>();
    // paises.add(origen);
    // paises.add(embarque);
    // SolicitudImportacion solicitudImportacion = new SolicitudImportacion(
    // solicitudImportacionDto.getNit(), origen, embarque, productos);
    // SolicitudImportacion solicitudImportacionBD = solicitudImportacion;
    // solicitudImportacionBD.setConsecutivo(1L);

    // when(paisRepository.findByIds(Arrays.asList(new Long[] { 1L, 2L
    // }))).thenReturn(paises);
    // when(subpartidaRepository.findByNumeroSubpartida(12345678)).thenReturn(subpartida);
    // when(subpartidaRepository.findByNumeroSubpartida(123456789)).thenReturn(subpartida2);
    // when(solicitudImportacionRepository.create(solicitudImportacion)).thenReturn(solicitudImportacionBD);
    // SolicitudImportacionDto radicado =
    // administradorSolicitudImportacion.radicar(solicitudImportacionDto);
    // Assertions.assertEquals(1700000, radicado.getValorTotalArancel());
    // Assertions.assertNotNull(radicado.getFechaRadicacion());
    // Assertions.assertNotNull(radicado.getConsecutivo());
    // }
}