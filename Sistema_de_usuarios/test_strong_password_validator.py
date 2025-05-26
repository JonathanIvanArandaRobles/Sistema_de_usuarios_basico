import pytest
from cuenta import Cuenta
from RegistroDeCuenta import RegistroDeCuenta
from StrongPasswordValidator import StrongPasswordValidator


@pytest.fixture
def registro():
    validador = StrongPasswordValidator()
    reg = RegistroDeCuenta(validador)
    return reg

def test_contraseña_nula_lanza_excepcion(registro):
    with pytest.raises(ValueError) as exc_info: registro.registrar_usuario("Carlos", None)
    assert str(exc_info.value) == "Contraseña inválida"

def test_contraseña_muy_corta(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Laura", "Ab1$")

def test_contraseña_sin_numero(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Carlos", "Hola@ABC")

def test_contraseña_sin_mayuscula(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Marta", "hola123@")

def test_contraseña_sin_minuscula(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Luis", "HOLA123@")

def test_contraseña_sin_caracter_especial(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Luis", "Chau12345")

def test_contraseña_con_espacios_debe_fallar(registro):
    with pytest.raises(ValueError): registro.registrar_usuario("Mario", "Hola 123@")

def test_contrasena_valida(registro):
    try:
        registro.registrar_usuario("Pepe", "Muscle5$")
    except ValueError:
        pytest.fail("No debería lanzar excepción con contraseña válida")










"""
@pytest.mark.parametrize("contraseña_invalida", ["", "abc", "123456", "ABCDEFGH", "hola1234", "HOLA1234"])
def test_contraseñas_invalidas_lanzan_excepcion(contraseña_invalida):
    validador = StrongPasswordValidator()
    with pytest.raises(ValueError):
        Cuenta("Carlos", contraseña_invalida, validador)
        

"""







"""
from RegistroDeCuenta import RegistroDeCuenta
from StrongPasswordValidator import StrongPasswordValidator

from unittest.mock import Mock
from cuenta import Cuenta

from unittest.mock import patch
# Reutilizable en muchos tests, evita repetición pero menos directo al leer (más avanzado)
@pytest.fixture
def validador_mock():
    with patch("StrongPasswordValidator.StrongPasswordValidator") as MockValidator:
        instancia = MockValidator.return_value
        instancia.es_contraseña_valida.return_value = True
        instancia.hashear.return_value = "HASHMOCK"
        yield instancia

def test_validacion_de_contraseña_con_fixture(validador_mock):
    cuenta = Cuenta("Pedro", "ClaveSegura!", validador_mock)
    assert cuenta.contraseña_hasheada == "HASHMOCK"


# explícito, ideal para 1 solo test, pero se repite si tenés muchos tests
def test_validacion_de_contraseña_con_mock():
    validador_mock = Mock(spec=StrongPasswordValidator)
    validador_mock.es_contraseña_valida.return_value = True
    validador_mock.hashear.return_value = "HASHMOCK"

    cuenta = Cuenta("Pedro", "ClaveSegura!", validador_mock)

    assert cuenta.contraseña_hasheada == "HASHMOCK"

@pytest.fixture
def validador():
    return StrongPasswordValidator()

@pytest.fixture
def contraseña():
    return "Muscle5$"

@pytest.fixture
def registro(validador, contraseña):
    r = RegistroDeCuenta(validador)
    r.registrar_usuario("Hercules", contraseña)
    return r

def test_contraseña_valida(validador):
    assert validador.es_contraseña_valida("Abc123!@")

"""















"""

@pytest.fixture
def setup():
    contraseña = "Muscle5$"
    validador = StrongPasswordValidator()
    resultado = validador.es_contraseña_valida(contraseña)
    context = {
        "validador": validador,
        "contraseña": contraseña,
        "resultado": resultado,
        "resultado_esperado": None  
    }
    yield context
    if context["resultado_esperado"] is not None:
        assert context["resultado"] == context["resultado_esperado"]

def test_special_char(setup):
    setup["resultado_esperado"] = any(c in "!@#$%^&*()" for c in setup["contraseña"])
    # assert any(c in "!@#$%^&*()" for c in setup["contraseña"])
    
def test_contraseña_no_nula(setup):
    assert setup["contraseña"] is not None, "La contraseña no debería ser nula"

def test_contraseña_valida(setup):
    assert setup["validador"].es_contraseña_valida(setup["contraseña"])

def test_sin_espacios(setup):
    resultado = " " in setup["contraseña"]
    assert not resultado, "La contraseña no debe contener espacios"





def test_caracter_especial_no_permitido_debe_fallar(registro):
    with pytest.raises(ValueError):
        registro.registrar_usuario("Pepe", "Hola123¿")


def test_sin_espacios(contraseña):
    assert " " not in contraseña


def test_uppercase(contraseña):
    assert any(c.isupper() for c in contraseña)


def test_lowercase(contraseña):
    assert any(c.islower() for c in contraseña)


def test_digit_case(contraseña):
    assert any(c.isdigit() for c in contraseña)


def test_special_char(contraseña):
    assert any(c in "!@#$%^&*()" for c in contraseña)

"""