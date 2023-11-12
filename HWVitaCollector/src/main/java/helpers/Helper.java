package helpers;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Helper {
    public static double parseDouble(String input) {
        // Usando expressão regular para substituir tudo exceto dígitos e o ponto por vazio
        String parsed = input.replaceAll("[^0-9.]", "");

        // Lógica para lidar com múltiplos pontos no número (mantendo apenas o primeiro)
        int dotCount = 0;
        StringBuilder result = new StringBuilder();
        for (char c : parsed.toCharArray()) {
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) {
                    continue;
                }
            }
            result.append(c);
        }

        double extractedNumber = 0.0;
        try {
            extractedNumber = Double.parseDouble(result.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Em caso de erro ao converter para double, retorna 0.0 ou outro valor padrão desejado
        }

        return extractedNumber;
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return "\n"+ sw;
    }
}
