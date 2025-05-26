package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import ejer.PasswordValidator;
import ejer.RegistroDeCuenta;
import ejer.StrongPasswordValidator;

class RegistroDeCuenta_test {

	private RegistroDeCuenta registro;

	@BeforeEach
	void setUp() {
		PasswordValidator validator = new StrongPasswordValidator();
		registro = new RegistroDeCuenta(validator);

		registro.registrarUsuario("Ana", "Segura123!");
		registro.registrarUsuario("Carlos", "ClaveFuerte!1");
	}

	// parámetros (usuario, contraseña, resultado esperado)
	static Stream<org.junit.jupiter.params.provider.Arguments> credencialesValidasOInvalidas() {
		return Stream.of(org.junit.jupiter.params.provider.Arguments.of("Ana", "Segura123!", true),
				org.junit.jupiter.params.provider.Arguments.of("Ana", "ClaveMala", false),
				org.junit.jupiter.params.provider.Arguments.of("Desconocido", "password123", false),
				org.junit.jupiter.params.provider.Arguments.of("Carlos", "ClaveFuerte!1", true),
				org.junit.jupiter.params.provider.Arguments.of("Carlos", "clavefuerte!1", false));
	}

	@ParameterizedTest
	@MethodSource("credencialesValidasOInvalidas")
	void testVerificarCredenciales(String usuario, String contraseña, boolean esperado) {
		boolean resultado = registro.verificarCredenciales(usuario, contraseña);
		assertEquals(esperado, resultado);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/credenciales.csv", numLinesToSkip = 1)
	void testVerificarCredencialesDesdeCSV(String usuario, String contraseña, boolean esperado) {
		boolean resultado = registro.verificarCredenciales(usuario, contraseña);
		assertEquals(esperado, resultado);
	}

	
	/*
	 
	 //se verifica el caso donde un mock pueda simular existe usuario
	 
	@Test 
	public void testVerificarCredencialesIncorrectas() {
		assertFalse(registro.verificarCredenciales("Ana", "Incorrecta123")); 
	 }
	  
	@Test 
	public void testRegistrarUsuarioDuplicado() { 
	  
		RegistroDeCuenta registroMock = mock(RegistroDeCuenta.class);
		when(registroMock.existeUsuario("Ana")).thenReturn(true);
	 
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		registroMock.registrarUsuario("Ana", "OtraClave!1"); });
	 
		assertEquals("El usuario ya existe", exception.getMessage()); } 
	}
	*/
	
	
	
	
	
	/*
	
	private RegistroDeCuenta registro;

	@BeforeAll
	public static void initAll() {
		System.out.println(">>> INICIANDO TESTS DE REGISTRO");
	}

	@BeforeEach
	public void setUp() {
		registro = new RegistroDeCuenta(new StrongPasswordValidator());
	}

	@Test
	public void testRegistroExitoso() {
		registro.registrarUsuario("Ana", "Segura123!");
		assertTrue(registro.verificarCredenciales("Ana", "Segura123!"));
	}

	@AfterEach
	public void tearDown() {
		registro.cerrarConexion();
		;
	}

	@AfterAll
	public static void tearDownAll() {
		System.out.println(">>> FINALIZANDO TESTS DE REGISTRO");
	}
	
	*/

}

