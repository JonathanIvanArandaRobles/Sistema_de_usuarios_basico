package test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import ejer.RegistroDeCuenta;

import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;
import ejer.StrongPasswordValidator;
import ejer.Cuenta;

class StrongPasswordValidator_test {

	private StrongPasswordValidator validador = new StrongPasswordValidator();
	private RegistroDeCuenta registro;
	private String contraseña = "Muscle5$";

	@BeforeEach
	public void inicializar() {
		registro = new RegistroDeCuenta(validador);
		registro.registrarUsuario("Hercules", contraseña);
	}

	@Test
	public void ContraseñaNula() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Carlos", null);
		});
		assertEquals("Contraseña inválida", exception.getMessage());
	}

	@Test
	public void ContraseñaMuyCorta() {
		assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Laura", "Ab1$");
		});
	}

	@Test
	public void ContraseñaSinNumero() {
		assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Carlos", "Hola@ABC");
		});
	}

	@Test
	public void ContraseñaSinMayuscula() {
		assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Marta", "hola123@");
		});
	}

	@Test
	public void ContraseñaSinMinuscula() {
		assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Luis", "HOLA123@");
		});
	}

	@Test
	public void ContraseñaSinCaracterEspecial() {
		assertThrows(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Luis", "Chau12345");
		});
	}

	@Test
	public void ContraseñaConEspaciosDebeFallar() {
		assertThrowsExactly(IllegalArgumentException.class, () -> {
			registro.registrarUsuario("Mario", "Hola 123@");
		});
	}

	@Test
	public void ContraseñaValida() {
		assertDoesNotThrow(() -> {
			registro.registrarUsuario("Pepe", contraseña);
		});
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "abc", "123456", "ABCDEFGH", "hola1234", "HOLA1234" }) // contraseñas inválidas
	public void contraseñasInvalidas_lanzanExcepcion(String contraseñaInvalida) {

		assertThrows(IllegalArgumentException.class, () -> {
			new Cuenta("Carlos", contraseñaInvalida, validador);
		});
	}

	
	
//	 private boolean resultado = validador.esContraseñaValida(contraseña); private
//	 boolean resultadoEsperado;
//	 
//	 @Test public void SpecialChar() { resultadoEsperado =
//	 contraseña.matches(".*[!@#$%^&*()].*"); }
//	  
//	 @AfterEach public void finalizar() { assertEquals(resultadoEsperado,
//	 resultado); }
//	  
//	 @Test public void ContraseñaNoNula() {
//	 assertNotNull("La contraseña no debería ser nula", contraseña); }
//	 
//	 @Test public void ContraseñaValidas() {
//	 assertTrue(validador.esContraseñaValida(contraseña)); }
//	  
//	 @Test public void SinEspacios() { 
//		resultado = contraseña.contains(" ");
//	 assertFalse(resultado); }

	// Con esta forma, Mockito se encarga automáticamente de inyectar los mocks en
	// el objeto bajo prueba.
	// cuando estés trabajando con clases que tienen varias dependencias //

	@Mock
	private StrongPasswordValidator validadorMock;

	@InjectMocks
	private Cuenta cuenta;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testValidacion() {

		when(validadorMock.esContraseñaValida("ClaveSegura!")).thenReturn(true);
		when(validadorMock.hashear("ClaveSegura!")).thenReturn("HASHMOCK");

		cuenta = new Cuenta("Pedro", "ClaveSegura!", validadorMock);
		assertEquals("HASHMOCK", cuenta.getContraseñaHasheada());
	}

	
	// version rustica (no elegante)
//	@BeforeEach
//	public void init() {
//
//		validador = new StrongPasswordValidator();
//		registro = new RegistroDeCuenta(validador);
//		registro.registrarUsuario("Hercules", contraseña);
//	}
//
//	@Test
//	public void testValidacionDeContraseñaConMock() {
//		StrongPasswordValidator validadorMock = mock(StrongPasswordValidator.class);
//		when(validadorMock.esContraseñaValida("ClaveSegura!")).thenReturn(true);
//		when(validadorMock.hashear("ClaveSegura!")).thenReturn("HASHMOCK");
//
//		Cuenta cuenta = new Cuenta("Pedro", "ClaveSegura!", validadorMock);
//
//		assertEquals("HASHMOCK", cuenta.getContraseñaHasheada());
//	}

}


 
 
//	@Test
//	public void Uppercase() {
//		resultadoEsperado = contraseña.chars().anyMatch(Character::isUpperCase);
//	}
//
//	@Test
//	public void Lowercase() {
//		resultadoEsperado = contraseña.chars().anyMatch(Character::isLowerCase);
//	}
//
//	@Test
//	public void DigitCase() {
//		resultadoEsperado = contraseña.chars().anyMatch(Character::isDigit);
//	}
//
//	// @Test // public void SpecialChar() { //
//	resultadoEsperado=contraseña.matches(".*[!@#$%^&*()].*"); // } // //
//
//	@AfterEach // 
// public void finalizar() { // 
// 
// 	assertEquals(resultadoEsperado, resultado); // 
// }
 



