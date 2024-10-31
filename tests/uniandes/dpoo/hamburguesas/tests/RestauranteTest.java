package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.*;

public class RestauranteTest {
	
	private Restaurante luli;
	
	@BeforeEach
	public void setUp() {
	luli = new Restaurante();
	}
	
	@Test
	public void iniciarPedidoCorrecto() throws YaHayUnPedidoEnCursoException {
		luli.iniciarPedido("Pedro Humedal", "Calle 67");
		
		assertNotNull(luli.getPedidoEnCurso(), "No se pudo iniciar el pedido");
	}
	
	@Test
	void testCargarInformacion() throws IOException, NumberFormatException, HamburguesaException{

	ArrayList<Ingrediente> ingredientesEsperados = new ArrayList<Ingrediente>();
	Ingrediente lechuga = new Ingrediente("lechuga" , 1000);
	Ingrediente tomate = new Ingrediente("tomate", 500);
	Ingrediente cebolla = new Ingrediente("cebolla", 1000);
	Ingrediente queso = new Ingrediente("queso", 1000);
	ingredientesEsperados.add(lechuga);
	ingredientesEsperados.add(tomate);
	ingredientesEsperados.add(cebolla);
	ingredientesEsperados.add(queso);


	ArrayList<ProductoMenu> menuBase = new ArrayList<ProductoMenu>();
	ProductoMenu hamburguesa = new ProductoMenu("Big Mac", 14000);
	ProductoMenu malteada = new ProductoMenu("Malteada", 16000);
	ProductoMenu papas = new ProductoMenu("Papas", 5500);
	ProductoMenu gaseosa = new ProductoMenu("Coca Cola", 5500);
	ProductoMenu manzana = new ProductoMenu("Porcion manzana", 5500);
	menuBase.add (hamburguesa);
	menuBase.add (malteada);
	menuBase.add (papas);
	menuBase.add (gaseosa);
	menuBase.add (manzana);

	ArrayList<Combo> menuCombos = new ArrayList<Combo>();
	ArrayList<ProductoMenu> itemsCombo1 = new ArrayList<ProductoMenu>();
	itemsCombo1.add(hamburguesa);
	itemsCombo1.add(malteada);
	itemsCombo1.add(papas);
	Combo combo1 = new Combo("Cheat day", 0.3, itemsCombo1);


	ArrayList<ProductoMenu> itemsCombo2 = new ArrayList<ProductoMenu>();
	itemsCombo2.add(hamburguesa);
	itemsCombo2.add(gaseosa);
	itemsCombo2.add(manzana);
	Combo combo2 = new Combo("No tan saludable", 0.2, itemsCombo2);
	menuCombos.add(combo1);
	menuCombos.add(combo2);


	File archivoIngredientes = new File("tests/prueba/ingredientes_test");
    File archivoMenu = new File("tests/prueba/menu_test");
    File archivoCombos = new File("tests/prueba/combos_test");
    
	luli.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);


	ArrayList<Ingrediente> ingredientesCargados = luli.getIngredientes();
	ArrayList<ProductoMenu> menuBaseCargado = luli.getMenuBase();
	ArrayList<Combo> menuCombosCargado = luli.getMenuCombos();
	
	assertEquals(4, ingredientesCargados.size(), "No se cargaron los ingredientes esperados.");
	assertEquals(5, menuBaseCargado.size(),"No	se cargaron elementos esperados en el menu base.");
	assertEquals(2, menuCombosCargado.size(), "No se cargaron los combos esperados.");

	for (int i = 0; i < ingredientesEsperados.size(); i++) {
	assertEquals((ingredientesEsperados.get(i)).getNombre(), (ingredientesCargados.get(i)).getNombre(),
	"El nombre del ingrediente no es el esperado.");
	
	assertEquals((ingredientesEsperados.get(i)).getCostoAdicional(), (ingredientesCargados.get(i)).getCostoAdicional(),
	"El costo adicional del ingrediente no es el esperado.");
	}
	
	for (int i = 0; i < menuBase.size(); i++) {
		assertEquals((menuBase.get(i)).getNombre(), (menuBase.get(i)).getNombre(),
		"El nombre del ingrediente no es el esperado.");
		
		assertEquals((menuBase.get(i)).getPrecio(), (menuBase.get(i)).getPrecio(),
		"El costo adicional del ingrediente no es el esperado.");
	}
		
	for (int i = 0; i < menuCombos.size(); i++) {
		assertEquals((menuCombos.get(i)).getNombre(), (menuCombos.get(i)).getNombre(),
		"El nombre del ingrediente no es el esperado.");
		
		assertEquals((menuCombos.get(i)).getPrecio(), (menuCombos.get(i)).getPrecio(),
		"El costo adicional del ingrediente no es el esperado.");
	}
	}
	
	@Test
	public void iniciarPedidoExcepcion() throws YaHayUnPedidoEnCursoException {
		luli.iniciarPedido("Pedro Humedal", "Calle 67");
		
		assertThrows(YaHayUnPedidoEnCursoException.class, () -> {luli.iniciarPedido("Santiago Cartagena", "Autopista 75");});
}
	@Test
	public void cerrarPedidoCorrecto() throws NoHayPedidoEnCursoException, IOException, YaHayUnPedidoEnCursoException
    {
		luli.iniciarPedido("Pedro Humedal", "Calle 67");
		luli.cerrarYGuardarPedido();
		assertNull(luli.getPedidoEnCurso(),"El pedido no se a cerrado correctament");
}
	
	@Test
	public void cerrarPedidoExcepcion() throws NoHayPedidoEnCursoException, IOException
    {
		assertThrows(NoHayPedidoEnCursoException.class, () -> {luli.cerrarYGuardarPedido();});
}
	@Test
	public void getPedidoCorrecto() throws NoHayPedidoEnCursoException, IOException, YaHayUnPedidoEnCursoException
    {
		luli.iniciarPedido("Pedro Humedal", "Calle 67");
		luli.cerrarYGuardarPedido();
		luli.iniciarPedido("Murphy Murfes", "Calle 34");
		luli.cerrarYGuardarPedido();
		luli.iniciarPedido("Santiago Santiana", "Avenida 2");
		luli.cerrarYGuardarPedido();
		assertEquals(3,luli.getPedidos().size(),"La lista de pedidos no es correcta");
	}
	
	@Test
	public void getMenuBaseCorrecto() throws IOException, NumberFormatException, HamburguesaException {
	    
	    ArrayList<ProductoMenu> menuBase = luli.getMenuBase();
	    assertNotNull(menuBase, "El menú base es null");
	    }
	
	@Test
	public void getMenuCombosCorrecto() throws HamburguesaException, IOException, NumberFormatException {
		
		ArrayList<Combo> menuCombos = luli.getMenuCombos();
	    assertNotNull(menuCombos, "El menú de combos es null");
	}
	
	@Test
	public void testGetIngredientes() throws HamburguesaException, IOException, NumberFormatException {
	    
	    ArrayList<Ingrediente> ingredientes = luli.getIngredientes();
	    assertNotNull(ingredientes, "La lista de ingredientes es null");
	}

}
