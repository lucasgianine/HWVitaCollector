package integracaoSlack;

import DAO.*;
import entidades.*;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alertas {
    static JSONObject json = new JSONObject();

    public static void AlertasLoop(String fkMaquina){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);

            List<ProcessoRegistro> processoRegistros = ProcessoRegistro.getProcessos();
            for (ProcessoRegistro processoRegistro: processoRegistros) {
                processoRegistro.setFkMaquina(fkMaquina);
                ProcessoDAO.inserirRegistroProcesso(processoRegistro);
            }

            List<DiscoRegistro> discoRegistros = DiscoRegistro.getDiscos();
            for (DiscoRegistro discoRegistro: discoRegistros) {
                discoRegistro.setFkMaquina(fkMaquina);
                DiscoDAO.inserirRegistroDisco(discoRegistro);

                try {
                    verificarDisco(discoRegistro.getEspacoLivre());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // CPU
            String temperaturaCpu = String.format("%.2f ºC", CpuRegistro.getCpuTemperatureValue());
            String usoCpu = CpuRegistro.getCpuUsePercentage();
            CpuRegistro cpuRegistro = new CpuRegistro(fkMaquina,dataFormatada,temperaturaCpu,usoCpu);
            CpuDAO.inserirRegistroCpu(cpuRegistro);

            try {
                verificarCPU(cpuRegistro.getTemperatura(), cpuRegistro.getUsoPorcentaegem());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // MEMÓRIA
            String totalMemoria1 = MemoriaRegistro.getTotalMemory();
            String porcentagemUsoMemoria = MemoriaRegistro.getMemoryUsagePercentage();
            MemoriaRegistro memoriaRegistro = new MemoriaRegistro(fkMaquina,dataFormatada,totalMemoria1,porcentagemUsoMemoria);
            MemoriaDAO.inserirRegistroMemoria(memoriaRegistro);

            try {
                verificarMemoria(memoriaRegistro.getUsoMemoria());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // SISTEMA
            SistemaRegistro sistemaRegistro = new SistemaRegistro
                    (fkMaquina,dataFormatada,SistemaRegistro.getSystemUptime(),SistemaRegistro.getUsbGroupSize());
            SistemaDAO.inserirRegistroSistema(sistemaRegistro);

            System.out.println("Registrou Todos");
        };

        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

    public static void verificarDisco (String espacoLivre) throws IOException, InterruptedException {
        String espacoLivreDisco = espacoLivre.replaceAll(",", ".");

        Double espacoLivreParsed = Double.parseDouble(espacoLivreDisco);

        if (espacoLivreParsed >= 5) {
            String alerta = "[🚨] - O espaço livre (%.1f GB) é menor que %.1f GB!".formatted(espacoLivreParsed, 5);

            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }

    public static void verificarCPU (String temperatura, String porcentagem) throws IOException, InterruptedException {
        String temperaturaCpu = temperatura.replaceAll(",", ".");
        String porcentagemCpu = porcentagem.replaceAll(",", ".");

        Double temperaturaParsed = Double.parseDouble(temperaturaCpu);
        Double porcentagemParsed = Double.parseDouble(porcentagemCpu);

        // Valores em hardcoded atualmente
        if (temperaturaParsed >= 40.0) {
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
        String usoMemoriaRAM = usoMemoria.replaceAll(",", ".");

        Double usoMemoriaParsed = Double.parseDouble(usoMemoriaRAM);

        // usoMemoria > parametro
        if (usoMemoriaParsed >= 3.7) {
            String alerta = "[🚨] - O uso atual da memória ram (%.1f GB) ultrapassou de %.1f GB!".formatted(usoMemoriaParsed, 3.7);

            json.put("text", alerta);
            Slack.sendMessage(json);
        }
    }
}
