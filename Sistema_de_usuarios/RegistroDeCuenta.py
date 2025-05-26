from cuenta import Cuenta

class RegistroDeCuenta:
    def __init__(self, validator):
        self.validator = validator
        self.usuarios = []
        self.conexion = True

    def cerrar_conexion(self):
        self.conexion = False

    def registrar_usuario(self, nombre: str, contraseña: str):
        if self.existe_usuario(nombre):
            raise ValueError("El usuario ya existe")
        nuevo = Cuenta(nombre, contraseña, self.validator)
        self.usuarios.append(nuevo)

    def existe_usuario(self, nombre: str) -> bool:
        return any(u.get_nombre() == nombre for u in self.usuarios)

    def verificar_credenciales(self, nombre: str, contraseña: str) -> bool:
        hash_ = self.validator.hashear(contraseña)
        return any(
            u.get_nombre() == nombre and u.get_contraseña_hasheada() == hash_
            for u in self.usuarios
        )

    def obtener_usuario(self, user: str):
        for u in self.usuarios:
            if u.get_nombre() == user:
                return u
        raise ValueError("Usuario no encontrado")
