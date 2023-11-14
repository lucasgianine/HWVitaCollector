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

    public static void AlertasLoop(String fkMaquina){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        if(parametrosAlerta == null){
            Logging.AddLogInfo(Logging.fileHandler,"Parametros nulos na classe Alertas");
            System.out.println("Parametros nulos na classe Alertas");
            return;
        }
        Runnable task = () -> {
            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);


            List<ProcessoRegistro> processoRegistros = ProcessoRegistro.getProcessos();
            for (ProcessoRegistro processoRegistro: processoRegistros) {
                processoRegistro.setFkMaquina(fkMaquina);
            }

            List<DiscoRegistro> discoRegistros = DiscoRegistro.getDiscos();
            for (DiscoRegistro discoRegistro: discoRegistros) {
                discoRegistro.setFkMaquina(fkMaquina);
                try {
                    verificarDisco(discoRegistro.getEspacoLivre());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // CPU
            String temperaturaCpu = String.format("%.2f ºC", CpuRegistro.getCpuTemperatureValue());
            String usoCpu = CpuRegistro.getCpuUsePercentage();



            try {
                verificarCPU(temperaturaCpu,usoCpu);
            }  catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            // MEMÓRIA
            String totalMemoria1 = MemoriaRegistro.getTotalMemory();
            String porcentagemUsoMemoria = MemoriaRegistro.getMemoryUsagePercentage();



            try {
                verificarMemoria(porcentagemUsoMemoria);
            }  catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            // SISTEMA
            SistemaRegistro sistemaRegistro = new SistemaRegistro
                    (fkMaquina,dataFormatada,SistemaRegistro.getSystemUptime(),SistemaRegistro.getUsbGroupSize());

        };

        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

    public static void verificarDisco (String espacoLivre) throws IOException, InterruptedException {
        Double espacoLivreParsed = Helper.parseDouble(espacoLivre);
        Integer espacoLivreBytes = (int) (espacoLivreParsed * 1024 * 1024 * 1024);
        Integer espacoLivreParametro = Integer.parseInt(parametrosAlerta.getMinLivreDisco());

        if (espacoLivreBytes <= espacoLivreParametro) {
            String alerta = "[🚨] - O espaço livre (%.1f GB) é menor que %d GB!".formatted(espacoLivreParsed, 5);

            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarCPU (String temperatura, String porcentagem) throws IOException, InterruptedException {
        Double temperaturaParsed = Helper.parseDouble(temperatura);
        Double porcentagemParsed = Helper.parseDouble(porcentagem);
        Double temperaturaParametro = Helper.parseDouble(parametrosAlerta.getMaxTempProcessador());

        // Valores em hardcoded atualmente
        if (temperaturaParsed >= temperaturaParametro) {
            String alerta = "[🚨] - A temperatura (%.1fº) da máquina passou de 40º!".formatted(temperaturaParsed);

            json.put("text", alerta);
            Slack.sendMessage(json);
        } else if (porcentagemParsed >= 90.0) {
            String alerta = "[🚨] - Sua CPU (%.1f%%) está ficando supercarregada!".formatted(porcentagemParsed);

            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarMemoria (String usoMemoria) throws IOException, InterruptedException {
        Double usoMemoriaParsed = Helper.parseDouble(usoMemoria);

        // usoMemoria > parametro
        if (usoMemoriaParsed >= 3.7) {
            String alerta = "[🚨] - O uso atual da memória ram (%.1f GB) ultrapassou de %.1f GB!".formatted(usoMemoriaParsed, 3.7);

            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }
}
