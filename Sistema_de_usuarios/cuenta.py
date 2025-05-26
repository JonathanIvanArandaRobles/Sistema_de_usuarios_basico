from StrongPasswordValidator import StrongPasswordValidator

class Cuenta:
    def __init__(self, nombre, contraseña, validador):
        if not validador.es_contraseña_valida(contraseña):
            raise ValueError("Contraseña inválida")
        self.user = nombre
        self.contraseña_hasheada = validador.hashear(contraseña)

    def get_nombre(self):
        return self.user

    def get_contraseña_hasheada(self):
        return self.contraseña_hasheada

