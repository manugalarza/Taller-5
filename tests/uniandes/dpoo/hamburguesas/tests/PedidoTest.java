package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.*;

public class PedidoTest {
	
	private Pedido pedido;
	
	private ProductoMenu hamburguesa;

	@BeforeEach
	public void setUp() {
		pedido = new Pedido("Pedro Humedal","Avenida 57");
		hamburguesa = new ProductoMenu("Big Mac", 20000);
		pedido.agregarProducto(hamburguesa);
	}
	
	@Test
	public void idCorrecto() {
		int id = pedido.getIdPedido();
		Pedido pedido2 = new Pedido("Santiago Santana","Calle 34");
		assertEquals(id+1,pedido2.getIdPedido(),"Ese no es el id del pedido");
	}
	
	@Test
	public void nombreCorrecto() {
		assertEquals("Pedro Humedal",pedido.getNombreCliente(),"Ese no es el nombre del cliente");
	}
	
	@Test
	public void facturaCorrecta() {
		assertEquals("Cliente: Pedro Humedal\nDirección: Avenida 57\n----------------\nBig Mac\n            20000\n----------------\nPrecio Neto:  20000\nIVA:          3800\nPrecio Total: 23800\n",pedido.generarTextoFactura(),"Esta factura no es correcta");
	}
	
	@Test
	public void guardarFacturaCorrecta() throws IOException {
		File archivoTemporal = null;
	    try {
	        archivoTemporal = File.createTempFile("facturaTest", ".txt");
	        pedido.guardarFactura(archivoTemporal);

	        String contenidoArchivo = Files.readString(Path.of(archivoTemporal.getPath()));
	        String textoEsperado = pedido.generarTextoFactura(); 

	        assertEquals(textoEsperado, contenidoArchivo, "El contenido de la factura guardada no es correcto");

	    } finally {
	            archivoTemporal.delete();}
		}
	
	@Test
    public void testGuardarFacturaLanzaFileNotFoundException() {
        File archivoNoEscribible = new File("directorio_no_existente/factura.txt");

        assertThrows(FileNotFoundException.class, () -> {pedido.guardarFactura(archivoNoEscribible);
       });
    }
}
