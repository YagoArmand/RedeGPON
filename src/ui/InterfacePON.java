package ui;

import calcs.CalcularProjeto;
import verify.VerificadorDeEntradas;

import javax.swing.*;
import java.awt.*;

public class InterfacePON extends JFrame {

    private JTextField potenciaTxField;
    private JTextField sensibilidadeRxField;
    private JTextField atenuacaoFibraField;
    private JTextField comprimentoFibraField;
    private JTextField perdasConectoresField;
    private JTextField quantidadeConectoresField;
    private JTextField perdasSplittersField;
    private JTextField margemSegurancaField;
    private JTextArea resultadoArea;

    public InterfacePON() {
        super("Calculadora de Projeto PON");

        setLayout(new BorderLayout(10, 10));
        JPanel painelEntradas = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Campo: Potência de Transmissão
        painelEntradas.add(new JLabel("Potência de Transmissão (dBm):"), gbc);
        gbc.gridx = 1;
        potenciaTxField = new JTextField(10);
        painelEntradas.add(potenciaTxField, gbc);

        // Campo: Sensibilidade de Recepção
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Sensibilidade de Recepção (dBm):"), gbc);
        gbc.gridx = 1;
        sensibilidadeRxField = new JTextField(10);
        painelEntradas.add(sensibilidadeRxField, gbc);

        // Campo: Atenuação da Fibra
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Atenuação da Fibra (dB/km):"), gbc);
        gbc.gridx = 1;
        atenuacaoFibraField = new JTextField(10);
        painelEntradas.add(atenuacaoFibraField, gbc);

        // Campo: Comprimento da Fibra
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Comprimento da Fibra (km):"), gbc);
        gbc.gridx = 1;
        comprimentoFibraField = new JTextField(10);
        painelEntradas.add(comprimentoFibraField, gbc);

        // Campo: Perdas por Conectores + Quantidade
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Perdas por Conectores (dB):"), gbc);
        gbc.gridx = 1;
        JPanel conectoresPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        perdasConectoresField = new JTextField(5);
        quantidadeConectoresField = new JTextField(3);
        conectoresPanel.add(perdasConectoresField);
        conectoresPanel.add(new JLabel("Qtd:"));
        conectoresPanel.add(quantidadeConectoresField);
        painelEntradas.add(conectoresPanel, gbc);

        // Campo: Perdas por Splitters
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Perdas por Splitters (dB):"), gbc);
        gbc.gridx = 1;
        perdasSplittersField = new JTextField(10);
        painelEntradas.add(perdasSplittersField, gbc);

        // Campo: Margem de Segurança
        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(new JLabel("Margem de Segurança (dB):"), gbc);
        gbc.gridx = 1;
        margemSegurancaField = new JTextField(10);
        painelEntradas.add(margemSegurancaField, gbc);

        // Botão Calcular
        gbc.gridy++;
        gbc.gridx = 1;
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.addActionListener(e -> calcularProjeto());
        painelEntradas.add(calcularBtn, gbc);

        // Área de resultado
        resultadoArea = new JTextArea(6, 40);
        resultadoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultadoArea);

        add(painelEntradas, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcularProjeto() {
        resultadoArea.setText("");

        try {
            Double Pt = parseOrNull(potenciaTxField.getText());
            Double Sr = parseOrNull(sensibilidadeRxField.getText());
            Double Af = parseOrNull(atenuacaoFibraField.getText());
            Double Cf = parseOrNull(comprimentoFibraField.getText());
            Double Pc = parseOrNull(perdasConectoresField.getText());
            Integer Nc = parseIntOrNull(quantidadeConectoresField.getText());
            Double Ps = parseOrNull(perdasSplittersField.getText());
            Double Ms = parseOrNull(margemSegurancaField.getText());

            // Chama a verificação
            String erros = VerificadorDeEntradas.verificar(Pt, Sr, Af, Cf, Pc, Nc, Ps, Ms);
            if (!erros.isEmpty()) {
                resultadoArea.setText(erros);  // exibe os erros e não calcula
            return;
        }

            String resultado = CalcularProjeto.calcular(Pt, Sr, Af, Cf, Pc, Nc, Ps, Ms);
            resultadoArea.setText(resultado);

        } catch (Exception e) {
            resultadoArea.setText("❌ Erro: " + e.getMessage());
        }
    }

    private Double parseOrNull(String text) {
        text = text.trim();
        return text.isEmpty() ? null : Double.parseDouble(text);
    }

    private Integer parseIntOrNull(String text) {
        text = text.trim();
        return text.isEmpty() ? null : Integer.parseInt(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfacePON::new);
    }
}