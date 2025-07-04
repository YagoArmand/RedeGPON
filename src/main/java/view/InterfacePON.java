package main.java.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.java.controller.CalcularProjeto;
import main.java.model.ProjetoPON;
import main.java.util.VerificadorDeEntradas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfacePON extends JFrame {

    // --- PALETA DE CORES MODERNA ---
    private final Color COR_FUNDO = new Color(248, 249, 250);
    private final Color COR_PRINCIPAL = new Color(0, 123, 255);
    private final Color COR_PRINCIPAL_HOVER = new Color(0, 105, 217);
    private final Color COR_TEXTO_TITULO = new Color(52, 58, 64);
    private final Color COR_TEXTO_LABEL = new Color(73, 80, 87);
    private final Color COR_BORDA = new Color(206, 212, 218);
    private final Color COR_RESULTADO_FUNDO = new Color(233, 236, 239);
    private final Color COR_CAMPO_DESABILITADO = new Color(233, 236, 239);

    private JComboBox<String> variavelComboBox;
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
        configurarJanela();

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(20, 25, 20, 25)); // Aumentado padding lateral
        add(painelPrincipal);

        JLabel tituloLabel = criarTitulo("Calculadora de rede GPON");
        painelPrincipal.add(tituloLabel, BorderLayout.NORTH);

        JPanel painelEntradas = criarPainelDeEntradas();
        painelPrincipal.add(painelEntradas, BorderLayout.CENTER);

        JScrollPane painelResultado = criarPainelDeResultado();
        painelPrincipal.add(painelResultado, BorderLayout.SOUTH);
        
        atualizarEstadoDosCampos();

        setVisible(true);
    }

    private void configurarJanela() {
        setSize(900, 800); // Aumentado a largura para comportar os novos textos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
    }

    private JLabel criarTitulo(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(COR_TEXTO_TITULO);
        label.setBorder(new EmptyBorder(0, 0, 15, 0));
        return label;
    }

    private JPanel criarPainelDeEntradas() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.45; // Ajustado peso para labels mais longas

        painel.add(criarLabel("Variável a Calcular:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.55; // Ajustado peso
        String[] opcoes = {
            "Potência de Transmissão (Pt)", "Sensibilidade de Recepção (Sr)", "Atenuação da Fibra (Af)",
            "Comprimento da Fibra (Cf)", "Perda por Conector (Pc)", "Quantidade de Conectores (Nc)",
            "Perda por Splitter (Ps)", "Margem de Segurança (Ms)"
        };
        variavelComboBox = new JComboBox<>(opcoes);
        variavelComboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        variavelComboBox.addActionListener(e -> atualizarEstadoDosCampos());
        painel.add(variavelComboBox, gbc);

        // --- Labels atualizadas com os intervalos de validação ---
        potenciaTxField = adicionarLinhaDeEntrada(painel, gbc, "Potência Tx (dBm) [0 a 10]:", 1); // LABEL ATUALIZADA
        sensibilidadeRxField = adicionarLinhaDeEntrada(painel, gbc, "Sensibilidade Rx (dBm) [-33 a -18]:", 2); // LABEL ATUALIZADA
        atenuacaoFibraField = adicionarLinhaDeEntrada(painel, gbc, "Atenuação Fibra (dB/km) [0.2 a 0.5]:", 3); // LABEL ATUALIZADA
        comprimentoFibraField = adicionarLinhaDeEntrada(painel, gbc, "Comprimento Fibra (km) [1 a 20]:", 4); // LABEL ATUALIZADA

        // Campo customizado para Conectores
        gbc.gridy = 5;
        gbc.gridx = 0;
        painel.add(criarLabel("Perda p/ Conector (dB) [0.2 a 1.0]:"), gbc); // LABEL ATUALIZADA
        gbc.gridx = 1;
        JPanel conectoresPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        conectoresPanel.setBackground(COR_FUNDO);
        perdasConectoresField = criarCampoTexto(8);
        quantidadeConectoresField = criarCampoTexto(4);
        conectoresPanel.add(perdasConectoresField);
        conectoresPanel.add(criarLabel("Qtd [0 a 10]:")); // LABEL ATUALIZADA
        conectoresPanel.add(quantidadeConectoresField);
        painel.add(conectoresPanel, gbc);

        perdasSplittersField = adicionarLinhaDeEntrada(painel, gbc, "Perda p/ Splitter (dB) [0 a 18]:", 6); // LABEL ATUALIZADA
        margemSegurancaField = adicionarLinhaDeEntrada(painel, gbc, "Margem de Segurança (dB) [1 a 6]:", 7); // LABEL ATUALIZADA

        // Botão de Calcular
        gbc.gridy = 8;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 5, 5, 5);
        JButton calcularBtn = criarBotaoPrincipal("Calcular");
        calcularBtn.addActionListener(e -> calcularProjeto());
        painel.add(calcularBtn, gbc);

        return painel;
    }

    private void habilitarCampo(JTextField campo) {
        campo.setEnabled(true);
        campo.setBackground(Color.WHITE);
    }

    private void desabilitarCampo(JTextField campo) {
        campo.setEnabled(false);
        campo.setBackground(COR_CAMPO_DESABILITADO);
        campo.setText("");
    }

    private void atualizarEstadoDosCampos() {
        habilitarCampo(potenciaTxField);
        habilitarCampo(sensibilidadeRxField);
        habilitarCampo(atenuacaoFibraField);
        habilitarCampo(comprimentoFibraField);
        habilitarCampo(perdasConectoresField);
        habilitarCampo(quantidadeConectoresField);
        habilitarCampo(perdasSplittersField);
        habilitarCampo(margemSegurancaField);

        String selecionada = (String) variavelComboBox.getSelectedItem();
        if (selecionada == null) return;

        switch (selecionada) {
            case "Potência de Transmissão (Pt)":
                desabilitarCampo(potenciaTxField);
                break;
            case "Sensibilidade de Recepção (Sr)":
                desabilitarCampo(sensibilidadeRxField);
                break;
            case "Atenuação da Fibra (Af)":
                desabilitarCampo(atenuacaoFibraField);
                break;
            case "Comprimento da Fibra (Cf)":
                desabilitarCampo(comprimentoFibraField);
                break;
            case "Perda por Conector (Pc)":
                desabilitarCampo(perdasConectoresField);
                break;
            case "Quantidade de Conectores (Nc)":
                desabilitarCampo(quantidadeConectoresField);
                break;
            case "Perda por Splitter (Ps)":
                desabilitarCampo(perdasSplittersField);
                break;
            case "Margem de Segurança (Ms)":
                desabilitarCampo(margemSegurancaField);
                break;
        }
    }


    private JTextField adicionarLinhaDeEntrada(JPanel painel, GridBagConstraints gbc, String label, int yPos) {
        gbc.gridy = yPos;
        gbc.gridx = 0;
        painel.add(criarLabel(label), gbc);
        gbc.gridx = 1;
        JTextField campoTexto = criarCampoTexto();
        painel.add(campoTexto, gbc);
        return campoTexto;
    }

    private JScrollPane criarPainelDeResultado() {
        resultadoArea = new JTextArea(7, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultadoArea.setBackground(COR_RESULTADO_FUNDO);
        resultadoArea.setForeground(COR_TEXTO_LABEL);
        resultadoArea.setMargin(new Insets(15, 15, 15, 15));
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        resultadoArea.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scroll = new JScrollPane(resultadoArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        return scroll;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(COR_TEXTO_LABEL);
        return label;
    }

    private JTextField criarCampoTexto() {
        return criarCampoTexto(15);
    }

    private JTextField criarCampoTexto(int cols) {
        JTextField campo = new JTextField(cols);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setForeground(COR_TEXTO_TITULO);
        campo.setBackground(Color.WHITE);
        Border bordaComPadding = BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(8, 10, 8, 10)
        );
        campo.setBorder(bordaComPadding);
        return campo;
    }

    private JButton criarBotaoPrincipal(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 15));
        botao.setBackground(COR_PRINCIPAL);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COR_PRINCIPAL, 2, true),
            new EmptyBorder(10, 25, 10, 25)
        ));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botao.setBackground(COR_PRINCIPAL_HOVER);
                botao.setBorder(new LineBorder(COR_PRINCIPAL_HOVER, 2, true));
            }

            public void mouseExited(MouseEvent evt) {
                botao.setBackground(COR_PRINCIPAL);
                botao.setBorder(new LineBorder(COR_PRINCIPAL, 2, true));
            }
        });
        return botao;
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

        } catch (NumberFormatException e) {
             resultadoArea.setText("❌ Erro: Verifique se todos os campos preenchidos contêm números válidos.");
        } catch (Exception e) {
            resultadoArea.setText("❌ Erro inesperado: " + e.getMessage());
        }
    }

    private Double parseDouble(String text) {
        text = text.trim();
        if (text.isEmpty()) return null;
        return Double.parseDouble(text.replace(",", "."));
    }

    private Integer parseInt(String text) {
        text = text.trim();
        return text.isEmpty() ? null : Integer.parseInt(text);
    }
}