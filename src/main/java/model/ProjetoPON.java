package main.java.model;

public class ProjetoPON {
    private Double potenciaTx;
    private Double sensibilidadeRx;
    private Double atenuacaoFibra;
    private Double comprimentoFibra;
    private Double perdaConector;
    private Integer qtdConectores;
    private Double perdaSplitter;
    private Double margemSeguranca;

    public Double getPotenciaTx() { return potenciaTx; }
    public void setPotenciaTx(Double potenciaTx) { this.potenciaTx = potenciaTx; }

    public Double getSensibilidadeRx() { return sensibilidadeRx; }
    public void setSensibilidadeRx(Double sensibilidadeRx) { this.sensibilidadeRx = sensibilidadeRx; }

    public Double getAtenuacaoFibra() { return atenuacaoFibra; }
    public void setAtenuacaoFibra(Double atenuacaoFibra) { this.atenuacaoFibra = atenuacaoFibra; }

    public Double getComprimentoFibra() { return comprimentoFibra; }
    public void setComprimentoFibra(Double comprimentoFibra) { this.comprimentoFibra = comprimentoFibra; }

    public Double getPerdaConector() { return perdaConector; }
    public void setPerdaConector(Double perdaConector) { this.perdaConector = perdaConector; }

    public Integer getQtdConectores() { return qtdConectores; }
    public void setQtdConectores(Integer qtdConectores) { this.qtdConectores = qtdConectores; }

    public Double getPerdaSplitter() { return perdaSplitter; }
    public void setPerdaSplitter(Double perdaSplitter) { this.perdaSplitter = perdaSplitter; }

    public Double getMargemSeguranca() { return margemSeguranca; }
    public void setMargemSeguranca(Double margemSeguranca) { this.margemSeguranca = margemSeguranca; }
}

