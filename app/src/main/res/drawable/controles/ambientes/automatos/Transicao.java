package com.automatoplay.controles.ambientes.automatos;

import java.io.Serializable;

public class Transicao implements Serializable {

    private int id;
    private String[] simbolo_alfabeto;
    private Estado estado_destino;
    private float[] cordenadas_seta = new float[4];
    private float[] cordenadas_simbolos = new float[2];

    public Transicao(Transicao t, Estado estado_destino){
        this.id = t.id;

        String resultado = "";
        for(int i = 0; i < t.simbolo_alfabeto.length; i++)
            resultado += t.simbolo_alfabeto[i] + " ";
        resultado = resultado.trim().replace(" ", ",");
        this.simbolo_alfabeto = resultado.split(",");

        this.estado_destino = estado_destino;
        this.cordenadas_seta = new float[]{t.cordenadas_seta[0], t.cordenadas_seta[1], t.cordenadas_seta[2], t.cordenadas_seta[3]};
        this.cordenadas_simbolos = new float[]{t.cordenadas_simbolos[0], t.cordenadas_simbolos[1]};
    }

    public Transicao(String[] simbolo_alfabeto, Estado estado) {
        this.simbolo_alfabeto = simbolo_alfabeto;
        this.estado_destino = estado;
    }

    public Transicao(String[] simbolo_alfabeto, Estado estado, float[] cordenadas_seta) {
        this.simbolo_alfabeto = simbolo_alfabeto;
        this.estado_destino = estado;
        this.cordenadas_seta = cordenadas_seta;
    }

    public void editarSimbolosTransicao(String simbolo){
        String resultado = "";
        for(int i = 0; i < simbolo_alfabeto.length; i++)
            resultado+= simbolo_alfabeto[i] + ",";
        resultado = resultado + simbolo;
        simbolo_alfabeto = resultado.split(",");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getSimbolo_alfabeto() {
        return simbolo_alfabeto;
    }

    public void setSimbolo_alfabeto(String[] simbolo_alfabeto) {
        this.simbolo_alfabeto = simbolo_alfabeto;
    }

    public Estado getEstadoDestino() {
        return estado_destino;
    }

    public void setEstadoDestino(Estado estado) {
        this.estado_destino = estado;
    }

    public float[] getCordenadas_seta() {
        return cordenadas_seta;
    }

    public void setCordenadas_seta(float[] cordenadas_seta) {
        this.cordenadas_seta = cordenadas_seta;
    }

    public float[] getCordenadas_simbolos() {
        return cordenadas_simbolos;
    }

    public void setCordenadas_simbolos(float[] cordenadas_simbolos) {
        this.cordenadas_simbolos = cordenadas_simbolos;
    }
}
