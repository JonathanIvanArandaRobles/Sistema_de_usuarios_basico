package ejer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class PasswordValidator {
	abstract boolean esContraseñaValida(String contraseña);
	
	public String hashear(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(contraseña.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}
