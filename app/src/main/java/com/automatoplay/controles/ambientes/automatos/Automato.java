package com.automatoplay.controles.ambientes.automatos;

import java.io.Serializable;
import java.util.ArrayList;

public class Automato implements Serializable {

    private ArrayList<Estado> estados = new ArrayList<Estado>();
    private String[] alfabeto;

    //region construtores

    public Automato(ArrayList<Estado> estados, String[] alfabeto) {
        this.estados = estados;
        this.alfabeto = alfabeto;
    }

    public Automato(String estados, String trasicoes, String[] alfabeto) {
        this.alfabeto = alfabeto;
        setEstados(estados);
        // Só coloque as trasições depois de adicionar os estados
        adicionarTransicoes(trasicoes);
    }

    //endregion

    //region Transicoes

    private void adicionarTransicoes(String transicoes){
        if (!transicoes.equals("")) {
            String[] transicoes2 = transicoes.replace(" ", "").split("&");
            for (int i = 0; i < transicoes2.length; i++) {
                String[] transicoes3 = transicoes2[i].split("_");
                Estado estado1 = null;
                Estado estado2 = null;
                for (int j = 0; j < estados.size(); j++) {
                    if (transicoes3[0].equals(estados.get(j).getNome())) {
                        estado1 = estados.get(j);
                    }
                    if (transicoes3[2].equals(estados.get(j).getNome())) {
                        estado2 = estados.get(j);
                    }
                    if (estado1 != null && estado2 != null) {
                        boolean flag = false;
                        for (int x = 0; x < estado1.getTransicoes().size(); x++) {
                            if (estado1.getTransicoes().get(x).getEstadoDestino() == estado2) {
                                estado1.getTransicoes().get(x).editarSimbolosTransicao(transicoes3[1]);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag)
                            estado1.adicionarTransicao(transicoes3[1].split(","), estado2);
                        break;
                    }
                }
            }
        }
    }

    //endregion

    //region Estados

    private void adicionarEstados(String estados){
        String[] estados2 = estados.replace(" ", "").split("&");
        for(int i = 0; i < estados2.length; i++){
            String[] estados3 = estados2[i].split("_");
            adicionarEstado( estados3[0], Boolean.parseBoolean(estados3[1]), Boolean.parseBoolean(estados3[2]));
        }
    }

    public void adicionarEstado(String nome, boolean estado_inicial, boolean estado_final){
        estados.add(new Estado(nome, estado_inicial, estado_final));
    }

    public void adicionarEstado(String nome, boolean estado_inicial, boolean estado_final, ArrayList<Transicao> transicoes){
        estados.add(new Estado(nome, estado_inicial, estado_final, transicoes));
    }

    public void adicionarEstado(String nome, boolean estado_inicial, boolean estado_final, String transicoes, ArrayList<Estado> estados){
        estados.add(new Estado(nome, estado_inicial, estado_final, transicoes, estados));
    }

    public void adicionarEstado(Estado e){
        estados.add(e);
    }

    public void removerEstadp(Estado e){
        estados.remove(e);
    }

    public void removerEstadp(int i){
        estados.remove(i);
    }

    public void editarTransicao(Estado estado_antigo, Estado estado_novo){
        estado_antigo.setNome(estado_novo.getNome());
        estado_antigo.setEstado_inicial(estado_novo.getEstado_inicial());
        estado_antigo.setEstado_aceitacao(estado_novo.getEstado_aceitacao());
        estado_antigo.setTransicoes(estado_novo.getTransicoes());
    }

    public Estado getEstadoInicial(){
        for(int i = 0; i < estados.size(); i++){
            if(estados.get(i).getEstado_inicial()){
                return estados.get(i);
            }
        }
        return null;
    }

    public Estado getEstadoInicial(ArrayList<Estado> estados){
        for(int i = 0; i < estados.size(); i++){
            if(estados.get(i).getEstado_inicial()){
                return estados.get(i);
            }
        }
        return null;
    }

    //endregion

    //region Gets e Sets

    public void setThis(ArrayList<Estado> estados, String[] alfabeto){
        this.estados = estados;
        this.alfabeto = alfabeto;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public void setEstados(String estados) {
        adicionarEstados(estados);
    }

    public String[] getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    //endregion
}
