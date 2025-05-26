package ejer;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        PasswordValidator validador = new StrongPasswordValidator();
        RegistroDeCuenta registro = new RegistroDeCuenta(validador);

        // Registramos usuarios
        registro.registrarUsuario("Ana", "Segura123!");
        registro.registrarUsuario("Carlos", "ClaveFuerte!1");

        ServicioDeLogueo logueo = new ServicioDeLogueo(registro);

        System.out.println("Bienvenido al sistema. Ingrese sus credenciales:");

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (logueo.autenticar(usuario, contraseña))
            System.out.println("Autenticación exitosa. ¡Bienvenido, " + usuario + "!");
        else 
            System.out.println("Error: Usuario o contraseña incorrectos.");

        scanner.close();
    }
}
