package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class ProductoMenuTest {
	private ProductoMenu hamburguesa;

	@BeforeEach
	public void setUp() {
		hamburguesa = new ProductoMenu("Big Mac",20000);
	}
	
	@Test
	public void nombreCorrecto() {
		assertEquals("Big Mac",hamburguesa.getNombre(),"Ese no es el nombre del producto");
	}
	
	@Test
	public void precioCorrecto() {
		assertEquals(20000,hamburguesa.getPrecio(),"Ese no es el precio del producto");
	}
	
	@Test
	public void facturaCorrecta() {
		assertEquals("Big Mac\n            20000\n",hamburguesa.generarTextoFactura(),"Esta factura no es correcta");
	}
	


}
