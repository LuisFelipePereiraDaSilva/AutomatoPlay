package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2;

import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;
import com.example.montanhadechomsky.controles.ambientes.automatos.ProblemaAutomatos;
import com.example.montanhadechomsky.fachadas.Controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProblemaAmbiente2 extends ProblemaAutomatos implements Serializable {

    private AFD automato;

    public ProblemaAmbiente2(){
        super();
    }

    public ProblemaAmbiente2(int numero){
        super(numero);
    }

    public ProblemaAmbiente2(int numero, String problema, AFD automato){
        super(numero, problema);
        this.automato = automato;
    }

    public void setThis(ProblemaAmbiente2 problema){
        this.automato = problema.automato;
        this.setPorcentagem(problema.getPorcentagem());
        this.setThis(problema.getNumero(), problema.getStatus_resposta(), problema.getResposta_submetida(), problema.getResposta_secundaria());
    }

    @Override
    public void setStatus_resposta(StatusResposta status_resposta) {
        super.setStatus_resposta(status_resposta);
        Controler.getControler().salvarProblemaAmbiente2(this);
    }

    @Override
    public void setResposta_submetida(ArrayList<Estado> resposta_submetida) {
        super.setResposta_submetida(resposta_submetida);
        super.setStatus_resposta(StatusResposta.Correta);
        Controler.getControler().salvarProblemaAmbiente2(this);
    }

    @Override
    public void setResposta_secundaria(ArrayList<Estado> resposta_secundaria) {
        super.setResposta_secundaria(resposta_secundaria);
        Controler.getControler().salvarProblemaAmbiente2(this);
    }

    public AFD getAutomato() {
        return automato;
    }

    public void setAutomato(AFD automato) {
        this.automato = automato;
    }

    public boolean validarRespostaUsuario(){
        return validarRespostaUsuario(automato, getResposta_secundaria());
    }
}
