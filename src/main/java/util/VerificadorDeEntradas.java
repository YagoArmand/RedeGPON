package main.java.util;

import main.java.model.ProjetoPON;

public class VerificadorDeEntradas {

    public static String verificar(ProjetoPON projeto) {
        StringBuilder erros = new StringBuilder();

        Double Pt = projeto.getPotenciaTx();
        Double Sr = projeto.getSensibilidadeRx();
        Double Af = projeto.getAtenuacaoFibra();
        Double Cf = projeto.getComprimentoFibra();
        Double Pc = projeto.getPerdaConector();
        Integer Nc = projeto.getQtdConectores();
        Double Ps = projeto.getPerdaSplitter();
        Double Ms = projeto.getMargemSeguranca();

        if (Pt != null && (Pt < 0 || Pt > 10))
            erros.append("⚠️ Potência de transmissão (Pt) fora do intervalo típico (0 a 10 dBm).\n");

        if (Sr != null && (Sr < -33 || Sr > -18))
            erros.append("⚠️ Sensibilidade do receptor (Sr) fora do intervalo (-33 a -18 dBm).\n");

        if (Af != null && (Af < 0.2 || Af > 0.5))
            erros.append("⚠️ Atenuação da fibra (Af) fora do intervalo (0.2 a 0.5 dB/km).\n");

        if (Cf != null && (Cf < 1 || Cf > 20))
            erros.append("⚠️ Comprimento da fibra (Cf) fora do intervalo (1 a 20 km).\n");

        if (Pc != null && (Pc < 0.2 || Pc > 1.0))
            erros.append("⚠️ Perda por conector (Pc) fora do intervalo (0.2 a 1.0 dB).\n");

        if (Nc != null && (Nc < 0 || Nc > 10))
            erros.append("⚠️ Quantidade de conectores (Nc) fora do intervalo (0 a 10).\n");

        if (Ps != null && (Ps < 0 || Ps > 18))
            erros.append("⚠️ Perda por splitter (Ps) fora do intervalo (0 a 18 dB).\n");

        if (Ms != null && (Ms < 1 || Ms > 6))
            erros.append("⚠️ Margem de segurança (Ms) fora do intervalo (1 a 6 dB).\n");

        return erros.toString();
    }
}
