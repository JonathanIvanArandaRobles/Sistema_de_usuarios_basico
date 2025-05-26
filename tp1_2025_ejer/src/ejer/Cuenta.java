package ejer;

public class Cuenta {
	private String User;
	private String contraseñaHasheada;

	public Cuenta(String nombre, String contraseña, PasswordValidator validador) {
		if (nombre == null || contraseña == null)
            throw new IllegalArgumentException("Nombre y/o contraseña no pueden ser nulos");
		
		if (!validador.esContraseñaValida(contraseña))
			throw new IllegalArgumentException("Contraseña inválida");
		
		this.User = nombre;
		this.contraseñaHasheada = validador.hashear(contraseña);
	}

	public String getNombre() {
		return this.User;
	}

	public String getContraseñaHasheada() {
		return contraseñaHasheada;
	}
}
