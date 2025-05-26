import re
from PasswordValidator import PasswordValidator

class StrongPasswordValidator(PasswordValidator):

    def es_contraseña_valida(self, password: str) -> bool:
        if password is None or len(password) < 8:
            return False

        if not re.fullmatch(r"[A-Za-z0-9!@#$%&*().]+", password):
            return False

        tiene_mayuscula = any(c.isupper() for c in password)
        tiene_minuscula = any(c.islower() for c in password)
        tiene_numero = any(c.isdigit() for c in password)
        tiene_especial = any(c in "!@#$%&*()." for c in password)

        return tiene_mayuscula and tiene_minuscula and tiene_numero and tiene_especial
