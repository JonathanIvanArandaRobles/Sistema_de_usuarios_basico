import hashlib
from abc import ABC, abstractmethod

class PasswordValidator(ABC):

    @abstractmethod
    def es_contraseña_valida(self, contraseña: str) -> bool:
        pass

    def hashear(self, contraseña: str) -> str:
        sha256 = hashlib.sha256()
        sha256.update(contraseña.encode('utf-8'))
        return sha256.hexdigest()

