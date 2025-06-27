package calcs;

public class CalcularProjeto {

    public static String calcular(Double Pt, Double Sr, Double Af, Double Cf, Double Pc, Integer Nc, Double Ps, Double Ms) {
        int vazios = 0;
        if (Pt == null) vazios++;
        if (Sr == null) vazios++;
        if (Af == null) vazios++;
        if (Cf == null) vazios++;
        if (Pc == null) vazios++;
        if (Nc == null) vazios++;
        if (Ps == null) vazios++;
        if (Ms == null) vazios++;

        if (vazios > 1) {
            return "⚠️ Preencha todos os campos, exceto apenas UM para ser calculado.\n";
        }

        // Cálculo baseado na equação: Pt = Sr + (Af * Cf + Pc * Nc + Ps) + Ms
        if (Pt == null) {
            return "✔️ Potência de transmissão (Pt) = " + (Sr + (Af * Cf + Pc * Nc + Ps) + Ms) + " dBm";
        } else if (Sr == null) {
            return "✔️ Sensibilidade do receptor (Sr) = " + (Pt - (Af * Cf + Pc * Nc + Ps) - Ms) + " dBm";
        } else if (Af == null) {
            return "✔️ Atenuação da fibra (Af) = " + ((Pt - Sr - Pc * Nc - Ps - Ms) / Cf) + " dB/km";
        } else if (Cf == null) {
            return "✔️ Comprimento da fibra (Cf) = " + ((Pt - Sr - Pc * Nc - Ps - Ms) / Af) + " km";
        } else if (Pc == null) {
            return "✔️ Perda por conector (Pc) = " + ((Pt - Sr - Af * Cf - Ps - Ms) / Nc) + " dB";
        } else if (Nc == null) {
            return "✔️ Quantidade de conectores (Nc) = " + ((Pt - Sr - Af * Cf - Ps - Ms) / Pc);
        } else if (Ps == null) {
            return "✔️ Perda por splitter (Ps) = " + (Pt - Sr - Af * Cf - Pc * Nc - Ms) + " dB";
        } else if (Ms == null) {
            return "✔️ Margem de segurança (Ms) = " + (Pt - Sr - (Af * Cf + Pc * Nc + Ps)) + " dB";
        } else {
            return "⚠️ Nenhum campo está em branco. Deixe um vazio para que ele seja calculado.";
        }
    }
    
}