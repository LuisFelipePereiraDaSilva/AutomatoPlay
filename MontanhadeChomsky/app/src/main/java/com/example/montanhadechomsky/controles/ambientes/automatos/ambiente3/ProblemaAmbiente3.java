package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3;

import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;
import com.example.montanhadechomsky.controles.ambientes.automatos.ProblemaAutomatos;
import com.example.montanhadechomsky.fachadas.Controler;

import java.io.Serializable;
import java.util.ArrayList;

public class ProblemaAmbiente3 extends ProblemaAutomatos implements Serializable {

    private AFND automato;

    public ProblemaAmbiente3(){
        super();
    }

    public ProblemaAmbiente3(int numero){
        super(numero);
    }

    public ProblemaAmbiente3(int numero, String problema, AFND automato){
        super(numero, problema);
        this.automato = automato;
    }

    public void setThis(ProblemaAmbiente3 problema){
        this.automato = problema.automato;
        this.setThis(problema.getNumero(), problema.getStatus_resposta(), problema.getResposta_submetida(), problema.getResposta_secundaria());
    }

    @Override
    public void setStatus_resposta(StatusResposta status_resposta) {
        super.setStatus_resposta(status_resposta);
        Controler.getControler().salvarProblemaAmbiente3(this);
    }

    @Override
    public void setResposta_submetida(ArrayList<Estado> resposta_submetida) {
        super.setResposta_submetida(resposta_submetida);
        super.setStatus_resposta(StatusResposta.Correta);
        Controler.getControler().salvarProblemaAmbiente3(this);
    }

    @Override
    public void setResposta_secundaria(ArrayList<Estado> resposta_secundaria) {
        super.setResposta_secundaria(resposta_secundaria);
        Controler.getControler().salvarProblemaAmbiente3(this);
    }

    public AFND getAutomato() {
        return automato;
    }

    public void setAutomato(AFND automato) {
        this.automato = automato;
    }

}
