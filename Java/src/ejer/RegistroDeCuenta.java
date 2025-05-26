package ejer;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

public class RegistroDeCuenta {
	private List<Cuenta> usuarios;
	private PasswordValidator validator;
	private boolean conexion;

	public RegistroDeCuenta (PasswordValidator validator) {
		this.usuarios = new ArrayList<>();
	    this.validator = validator;
	    this.conexion = true;
	}
	
	public void cerrarConexion() {
        this.conexion = false;
    }
	
	public boolean isConexionActiva() {
	    return conexion;
	}

	public void registrarUsuario (String nombre, String contraseña) {
		if (nombre == null)
			throw new IllegalArgumentException("El user no puede ser null");
		
		if (existeUsuario(nombre))
			throw new IllegalArgumentException("El usuario ya existe");
		
		Cuenta nuevo = new Cuenta(nombre, contraseña, validator);
		usuarios.add(nuevo);
	}

	public boolean existeUsuario(String nombre) {
		return usuarios.stream().anyMatch(u -> u.getNombre().equals(nombre));
	}

	public boolean verificarCredenciales(String nombre, String contraseña) {
		String hash = validator.hashear(contraseña);
		return usuarios.stream().anyMatch(u -> u.getNombre().equals(nombre) && u.getContraseñaHasheada().equals(hash));
	}
	
	public Cuenta obtenerUsuario(String user) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equals(user))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
