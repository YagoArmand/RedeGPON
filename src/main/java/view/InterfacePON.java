package main.java.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.java.controller.CalcularProjeto;
import main.java.model.ProjetoPON;
import main.java.util.VerificadorDeEntradas;

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

    private final Color azulClaro = new Color(100, 149, 237);
    private final Color cinzaClaro = new Color(245, 245, 245);
    private final Color textoCinza = new Color(60, 60, 60);

    public InterfacePON() {
        super("Calculadora de Projeto PON");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntradas = new JPanel(new GridBagLayout());
        painelEntradas.setBackground(Color.WHITE);
        painelEntradas.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font fonteRotulo = new Font("SansSerif", Font.BOLD, 13);

        painelEntradas.add(criarLabel("Potência de Transmissão (dBm):", fonteRotulo), gbc);
        gbc.gridx = 1;
        potenciaTxField = criarCampoTexto();
        painelEntradas.add(potenciaTxField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Sensibilidade de Recepção (dBm):", fonteRotulo), gbc);
        gbc.gridx = 1;
        sensibilidadeRxField = criarCampoTexto();
        painelEntradas.add(sensibilidadeRxField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Atenuação da Fibra (dB/km):", fonteRotulo), gbc);
        gbc.gridx = 1;
        atenuacaoFibraField = criarCampoTexto();
        painelEntradas.add(atenuacaoFibraField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Comprimento da Fibra (km):", fonteRotulo), gbc);
        gbc.gridx = 1;
        comprimentoFibraField = criarCampoTexto();
        painelEntradas.add(comprimentoFibraField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Perdas por Conectores (dB):", fonteRotulo), gbc);
        gbc.gridx = 1;
        JPanel conectoresPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        conectoresPanel.setBackground(Color.WHITE);
        perdasConectoresField = criarCampoTexto(5);
        quantidadeConectoresField = criarCampoTexto(3);
        conectoresPanel.add(perdasConectoresField);
        conectoresPanel.add(criarLabel("Qtd:", fonteRotulo));
        conectoresPanel.add(quantidadeConectoresField);
        painelEntradas.add(conectoresPanel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Perdas por Splitters (dB):", fonteRotulo), gbc);
        gbc.gridx = 1;
        perdasSplittersField = criarCampoTexto();
        painelEntradas.add(perdasSplittersField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        painelEntradas.add(criarLabel("Margem de Segurança (dB):", fonteRotulo), gbc);
        gbc.gridx = 1;
        margemSegurancaField = criarCampoTexto();
        painelEntradas.add(margemSegurancaField, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.setBackground(azulClaro);
        calcularBtn.setForeground(Color.WHITE);
        calcularBtn.setFocusPainted(false);
        calcularBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        calcularBtn.setBorder(new LineBorder(azulClaro.darker(), 1, true));
        calcularBtn.addActionListener(e -> calcularProjeto());
        painelEntradas.add(calcularBtn, gbc);

        resultadoArea = new JTextArea(6, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultadoArea.setBackground(cinzaClaro);
        resultadoArea.setForeground(textoCinza);
        resultadoArea.setBorder(new LineBorder(azulClaro, 1, true));
        resultadoArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(resultadoArea);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(painelEntradas, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        label.setForeground(new Color(50, 50, 50));
        return label;
    }

    private JTextField criarCampoTexto() {
        return criarCampoTexto(10);
    }

    private JTextField criarCampoTexto(int cols) {
        JTextField campo = new JTextField(cols);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        campo.setBackground(Color.WHITE);
        return campo;
    }

    private void calcularProjeto() {
        resultadoArea.setText("");

        try {
            ProjetoPON projeto = new ProjetoPON();
            projeto.setPotenciaTx(parseDouble(potenciaTxField.getText()));
            projeto.setSensibilidadeRx(parseDouble(sensibilidadeRxField.getText()));
            projeto.setAtenuacaoFibra(parseDouble(atenuacaoFibraField.getText()));
            projeto.setComprimentoFibra(parseDouble(comprimentoFibraField.getText()));
            projeto.setPerdaConector(parseDouble(perdasConectoresField.getText()));
            projeto.setQtdConectores(parseInt(quantidadeConectoresField.getText()));
            projeto.setPerdaSplitter(parseDouble(perdasSplittersField.getText()));
            projeto.setMargemSeguranca(parseDouble(margemSegurancaField.getText()));

            String erros = VerificadorDeEntradas.verificar(projeto);
            if (!erros.isEmpty()) {
                resultadoArea.setText(erros);
                return;
            }

            String resultado = CalcularProjeto.calcular(projeto);
            resultadoArea.setText(resultado);

        } catch (Exception e) {
            resultadoArea.setText("❌ Erro: " + e.getMessage());
        }
    }

    private Double parseDouble(String text) {
        text = text.trim();
        return text.isEmpty() ? null : Double.parseDouble(text);
    }

    private Integer parseInt(String text) {
        text = text.trim();
        return text.isEmpty() ? null : Integer.parseInt(text);
    }

}
