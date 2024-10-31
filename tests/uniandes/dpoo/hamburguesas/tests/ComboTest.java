package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.*;

public class ComboTest {
	private ProductoMenu hamburguesa;
	
	private ProductoMenu malteada;
	
	private ProductoMenu papas;
	
	private ArrayList<ProductoMenu> itemsCombo;	

    private Combo combo;

	@BeforeEach
	public void setUp() {
		hamburguesa = new ProductoMenu("Big Mac",20000);
		malteada = new ProductoMenu("Malteada de chocolate",13500);
		papas = new ProductoMenu("Papas medianas",5000);
		itemsCombo = new ArrayList<ProductoMenu>();
		itemsCombo.add(hamburguesa);
		itemsCombo.add(malteada);
		itemsCombo.add(papas);
		combo = new Combo("Cheat day", 0.3, itemsCombo);
	}
	
	@Test
	public void nombreCorrecto() {
		assertEquals("Cheat day",combo.getNombre(),"Ese no es el nombre del combo");
	}
	
	@Test
	public void precioCorrecto() {
		assertEquals(26950,combo.getPrecio(),"Ese no es el precio del combo");
	}
	
	@Test
	public void facturaCorrecta() {
		assertEquals("Combo Cheat day\n Descuento: 0.3\n            26950\n",combo.generarTextoFactura(),"Esta factura no es correcta");
	}
}
