package ejer;

public class ServicioDeLogueo {
	private RegistroDeCuenta registro;

    public ServicioDeLogueo(RegistroDeCuenta registroCuentas) {
        this.registro = registroCuentas;
    }

    public boolean autenticar(String usuario, String contraseña) {
        return registro.existeUsuario(usuario) &&
               registro.verificarCredenciales(usuario, contraseña);
    }
}
