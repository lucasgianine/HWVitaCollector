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
    public static ParametrosAlerta parametrosAlerta = ParametrosAlertaDAO.getParametros(Login.fkHospitalStatic);

    public static String fkMaquinaStatic;


    public static void VerificarMetricas(String fkMaquina, List<ProcessoRegistro> processoRegistros, List<DiscoRegistro> discoRegistros, CpuRegistro cpuRegistro, MemoriaRegistro memoriaRegistro, SistemaRegistro sistemaRegistro) throws IOException, InterruptedException {

        if (parametrosAlerta == null) {
            Logging.AddLogInfo(Logging.fileHandler, "Parametros nulos na classe Alertas");
            System.out.println("Parametros nulos na classe Alertas");
            System.out.println("Fk Hospital: " + Login.fkHospitalStatic);
            return;
        }
        //System.out.println("sout parametros " + parametrosAlerta);
        fkMaquinaStatic = fkMaquina;
        Integer tempoParaAlerta = Integer.parseInt(parametrosAlerta.getTempoParaAlertaSec());
        List<Double> mediaParametrosUltimosSegundos = ParametrosAlertaDAO.getAvgsByTime(fkMaquina, tempoParaAlerta);

        Double mediaUsoCpu = null;
        Double mediaTempCpu = null;
        Double mediaUsoMemoria = null;
        try{
            mediaUsoCpu = mediaParametrosUltimosSegundos.get(0);
            mediaTempCpu = mediaParametrosUltimosSegundos.get(1);
            mediaUsoMemoria = mediaParametrosUltimosSegundos.get(2);
        }catch (Exception e){
            String stacktrace = Helper.getStackTraceAsString(e);
            System.out.println("Exception gerando as médias");
            Logging.AddLogInfo(Logging.fileHandler, "Exception gerando as médias" + stacktrace);
        }
        //System.out.println("Media uso coisas " + mediaUsoCpu + " " + mediaTempCpu + " " +mediaUsoMemoria);


        try {

        for (DiscoRegistro dc : discoRegistros) {
            verificarDisco(dc.getEspacoLivre());
            //System.out.println("Espaço livre disco " + dc.getEspacoLivre());
        }
        verificarCPU(mediaTempCpu, mediaUsoCpu);
        verificarMemoria(mediaUsoMemoria);

        for (ProcessoRegistro ps : processoRegistros) {
            verificarProcesso(ps.getResidentMemory(), ps.getNome());
        }

        verificarSistema(sistemaRegistro.getTempoDeAtividadeSO(),sistemaRegistro.getQtdDisposivosUsbConectados());
        }catch (Exception e){
            String stacktrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,"Erro nas verificações de alertas " + stacktrace);
        }

    }

    public static void verificarDisco(String espacoLivre) throws IOException, InterruptedException {
        Double espacoLivreParsed = Double.parseDouble(espacoLivre.toUpperCase().replaceAll("GB", "").replaceAll("MB", "").replaceAll("TB", ""));
        long espacoLivreBytes = (long) (espacoLivreParsed * 1024 * 1024 * 1024);
        long espacoLivreParametro = Long.parseLong(parametrosAlerta.getMinLivreDisco());


        //dadinho mockado pra forçar alerta
        if (espacoLivreBytes <= espacoLivreParametro || true) {
            String alerta = "[🚨] - O espaço livre (%.1f GB) é menor que (%.1f) GB!".formatted(espacoLivreParsed, ((double) espacoLivreParametro / 1024 / 1024 / 1024));
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"hardware","Disco","armazenamentoLivre",alerta)){
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
            }
        }
    }

    public static void verificarCPU(Double temperatura, Double porcentagem) throws IOException, InterruptedException {
        Double temperaturaParametro = Double.parseDouble(parametrosAlerta.getMaxTempProcessador());
        Double porcentagemParametro = Double.parseDouble(parametrosAlerta.getMaxUsoProcessador());

        if (temperatura > temperaturaParametro) {
            String alerta = "[🚨] - A temperatura (%.1fº) da máquina passou de 75º!".formatted(temperatura);
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"hardware","Cpu","temperatura",alerta)){
                System.out.println(alerta);
                json.put("text", alerta);
                Slack.sendMessage(json);
            }
        }

        if (porcentagem >= porcentagemParametro || true) {
            String alerta = "[🚨] - Sua CPU (%.1f%%) está ficando supercarregada!".formatted(porcentagem);
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"hardware","Cpu","usoPorcentagem",alerta)){
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
            }

        }
    }

    public static void verificarMemoria(Double usoMemoria) throws IOException, InterruptedException {
        Double maxUsoMemoria = Double.parseDouble(parametrosAlerta.getMaxUsoMemoria());

        // usoMemoria > parametro
        if (usoMemoria > maxUsoMemoria) {
            String alerta = "[🚨] - O uso da memória ram (%.1f %%) ultrapassou de %.1f %%!".formatted(usoMemoria, maxUsoMemoria);
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"hardware","Memoria","usoMemoria",alerta)){
            System.out.println(alerta);
            json.put("text", alerta);
            Slack.sendMessage(json);
            }
        }
    }

    public static void verificarProcesso(long usoRamProcesso, String nome) throws IOException, InterruptedException {
        long totalRam = new Memoria().getTotal();
        double pctUso = ((double) usoRamProcesso / totalRam * 100);
        double pctMaximaRamParametro = Double.parseDouble(parametrosAlerta.getPorcentagemMaximaRamProcesso());

        //dadinho mockado pra forçar alerta
        if (pctUso > pctMaximaRamParametro || true) {
                String alerta = "[🚨] - O uso de memória ram do processo %s está em %.2f %% do total!".formatted(nome, pctUso);
                if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"software","Processo","usoMemoriaRam",alerta)){
                System.out.println(alerta);
                json.put("text", alerta);
                Slack.sendMessage(json);
            }
        }
    }

    public static void verificarSistema(String secUptime, int qtdUsb) throws IOException, InterruptedException  {
        long secUptimeParsed = Long.parseLong(secUptime);
        long secUptimeParametro = Long.parseLong(parametrosAlerta.getMaxTempoDeAtividade());
        int minQtdUsbParametro = Integer.parseInt(parametrosAlerta.getMinQtdUsb());
        if(secUptimeParsed>secUptimeParametro){
            String alerta = "[🚨] - O sistema está ativo a muito tempo (%s), pode haver perda de performance ".formatted(Conversor.formatarSegundosDecorridos(secUptimeParsed));
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"software","Sistema Operacional","tempoDeAtividadeSistema",alerta)){
                System.out.println(alerta);
                json.put("text", alerta);
                Slack.sendMessage(json);
            }

        }

        if(qtdUsb<minQtdUsbParametro){
            String alerta = "[🚨] - O não foram encontrados os dispositivos usb necessários";
            if(gerarOcorrencia(fkMaquinaStatic,Helper.getDataFormatada(),"software","Sistema Operacional","qtdDispositivosUsb",alerta)){
                System.out.println(alerta);
                json.put("text", alerta);
                Slack.sendMessage(json);
            }
        }
    }


    public static boolean gerarOcorrencia(String fkMaquina,String dtOcorrencia,String categoria, String componente, String metrica, String descricao){
        if(!OcorrenciaDAO.hasOcorrenciaIgualRecente(fkMaquina,metrica)){
            OcorrenciaDAO.inserirOcorrencia(fkMaquina, dtOcorrencia, componente, categoria, metrica, descricao);
            return true;
        }
        return false;
    }

}
