package fp.br.com.senai.fplocacoes.models;

import java.io.Serializable;

public class TransporteRent implements Serializable {

    private Long idAlg;
    private String dtAlugacao;
    private String dtDevolucao;
    private String passageiros;
    private String origem;
    private String destino;
    private String ImgCarro;

    public Long getIdAlg() {
        return idAlg;
    }

    public void setIdAlg(Long idAlg) {
        this.idAlg = idAlg;
    }

    public String getDtAlugacao() {
        return dtAlugacao;
    }

    public void setDtAlugacao(String dtAlugacao) {
        this.dtAlugacao = dtAlugacao;
    }

    public String getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(String dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public String getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(String passageiros) {
        this.passageiros = passageiros;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getImgCarro() {
        return ImgCarro;
    }

    public void setImgCarro(String imgCarro) {
        ImgCarro = imgCarro;
    }
}
