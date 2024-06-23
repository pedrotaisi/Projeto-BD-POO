package Classes_extras;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validacao {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // CPF válido
        return true;
    }

    public static boolean validarTelefone(String telefone) {
        // Remover caracteres não numéricos
        telefone = telefone.replaceAll("\\D", "");

        // Verificar se o telefone tem entre 10 e 11 dígitos
        if (telefone.length() < 10 || telefone.length() > 11) {
            return false;
        }

        // Telefone válido
        return true;
    }


}


