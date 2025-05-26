import pytest
from RegistroDeCuenta import RegistroDeCuenta
from StrongPasswordValidator import StrongPasswordValidator
from ServicioDeLogueo import ServicioDeLogueo

# Casos de autenticacion como lista de tuplas
casos_autenticacion = [
    ("AUTENTICACION_EXITOSA", "Ana", "Segura123!", True),
    ("CONTRASEÑA_INCORRECTA", "Ana", "MalaClave", False),
    ("USUARIO_INEXISTENTE", "Desconocido", "Clave123", False),
]

@pytest.fixture
def servicio():
    validator = StrongPasswordValidator()
    registro = RegistroDeCuenta(validator)
    registro.registrar_usuario("Ana", "Segura123!")
    registro.registrar_usuario("Juan", "Clave456!")
    return ServicioDeLogueo(registro)

@pytest.mark.parametrize("caso, usuario, contraseña, esperado", casos_autenticacion)
def test_autenticacion_por_caso(servicio, caso, usuario, contraseña, esperado):
    resultado = servicio.autenticar(usuario, contraseña)
    assert resultado == esperado, f"Fallo en caso: {caso} con usuario: {usuario}"








"""
@pytest.mark.parametrize(
    "usuario, contraseña, esperado",
    [
        ("Ana", "Segura123!", True),
        ("Ana", "ClaveMala", False),
        ("Juan", "Clave456!", True),
        ("Juan", "Incorrecta", False),
        ("Desconocido", "Algo123", False)
    ]
)
def test_autenticacion_con_datos_reales(servicio_de_logueo, usuario, contraseña, esperado):
    resultado = servicio_de_logueo.autenticar(usuario, contraseña)
    if esperado:
        assert resultado is True
    else:
        assert resultado is False
"""












"""

from unittest.mock import patch

def verificar_credenciales_side_effect(usuario, contraseña):
    return usuario == "Ana" and contraseña == "Segura123!"

@pytest.fixture
def servicio_con_mock():
    with patch('ServicioDeLogueo.RegistroDeCuenta') as mock_registro:
        mock_registro.return_value.existe_usuario.return_value = True
        mock_registro.return_value.verificar_credenciales.side_effect = verificar_credenciales_side_effect
        servicio = ServicioDeLogueo(mock_registro.return_value)

        yield servicio, mock_registro.return_value

# Test donde las credenciales son correctas
def test_autenticacion_exitosa(servicio_con_mock):
    servicio, _ = servicio_con_mock
    assert servicio.autenticar("Ana", "Segura123!")

# Test donde el usuario existe pero la clave es incorrecta
def test_autenticacion_clave_incorrecta(servicio_con_mock):
    servicio, _ = servicio_con_mock
    assert not servicio.autenticar("Ana", "ClaveMala")

# Test donde el usuario no existe (controlando existe_usuario)
def test_autenticacion_usuario_no_existente(servicio_con_mock):
    servicio, registro_mock = servicio_con_mock
    registro_mock.existe_usuario.return_value = False

    assert not servicio.autenticar("Desconocido", "password123")

"""












"""
@pytest.fixture(autouse=True)
def servicio_con_mock():
    with patch('ServicioDeLogueo.RegistroDeCuenta') as mock_registro:
        mock_registro.return_value.existe_usuario.return_value = True
        mock_registro.return_value.verificar_credenciales.return_value = True

        # Creamos el servicio con el mock inyectado
        servicio = ServicioDeLogueo(mock_registro.return_value)

        # Lo que retornamos estará disponible en los tests
        yield servicio, mock_registro.return_value

def test_autenticacion_exitosa(servicio_con_mock):
    servicio, registro_mock = servicio_con_mock

    # Ya viene configurado como exitoso
    assert servicio.autenticar("Ana", "Segura123!")

def test_autenticacion_fallida_usuario_no_existe(servicio_con_mock):
    servicio, registro_mock = servicio_con_mock

    # Cambiamos el mock solo para este caso
    registro_mock.existe_usuario.return_value = False

    assert not servicio.autenticar("Desconocido", "password123")

"""














"""
from unittest.mock import Mock
from ServicioDeLogueo import ServicioDeLogueo

def test_autenticacion_exitosa_con_mock():
    registro_mock = Mock()
    registro_mock.existe_usuario.return_value = True
    registro_mock.verificar_credenciales.return_value = True

    servicio = ServicioDeLogueo(registro_mock)
    assert servicio.autenticar("Ana", "Segura123!")

def test_autenticacion_fallida_usuario_no_existe():
    registro_mock = Mock()
    registro_mock.existe_usuario.return_value = False

    servicio = ServicioDeLogueo(registro_mock)
    assert not servicio.autenticar("Desconocido", "password123")
"""

