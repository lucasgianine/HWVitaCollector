package integracaoSlack;

import DAO.*;
import entidades.*;
import gui.Login;
import helpers.Helper;
import helpers.Logging;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

@SuppressWarnings("WrapperTypeMayBePrimitive")
public class Alertas {
    static JSONObject json = new JSONObject();
    public static ParametrosAlerta parametrosAlerta = ParametrosAlertaDAO.getParametros(Login.fkFuncionarioStatic);

    public static void VerificarMetricas(List<ProcessoRegistro> processoRegistros, List<DiscoRegistro> discoRegistros, CpuRegistro cpuRegistro, MemoriaRegistro memoriaRegistro, SistemaRegistro sistemaRegistro) throws IOException, InterruptedException {

        if (parametrosAlerta == null) {
            Logging.AddLogInfo(Logging.fileHandler, "Parametros nulos na classe Alertas");
            System.out.println("Parametros nulos na classe Alertas");
            return;
        }

        for (DiscoRegistro dc : discoRegistros) {
            verificarDisco(dc.getEspacoLivre());
        }
        verificarCPU(cpuRegistro.getTemperatura(), cpuRegistro.getUsoPorcentagem());
        verificarMemoria(memoriaRegistro.getUsoMemoria());


    }

    public static void verificarDisco (String espacoLivre) throws IOException, InterruptedException {
        Double espacoLivreParsed = Double.parseDouble(espacoLivre.toUpperCase().replaceAll("GB","").replaceAll("MB","").replaceAll("TB","")) - 10.0;
        long espacoLivreBytes = (long) (espacoLivreParsed * 1024 * 1024 * 1024);
        long espacoLivreParametro = Long.parseLong(parametrosAlerta.getMinLivreDisco()) ;

        double espacoLivreParametroEmGb = (double) espacoLivreParametro /1024/1024/1024;


        if (espacoLivreBytes <= espacoLivreParametro + 9999999999L ) {
            String alerta = "[🚨] - O espaço livre (%.1f GB) é menor que (%.1f) GB!".formatted(espacoLivreParsed, espacoLivreParametroEmGb);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarCPU (String temperatura, String porcentagem) throws IOException, InterruptedException {
        Double temperaturaParsed = Double.parseDouble(temperatura.replaceAll("ºC","").replace(",","."));
        Double porcentagemParsed = Double.parseDouble(porcentagem.replaceAll("%","").replace(",","."));
        Double temperaturaParametro = Double.parseDouble(parametrosAlerta.getMaxTempProcessador());
        Double porcentagemParametro = Double.parseDouble(parametrosAlerta.getMaxUsoProcessador());


        if (temperaturaParsed > temperaturaParametro) {
            String alerta = "[🚨] - A temperatura (%.1fº) da máquina passou de 75º!".formatted(temperaturaParsed);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
        if (porcentagemParsed >= porcentagemParametro) {
            String alerta = "[🚨] - Sua CPU (%.1f%%) está ficando supercarregada!".formatted(porcentagemParsed);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarMemoria (String usoMemoria) throws IOException, InterruptedException {
        Double usoMemoriaParsed = Double.parseDouble(usoMemoria.replaceAll("%","").replace(",","."));
        Double maxUsoMemoria = Double.parseDouble(parametrosAlerta.getMaxUsoMemoria());

        // usoMemoria > parametro
        if (usoMemoriaParsed >= maxUsoMemoria) {
            String alerta = "[🚨] - O uso atual da memória ram (%.1f %%) ultrapassou de %.1f %%!".formatted(usoMemoriaParsed, 95.0);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }
}
