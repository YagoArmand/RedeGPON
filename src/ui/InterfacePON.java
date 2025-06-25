package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacePON extends JFrame {

    // Campos de entrada
    private JTextField potenciaTxField;
    private JTextField sensibilidadeRxField;
    private JTextField atenuacaoFibraField;
    private JTextField comprimentoFibraField;
    private JTextField perdasConectoresField;
    private JTextField perdasSplittersField;

    // Área de saída
    private JTextArea resultadoArea;

    public InterfacePON() {
        super("Calculadora de Projeto PON");

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        JPanel painelEntradas = new JPanel(new GridLayout(7, 2, 5, 5));

        // Campos de entrada
        painelEntradas.add(new JLabel("Potência de Transmissão (dBm):"));
        potenciaTxField = new JTextField();
        painelEntradas.add(potenciaTxField);

        painelEntradas.add(new JLabel("Sensibilidade de Recepção (dBm):"));
        sensibilidadeRxField = new JTextField();
        painelEntradas.add(sensibilidadeRxField);

        painelEntradas.add(new JLabel("Atenuação da Fibra (dB/km):"));
        atenuacaoFibraField = new JTextField();
        painelEntradas.add(atenuacaoFibraField);

        painelEntradas.add(new JLabel("Comprimento da Fibra (km):"));
        comprimentoFibraField = new JTextField();
        painelEntradas.add(comprimentoFibraField);

        painelEntradas.add(new JLabel("Perdas por Conectores (dB):"));
        perdasConectoresField = new JTextField();
        painelEntradas.add(perdasConectoresField);

        painelEntradas.add(new JLabel("Perdas por Splitters (dB):"));
        perdasSplittersField = new JTextField();
        painelEntradas.add(perdasSplittersField);

        // Botão de cálculo
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularProjeto();
            }
        });
        painelEntradas.add(calcularBtn);

        // Área de resultado
        resultadoArea = new JTextArea(5, 30);
        resultadoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultadoArea);

        // Adicionando à janela
        add(painelEntradas, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Configurações da janela
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcularProjeto() {
        // Aqui será implementada a lógica de cálculo
        resultadoArea.setText("Cálculos serão implementados aqui.\n");

        // Você pode adicionar validações:
        if (potenciaTxField.getText().isEmpty() || sensibilidadeRxField.getText().isEmpty()) {
            resultadoArea.append("⚠️ Por favor, preencha os campos obrigatórios.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfacePON::new);
    }
}
