package com.example.montanhadechomsky.controles.ambientes.ambiente1;

import java.io.Serializable;

public class ER implements Serializable {

    private String[] alfabeto;
    private String expressao;

    public ER(String expressao, String[] alfabeto) {
        this.alfabeto = alfabeto;
        this.expressao = expressao;
    }

    public String[] getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }
}
