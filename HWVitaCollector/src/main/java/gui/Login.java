package gui;

import DAO.FuncionarioDAO;
import DAO.MaquinaDAO;
import entidades.Funcionario;
import entidades.Maquina;
import helpers.VerificacaoHelper;
import oshi.SystemInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
    public static int fkFuncionarioStatic ;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel labelAlerta;

    private static Login d;

    public static void mainLogin() {
        Login dialog = new Login();
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        d = dialog;

    }
    public Login() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();
        String emailFuncionario = textField1.getText();
        String senhaFuncionario = passwordField1.getText();

        Funcionario funcionario = FuncionarioDAO.getFuncionario(emailFuncionario,senhaFuncionario);
        if(VerificacaoHelper.funcionarioIsAutenticado(funcionario)){
            fkFuncionarioStatic = funcionario.getFkHospital();
            if(VerificacaoHelper.maquinaIsCadastrada(UUID)){
                System.out.println("Inicializando programa...");
                dispose();
            }else{
                CadastroMaquina.CadastrarMaquina(funcionario);
                onOK();
            }
        dispose();
        }else{
            labelAlerta.setText("Usuário não encontrado, tente novamente");
        }
    }

    private void onCancel() {
        System.exit(0);
        dispose();
    }

}
