from RegistroDeCuenta import RegistroDeCuenta

class ServicioDeLogueo:
    def __init__(self, registro_cuentas):
        self.registro = registro_cuentas

    def autenticar(self, usuario: str, contraseña: str) -> bool:
        return self.registro.existe_usuario(usuario) and \
               self.registro.verificar_credenciales(usuario, contraseña)
