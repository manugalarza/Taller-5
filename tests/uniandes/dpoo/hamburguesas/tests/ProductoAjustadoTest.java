package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.*;

public class ProductoAjustadoTest {
	private ProductoMenu hamburguesa;
	
	private ProductoAjustado hamburguesaSinPepinillos;
	
	private Ingrediente pepinillos;
	
	private Ingrediente queso;

	@BeforeEach
	public void setUp() {
		hamburguesa = new ProductoMenu("Big Mac",20000);
		hamburguesaSinPepinillos = new ProductoAjustado(hamburguesa);
		pepinillos = new Ingrediente("Pepinillos", 1000);
		queso = new Ingrediente("Queso", 1000);
		hamburguesaSinPepinillos.agregarIngrediente(queso);
		hamburguesaSinPepinillos.quitarIngrediente(pepinillos);
		
	}
	
	@Test
	public void nombreCorrecto() {
		assertEquals("Big Mac",hamburguesaSinPepinillos.getNombre(),"Ese no es el nombre del producto");
	}
	
	@Test
	public void precioCorrecto() {
		assertEquals(21000,hamburguesaSinPepinillos.getPrecio(),"Ese no es el precio del producto");
	}
	
	@Test //TODO: cambiar la factura
	public void facturaCorrecta() {
		assertEquals("Big Mac    +Queso                1000    -Pepinillos            21000\n",hamburguesaSinPepinillos.generarTextoFactura(),"Esta factura no es correcta"); 
	}
}
