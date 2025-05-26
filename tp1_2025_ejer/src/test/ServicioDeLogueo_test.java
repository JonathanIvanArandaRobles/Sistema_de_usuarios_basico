package test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ejer.RegistroDeCuenta;
import ejer.ServicioDeLogueo;
import ejer.StrongPasswordValidator;

class ServicioDeLogueo_test {

	private StrongPasswordValidator validator;
	private RegistroDeCuenta registro;
	private ServicioDeLogueo service;
	
	@BeforeEach
	void setUp() {
		validator = new StrongPasswordValidator();
		registro = new RegistroDeCuenta(validator);
		registro.registrarUsuario("Ana", "Segura123!");
		registro.registrarUsuario("Juan", "Clave456!");
		service = new ServicioDeLogueo(registro);
	}
	
	public enum CasoAutenticacion {
		AUTENTICACION_EXITOSA, CONTRASEÑA_INCORRECTA, USUARIO_INEXISTENTE 
	}
	
	@ParameterizedTest(name = "Caso: {0}")
	@EnumSource(CasoAutenticacion.class)
	void autenticacionPorCaso(CasoAutenticacion caso) {
		String usuario = null;
		String contraseña = null;
		boolean esperado = false;
		
		switch (caso) {
		case AUTENTICACION_EXITOSA -> {
			usuario = "Ana";
			contraseña = "Segura123!";
			esperado = true;
		}
		case CONTRASEÑA_INCORRECTA -> {
			usuario = "Ana";
			contraseña = "MalaClave";
			esperado = false;
		}
		case USUARIO_INEXISTENTE -> {
			usuario = "Desconocido";
			contraseña = "Clave123";
			esperado = false;
		}
		}
		boolean resultado = service.autenticar(usuario, contraseña);
		
		assertEquals(esperado, resultado, "Fallo en caso: " 
				+ caso + " con usuario: " + usuario);
	}
	
	  
	@Test 
	public void testAutenticacionExitosaConMock() { 
		
		RegistroDeCuenta registroMock = mock(RegistroDeCuenta.class);
		when(registroMock.existeUsuario("Ana")).thenReturn(true);
		when(registroMock.verificarCredenciales("Ana", "Segura123!")).thenReturn(true);
	  
		ServicioDeLogueo servicio = new ServicioDeLogueo(registroMock);
		assertTrue(servicio.autenticar("Ana", "Segura123!")); 
	}
	  
	@Test 
	public void testAutenticacionFallidaUsuarioNoExiste() {
		
		RegistroDeCuenta registroMock = mock(RegistroDeCuenta.class);
		when(registroMock.existeUsuario("Desconocido")).thenReturn(false);
	  
		ServicioDeLogueo servicio = new ServicioDeLogueo(registroMock);
		assertFalse(servicio.autenticar("Desconocido", "password123")); 
	}
	
	
	/*
	 * forma elegante
	 
	@Mock 
	private RegistroDeCuenta registroMock;
	  
	@InjectMocks 
	private ServicioDeLogueo servicio;
	  
	@BeforeEach 
	void setUp() { 
		
		MockitoAnnotations.openMocks(this); // Inicializa los @Mock y @InjectMocks 
	}
	  
	@Test 
	void testAutenticacionExitosaConMock() {
		
		when(registroMock.existeUsuario("Ana")).thenReturn(true);
		when(registroMock.verificarCredenciales("Ana", "Segura123!")).thenReturn(true);
	  
		assertTrue(servicio.autenticar("Ana", "Segura123!")); }
	  
	@Test 
	public void testAutenticacionFallidaUsuarioNoExiste() {
		
		when(registroMock.existeUsuario("Desconocido")).thenReturn(false);
	 
		assertFalse(servicio.autenticar("Desconocido", "password123")); 
	}
	*/

	@ParameterizedTest(name = "Usuario {0} con contraseña {1} debe devolver {2}")
	@CsvSource({ "Ana, Segura123!, true", "Ana, ClaveMala, false", 
				"Juan, Clave456!, true", "Juan, Incorrecta, false", "Desconocido, Algo123, false" })
	void AutenticacionConDatosReales(String usuario, String contraseña, boolean esperado) {
		boolean resultado = service.autenticar(usuario, contraseña);
		if (esperado)
			assertTrue(resultado);
		else
			assertFalse(resultado);
	}

	
}
