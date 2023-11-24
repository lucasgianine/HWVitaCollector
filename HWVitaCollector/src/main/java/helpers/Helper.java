package helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Helper {

    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return "\n" + sw;
    }

    public static String getDataFormatada() {
        Date dataAtual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataFormatada = dateFormat.format(dataAtual);

        return dataFormatada;
    }

    public static String decodeBase64ToString(String encodedValue) {
        try {
            // Decodifica o valor Base64 para bytes
            byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);

            // Converte os bytes para uma string usando UTF-8 (ou outra codificação, se aplicável)
            String decodedString = new String(decodedBytes, "UTF-8");

            return decodedString;
        } catch (Exception e) {
            // Trata exceções, como um valor inválido em Base64
            System.out.println("Erro ao decodificar Base64: " + e.getMessage());
            return null;
        }
    }
}
