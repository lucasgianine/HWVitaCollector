package gui;

import DAO.FuncionarioDAO;
import DAO.MaquinaDAO;
import entidades.Funcionario;
import entidades.Maquina;
import oshi.SystemInfo;

import javax.swing.*;
import java.awt.event.*;

public class CadastroMaquina extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField localLabel;
    private JTextField responsavelLabel;
    private JLabel labelCamposIncompletos;

    private static String emailFuncionario;
    private static String senhaFuncionario;

    public CadastroMaquina() {
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
        if(localLabel.getText().isEmpty()|| responsavelLabel.getText().isEmpty()){
            labelCamposIncompletos.setText("Preencha todos os campos primeiro");
            return;
        }
        Funcionario funcionario = FuncionarioDAO.getFuncionario(emailFuncionario,senhaFuncionario);
        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();
        assert funcionario != null;
        Maquina maquina = new Maquina(UUID,funcionario.getFkHospital(),localLabel.getText(),responsavelLabel.getText());
        MaquinaDAO.registrarMaquina(maquina);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void CadastrarMaquina(Funcionario funcionario) {
        CadastroMaquina dialog = new CadastroMaquina();
        emailFuncionario = funcionario.getEmail();
        senhaFuncionario = funcionario.getSenha();
        dialog.setBounds(0,0,320,240);
        dialog.setVisible(true);
    }
}
