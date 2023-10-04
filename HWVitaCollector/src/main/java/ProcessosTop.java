public class ProcessosTop {
    public static void main(String[] args) {
        /*SystemInfo systemInfo = new SystemInfo();
        List<OSProcess> processos = systemInfo.getOperatingSystem().getProcesses();
        List<Processos> listaProcessos = new ArrayList<>();
        for (OSProcess processo : processos) {
            boolean processoJaFoiColhido = false;

            for (Processos p : listaProcessos) {
                if (processo.getName().contains(p.getNome())) {
                    p.incrementThreads(processo.getThreadCount());
                    p.incrementResidentSize(processo.getResidentSetSize());
                    p.incrementVirtualMemory(processo.getVirtualSize());

                    processoJaFoiColhido = true;
                    break;
                }
            }

            if (!processoJaFoiColhido) {
                Date dataAtual = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Formata a data atual no formato desejado
                String dataFormatada = dateFormat.format(dataAtual);
                listaProcessos.add(new Processos(processo.getName(), processo.getThreadCount(), processo.getResidentSetSize(),dataFormatada));
            }

        }

        List<Processos> processosOrdenadosPorMemoria = bubbleSort(listaProcessos);
        Integer threadsTotais = 0;
        long usoMemoriaTotal = 0;
        for (Processos processo : processosOrdenadosPorMemoria) {
            threadsTotais += processo.getThreads() ;
            usoMemoriaTotal += processo.getResidentMemory();
            System.out.println("Nome do processo: " + processo.getNome());
            System.out.println("Total de Threads: " + processo.getThreads());
            System.out.println("Total de ResidentMemory : " + Conversor.formatarBytes(processo.getResidentMemory()));
            System.out.println("----------------------------");
        }

        System.out.println("Total Threads " + threadsTotais);
        System.out.println("Uso memoria total processos " + Conversor.formatarBytes(usoMemoriaTotal));
        System.out.println(String.format("Uso Cpu %.2f%% ", new Processador().getUso()));
        System.out.println("Uso memória Looca " +Conversor.formatarBytes(new Memoria().getEmUso()));




    }


    static List<Processos> bubbleSort(List<Processos> listaProcessos) {
        int n = listaProcessos.size();
        long tempRM = 0;
        String tempNome = "";
        long tempVM = 0;
        int tempThreads = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (listaProcessos.get(j - 1).getResidentMemory() > listaProcessos.get(j).getResidentMemory()) {
                    //swap elements
                    tempRM = listaProcessos.get(j - 1).getResidentMemory();
                    listaProcessos.get(j - 1).setResidentMemory(listaProcessos.get(j).getResidentMemory());
                    listaProcessos.get(j).setResidentMemory(tempRM);

                    tempNome = listaProcessos.get(j - 1).getNome();
                    listaProcessos.get(j - 1).setNome(listaProcessos.get(j).getNome());
                    listaProcessos.get(j).setNome(tempNome);

                    tempVM = listaProcessos.get(j - 1).getVirtualMemory();
                    listaProcessos.get(j - 1).setVirtualMemory(listaProcessos.get(j).getVirtualMemory());
                    listaProcessos.get(j).setVirtualMemory(tempVM);

                    tempThreads = listaProcessos.get(j - 1).getThreads();
                    listaProcessos.get(j - 1).setThreads(listaProcessos.get(j).getThreads());
                    listaProcessos.get(j).setThreads(tempThreads);
                }
            }
            }
        return listaProcessos;*/
        }
    }

