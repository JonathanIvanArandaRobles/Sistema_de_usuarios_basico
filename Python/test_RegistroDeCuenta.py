import pytest
import csv
from RegistroDeCuenta import RegistroDeCuenta
from StrongPasswordValidator import StrongPasswordValidator

@pytest.fixture
def registro():
    registro = RegistroDeCuenta(StrongPasswordValidator())
    registro.registrar_usuario("Ana", "Segura123!")
    registro.registrar_usuario("Carlos", "ClaveFuerte!1")
    yield registro                # entrega el recurso a los demas test 
    registro.cerrar_conexion()
    
def leer_csv():
    with open("tests/resources/credenciales.csv", newline='') as csvfile:
        lector = csv.DictReader(csvfile)
        for fila in lector:
            usuario = fila['usuario']
            contraseña = fila['contraseña']
            esperado = fila['esperado'].strip().lower() == 'true'
            yield usuario, contraseña, esperado

@pytest.mark.parametrize("usuario, contraseña, esperado", list(leer_csv()))
def test_verificar_credenciales_csv(registro, usuario, contraseña, esperado):
    resultado = registro.verificar_credenciales(usuario, contraseña)
    assert resultado == esperado


@pytest.mark.parametrize(
    "usuario, contraseña, esperado",   # similar a MethodSource
    [
        ("Ana", "Segura123!", True), ("Ana", "ClaveMala", False),
        ("Desconocido", "password123", False), ("Carlos", "ClaveFuerte!1", True),
        ("Carlos", "clavefuerte!1", False)
    ]
)
def test_verificar_credenciales(registro, usuario, contraseña, esperado):
    resultado = registro.verificar_credenciales(usuario, contraseña)
    assert resultado == esperado







"""

@pytest.fixture(scope="session", autouse=True)
def Before_and_after_all():
    print(">>> INICIANDO TESTS DE REGISTRO")
    yield
    print(">>> FINALIZANDO TESTS DE REGISTRO")

@pytest.fixture
def registro():
    registro = RegistroDeCuenta(StrongPasswordValidator())
    yield registro                # entrega el recurso a los demas test 
    registro.cerrar_conexion()

def test_registro_exitoso(registro):
    registro.registrar_usuario("Ana", "Segura123!")
    assert registro.verificar_credenciales("Ana", "Segura123!")

"""







"""

from unittest.mock import Mock

def test_registrar_usuario_duplicado():
    registro_mock = Mock(spec=RegistroDeCuenta)
    registro_mock.existe_usuario.return_value = True

    # Simulamos que registrar_usuario lanza una excepción si el usuario ya existe
    registro_mock.registrar_usuario.side_effect = lambda usuario, clave: (
        (_ for _ in ()).throw(ValueError("El usuario ya existe"))
        if registro_mock.existe_usuario(usuario) else None
    )

    with pytest.raises(ValueError) as exc_info:
        registro_mock.registrar_usuario("Ana", "OtraClave!1")

    assert str(exc_info.value) == "El usuario ya existe"

"""