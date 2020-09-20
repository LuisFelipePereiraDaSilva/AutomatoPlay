package com.example.montanhadechomsky.controles.ambientes.automatos;

import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;

import java.io.Serializable;
import java.util.ArrayList;

public class ProblemaAutomatos extends ProblemaAmbientes implements Serializable {

    private ArrayList<Estado> resposta_submetida = new ArrayList<Estado>();
    private ArrayList<Estado> resposta_secundaria = new ArrayList<Estado>();

    public ProblemaAutomatos(){
        super();
    }

    public ProblemaAutomatos(int numero){
        super(numero);
    }

    public ProblemaAutomatos(int numero, String problema){
        super(numero, problema);
    }

    public void setThis(int numero, StatusResposta status, ArrayList<Estado> resposta_submetida, ArrayList<Estado> resposta_secundaria){
        super.setThis(numero, status);
        this.resposta_submetida = resposta_submetida;
        this.resposta_secundaria = resposta_secundaria;
    }

    @Override
    public void setStatus_resposta(StatusResposta status_resposta) {
        super.setStatus_resposta(status_resposta);
    }

    public ArrayList<Estado> getResposta_submetida() {
        return resposta_submetida;
    }

    public void setResposta_submetida(ArrayList<Estado> resposta_submetida) {
        this.resposta_submetida = resposta_submetida;
    }

    public ArrayList<Estado> getResposta_secundaria() {
        return resposta_secundaria;
    }

    public void setResposta_secundaria(ArrayList<Estado> resposta_secundaria) {
        this.resposta_secundaria = resposta_secundaria;
    }

    public int getPorcentagem() {
        return super.getPorcentagem();
    }

    public void setPorcentagem(int porcentagem) {
        super.setPorcentagem(porcentagem);
    }
}
