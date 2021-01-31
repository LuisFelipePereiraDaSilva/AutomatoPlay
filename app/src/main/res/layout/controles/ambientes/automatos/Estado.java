package com.automatoplay.controles.ambientes.automatos;

import com.automatoplay.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Estado implements Serializable {

    private int id;
    private String nome;
    private boolean estado_inicial;
    private boolean estado_aceitacao;
    private ArrayList<Transicao> transicoes = new ArrayList<Transicao>();
    private float[] cordenadas = new float[2];

    //region Construtores

    public Estado(Estado estado){
        this.id = estado.id;
        this.nome = estado.nome;
        this.estado_inicial = estado.estado_inicial;
        this.estado_aceitacao = estado.estado_aceitacao;
        this.cordenadas = new float[]{estado.cordenadas[0], estado.cordenadas[1]};
    }

    public Estado(int numero, boolean estado_inicial, boolean estado_aceitacao){
        this.nome = "q" + numero;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
    }

    public Estado(int id, String nome, boolean estado_inicial, boolean estado_aceitacao, ArrayList<Transicao> transicoes, float[] cordenadas) {
        this.id = id;
        this.nome = nome;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
        this.transicoes = transicoes;
        this.cordenadas = cordenadas;
    }

    public Estado(int id, String nome, boolean estado_inicial, boolean estado_aceitacao, float[] cordenadas) {
        this.id = id;
        this.nome = nome;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
        this.transicoes = transicoes;
        this.cordenadas = cordenadas;
    }

    public Estado(String nome, boolean estado_inicial, boolean estado_aceitacao) {
        this.nome = nome;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
    }

    public Estado(String nome, boolean estado_inicial, boolean estado_aceitacao, ArrayList<Transicao> transicoes) {
        this.nome = nome;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
        this.transicoes = transicoes;
    }

    public Estado(String nome, boolean estado_inicial, boolean estado_aceitacao, String transicoes, ArrayList<Estado> estados) {
        this.nome = nome;
        this.estado_inicial = estado_inicial;
        this.estado_aceitacao = estado_aceitacao;
        setTransicoes(transicoes, estados);
    }

    //endregion

    //region Transicoes

    public void atualizarCordenadasMinhaSeta(float x, float y){
        for(int i = 0; i < transicoes.size(); i++){
            Transicao transicao = transicoes.get(i);
            transicao.getCordenadas_seta()[0] = x;
            transicao.getCordenadas_seta()[1] = y;
        }
    }

    public void atualizarSeta(float x, float y, float ax, float ay){
        for(int i = 0; i < transicoes.size(); i++){
            Transicao traisicao = transicoes.get(i);
            if(traisicao.getCordenadas_seta()[2] == x && traisicao.getCordenadas_seta()[3] == y){
                traisicao.getCordenadas_seta()[2] = ax;
                traisicao.getCordenadas_seta()[3] = ay;
                break;
            }
        }
    }

    private void adicionarTransicoes(String transicoes, ArrayList<Estado> estados){
        String[] transicoes2 = transicoes.split("|");
        for(int i = 0; i < transicoes2.length; i++){
            String[] transicoes3 = transicoes2[i].split("_");
            for(int j = 0; j < estados.size(); j++){
                if(transicoes3[1].equals(estados.get(j).nome)){
                    adicionarTransicao(transicoes3[0].split(","), estados.get(i));
                    break;
                }
            }
        }
    }

    public void adicionarTransicao(String[] simbolo_alfabeto, Estado estado){
        transicoes.add(new Transicao(simbolo_alfabeto, estado));
    }

    public void adicionarTransicao(Transicao t){
        transicoes.add(t);
    }

    public void removerTransicao(String simbolo_alfabeto, Estado estado){
        for(int i = 0; i < transicoes.size(); i++)
            if(transicoes.get(i).getSimbolo_alfabeto().equals(simbolo_alfabeto) && transicoes.get(i).getEstadoDestino().equals(estado)){
                transicoes.remove(i);
                break;
            }
    }

    public void removerTransicao(Transicao t){
        transicoes.remove(t);
    }

    public void removerSeta(float x, float y){
        for(int i = 0; i < transicoes.size(); i++){
            Transicao transicao = transicoes.get(i);
            if(transicao.getCordenadas_seta()[2] == x && transicao.getCordenadas_seta()[3] == y){
                transicoes.remove(transicao);
                break;
            }
        }
    }

    public void editarTransicao(String simbolo_alfabeto_antigo, Estado estado_antigo, String[] simbolo_alfabeto_novo, Estado estado_novo){
        for(int i = 0; i < transicoes.size(); i++)
            if(transicoes.get(i).getSimbolo_alfabeto().equals(simbolo_alfabeto_antigo) && transicoes.get(i).getEstadoDestino().equals(estado_antigo)){
                transicoes.get(i).setSimbolo_alfabeto(simbolo_alfabeto_novo);
                transicoes.get(i).setEstadoDestino(estado_novo);
                break;
            }
    }

    public void editarTransicao(Transicao transicao_antiga, Transicao transicao_nova){
        transicao_antiga.setSimbolo_alfabeto(transicao_nova.getSimbolo_alfabeto());
        transicao_antiga.setEstadoDestino(transicao_nova.getEstadoDestino());
    }

    public void editarTransicao(Transicao transicao_antiga, String[] simbolo_alfabeto_novo, Estado estado_novo){
        transicao_antiga.setSimbolo_alfabeto(simbolo_alfabeto_novo);
        transicao_antiga.setEstadoDestino(estado_novo);
    }

    public Estado buscarTransicao(String simbolo){

        for (int i = 0; i < transicoes.size(); i++){
            for(int j = 0; j < transicoes.get(i).getSimbolo_alfabeto().length; j++) {
                if (transicoes.get(i).getSimbolo_alfabeto()[j].equals(simbolo)) {
                    return transicoes.get(i).getEstadoDestino();
                }
            }
        }

        return null;
    }

    //endregion

    //region Gets e Sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getEstado_inicial() {
        return estado_inicial;
    }

    public void setEstado_inicial(boolean estado_inicial) {
        this.estado_inicial = estado_inicial;
    }

    public boolean getEstado_aceitacao() {
        return estado_aceitacao;
    }

    public void setEstado_aceitacao(boolean estado_aceitacao) {
        this.estado_aceitacao = estado_aceitacao;
    }

    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public void setTransicoes(String transicoes, ArrayList<Estado> estados){
        adicionarTransicoes(transicoes, estados);
    }

    public float[] getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(float[] cordenadas) {
        this.cordenadas = cordenadas;
    }

    public int getImg() {
        if(!estado_inicial && !estado_aceitacao)
            return R.drawable.estado_normal;
        else if(estado_inicial && estado_aceitacao)
            return R.drawable.estado_inicial_aceitacao;
        else if(estado_inicial)
            return R.drawable.estado_inicial_normal;
        else
            return R.drawable.estado_aceitacao;
    }

    //endregion
}
