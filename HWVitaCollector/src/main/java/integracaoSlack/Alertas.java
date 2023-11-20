package integracaoSlack;

import DAO.*;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;
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


    public static void VerificarMetricas(String fkMaquina, List<ProcessoRegistro> processoRegistros, List<DiscoRegistro> discoRegistros, CpuRegistro cpuRegistro, MemoriaRegistro memoriaRegistro, SistemaRegistro sistemaRegistro) throws IOException, InterruptedException {

        if (parametrosAlerta == null) {
            Logging.AddLogInfo(Logging.fileHandler, "Parametros nulos na classe Alertas");
            System.out.println("Parametros nulos na classe Alertas");
            return;
        }

        Integer tempoParaAlerta = Integer.parseInt(parametrosAlerta.getTempoParaAlertaSec());
        List<Double> mediaParametrosUltimosSegundos = ParametrosAlertaDAO.getAvgsByTime(fkMaquina, tempoParaAlerta);

        assert mediaParametrosUltimosSegundos != null;
        Double mediaUsoCpu = mediaParametrosUltimosSegundos.get(0);
        Double mediaTempCpu = mediaParametrosUltimosSegundos.get(1);
        Double mediaUsoMemoria = mediaParametrosUltimosSegundos.get(2);


        for (DiscoRegistro dc : discoRegistros) {
            verificarDisco(dc.getEspacoLivre());
        }
        verificarCPU(mediaTempCpu, mediaUsoCpu);
        verificarMemoria(mediaUsoMemoria);

        for (ProcessoRegistro ps : processoRegistros) {
            verificarProcesso(ps.getResidentMemory(), ps.getNome());
        }

        verificarSistema(sistemaRegistro.getTempoDeAtividadeSO(),sistemaRegistro.getQtdDisposivosUsbConectados());

    }

    public static void verificarDisco(String espacoLivre) throws IOException, InterruptedException {
        Double espacoLivreParsed = Double.parseDouble(espacoLivre.toUpperCase().replaceAll("GB", "").replaceAll("MB", "").replaceAll("TB", ""));
        long espacoLivreBytes = (long) (espacoLivreParsed * 1024 * 1024 * 1024);
        long espacoLivreParametro = Long.parseLong(parametrosAlerta.getMinLivreDisco());


        //dadinho mockado pra forçar alerta
        if (espacoLivreBytes <= espacoLivreParametro + 9999999999L) {
            String alerta = "[🚨] - O espaço livre (%.1f GB) é menor que (%.1f) GB!".formatted(espacoLivreParsed, ((double) espacoLivreParametro / 1024 / 1024 / 1024));
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarCPU(Double temperatura, Double porcentagem) throws IOException, InterruptedException {
        Double temperaturaParametro = Double.parseDouble(parametrosAlerta.getMaxTempProcessador());
        Double porcentagemParametro = Double.parseDouble(parametrosAlerta.getMaxUsoProcessador());


        if (temperatura > temperaturaParametro) {
            String alerta = "[🚨] - A temperatura (%.1fº) da máquina passou de 75º!".formatted(temperatura);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
        if (porcentagem >= porcentagemParametro) {
            String alerta = "[🚨] - Sua CPU (%.1f%%) está ficando supercarregada!".formatted(porcentagem);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarMemoria(Double usoMemoria) throws IOException, InterruptedException {
        Double maxUsoMemoria = Double.parseDouble(parametrosAlerta.getMaxUsoMemoria());

        // usoMemoria > parametro
        if (usoMemoria >= maxUsoMemoria) {
            String alerta = "[🚨] - O uso atual da memória ram (%.1f %%) ultrapassou de %.1f %%!".formatted(usoMemoria, maxUsoMemoria);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarProcesso(long usoRamProcesso, String nome) throws IOException, InterruptedException {
        long totalRam = new Memoria().getTotal();
        double pctUso = ((double) usoRamProcesso / totalRam * 100);
        double pctMaximaRamParametro = Double.parseDouble(parametrosAlerta.getPorcentagemMaximaRamProcesso());

        //dadinho mockado pra forçar alerta
        if (pctUso > pctMaximaRamParametro - 50.0) {
            String alerta = "[🚨] - O uso atual de memória ram do processo %s está em %.2f %% do total!".formatted(nome, pctUso);
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarSistema(String secUptime, int qtdUsb) throws IOException, InterruptedException  {
        long secUptimeParsed = Long.parseLong(secUptime);
        long secUptimeParametro = Long.parseLong(parametrosAlerta.getMaxTempoDeAtividade());
        int minQtdUsbParametro = Integer.parseInt(parametrosAlerta.getMinQtdUsb());
        if(secUptimeParsed>secUptimeParametro){
            String alerta = "[🚨] - O sistema está ativo a muito tempo (%s), pode haver perda de performance ".formatted(Conversor.formatarSegundosDecorridos(secUptimeParsed));
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }

        if(qtdUsb<minQtdUsbParametro){
            String alerta = "[🚨] - O não foram encontrados os dispositivos usb necessários";
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }
}
