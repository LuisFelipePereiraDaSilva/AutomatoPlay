package com.example.montanhadechomsky.controles.ambientes;

import java.io.Serializable;

public class ProblemaAmbientes implements Serializable {

    public enum StatusResposta {Correta, Errada, NaoRespondida}

    private int numero;
    private StatusResposta status_resposta;
    private String problema;

    public ProblemaAmbientes(int numero, String problema){
        this.numero = numero;
        this.problema = problema;
        this.status_resposta = StatusResposta.NaoRespondida;
    }

    public ProblemaAmbientes(){
        this.status_resposta = StatusResposta.NaoRespondida;
    }

    public ProblemaAmbientes(int numero){
        this.numero = numero;
        this.status_resposta = StatusResposta.NaoRespondida;
    }

    public void setThis(int numero, StatusResposta status){
        this.numero = numero;
        this.status_resposta = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public StatusResposta getStatus_resposta() {
        return status_resposta;
    }

    public void setStatus_resposta(StatusResposta status_resposta) {
        this.status_resposta = status_resposta;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
