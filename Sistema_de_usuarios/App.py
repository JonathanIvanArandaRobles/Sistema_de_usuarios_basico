from StrongPasswordValidator import StrongPasswordValidator 
from RegistroDeCuenta import RegistroDeCuenta
from ServicioDeLogueo import ServicioDeLogueo

def main():
    import getpass

    validador = StrongPasswordValidator()
    registro = RegistroDeCuenta(validador)

    # Registramos usuarios
    registro.registrar_usuario("Ana", "Segura123!")
    registro.registrar_usuario("Carlos", "ClaveFuerte!1")

    logueo = ServicioDeLogueo(registro)

    print("Bienvenido al sistema. Ingrese sus credenciales:")

    usuario = input("Usuario: ")
    contraseña = getpass.getpass("Contraseña: ")  # Oculta la contraseña en consola

    if logueo.autenticar(usuario, contraseña):
        print(f"Autenticación exitosa. ¡Bienvenido/a, {usuario}!")
    else:
        print("Error: Usuario o contraseña incorrectos.")

if __name__ == "__main__":
    main()
