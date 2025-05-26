package ejer;

public class StrongPasswordValidator extends PasswordValidator {
	
    @Override
    public boolean esContraseñaValida(String password) {
        if (password == null || password.length() < 8) return false;

        if (!password.matches("[A-Za-z0-9!@#$%&*().]+")) return false;

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;

        for (char caracter : password.toCharArray()) {
            if (Character.isUpperCase(caracter)) tieneMayuscula = true;
            else if (Character.isLowerCase(caracter)) tieneMinuscula = true;
            else if (Character.isDigit(caracter)) tieneNumero = true;
            else if ("!@#$%&*().".indexOf(caracter) >= 0) tieneEspecial = true;

            // Si ya cumple todo, terminamos antes
            if (tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial) return true;
        }

        return false;
    }
}

