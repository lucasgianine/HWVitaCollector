package DAO;

import com.github.britooo.looca.api.util.Conversor;
import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.ProcessoRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessoDAO {



    public static void inserirRegistroProcesso(ProcessoRegistro processoRegistro){
        try {
            inserirRegistroProcessoLocal(processoRegistro);
            //inserirRegistroProcessoNuvem(processoRegistro);
        }catch (Exception e){
            System.out.println("Paia");
            throw new RuntimeException(e);
        }

    }


    public static void inserirRegistroProcessoLocal(ProcessoRegistro processoRegistro){
        String sql = "INSERT INTO ProcessoRegistro (fkMaquina,nome,dtRegistro,threads,usoMemoriaRam) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, processoRegistro.getFkMaquina());
            ps.setString(2, processoRegistro.getNome());
            ps.setString(3, processoRegistro.getDtRegistro());
            ps.setInt(4, processoRegistro.getThreads());
            ps.setString(5, Conversor.formatarBytes(processoRegistro.getResidentMemory()));
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de processo  (Local)                                                              |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,processoRegistro));
            ps.close();

        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void inserirRegistroProcessoNuvem(ProcessoRegistro processoRegistro){
        String sql = "INSERT INTO ProcessoRegistro (fkMaquina,nome,dtRegistro,threads,usoMemoriaRam) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = ConexaoNuvem.getConexaoNuvem().prepareStatement(sql);
            ps.setString(1, processoRegistro.getFkMaquina());
            ps.setString(2, processoRegistro.getNome());
            ps.setString(3, processoRegistro.getDtRegistro());
            ps.setInt(4, processoRegistro.getThreads());
            ps.setString(5, Conversor.formatarBytes(processoRegistro.getResidentMemory()));
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de processo  (Nuvem)                                                              |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,processoRegistro));
            ps.close();

        }catch (SQLException  | ClassNotFoundException e){
            throw  new RuntimeException(e);
        }
    }
}

