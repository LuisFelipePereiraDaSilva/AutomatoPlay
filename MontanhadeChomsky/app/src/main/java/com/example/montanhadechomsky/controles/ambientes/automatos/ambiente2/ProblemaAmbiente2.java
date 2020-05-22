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

        Queue<ParEstados> fila = new LinkedList<ParEstados>();
        ArrayList<ParEstados> pares = new ArrayList<ParEstados>();

        Estado estado_inicial1 = automato.getEstadoInicial();
        Estado estado_inicial2 = automato.getEstadoInicial(getResposta_secundaria());
        if ( estado_inicial1 != null && estado_inicial2 != null && estado_inicial1.getEstado_aceitacao() == estado_inicial2.getEstado_aceitacao() )  {
            ParEstados par = new ParEstados(automato.getEstadoInicial(), automato.getEstadoInicial(getResposta_secundaria()));
            fila.add(par);
            pares.add(par);
        } else {
            return false;
        }

        while (!fila.isEmpty()) {
            Estado auxUserFila = null;
            Estado auxCorrFila = null;
            Estado auxUserTrans = null;
            Estado auxCorrTrans = null;

            auxUserFila = fila.element().estado1;
            auxCorrFila = fila.element().estado2;
            fila.remove();

            for (int i = 0; i < automato.getAlfabeto().length; i++) {

                auxUserTrans = auxUserFila.buscarTransicao(automato.getAlfabeto()[i]);
                auxCorrTrans = auxCorrFila.buscarTransicao(automato.getAlfabeto()[i]);

                if ( ( auxUserTrans != null && auxCorrTrans != null && auxUserTrans.getEstado_aceitacao() == auxCorrTrans.getEstado_aceitacao() )
                    || (auxUserTrans == null && auxCorrTrans == null) ) {

                    if(auxUserTrans != null && auxCorrTrans != null) {
                        ParEstados par = new ParEstados(auxUserTrans, auxCorrTrans);
                        boolean contemPar = pares.contains(par);

                        if (!contemPar) {
                            fila.add(par);
                            pares.add(par);
                        }
                    }
                } else {
                    return false;
                }

            }

        }

        return true;
    }

    private class ParEstados{
        private Estado estado1;
        private Estado estado2;

        public ParEstados(Estado estado1, Estado estado2){
            this.estado1 = estado1;
            this.estado2 = estado2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ParEstados a = (ParEstados) obj;
            return a.estado1.equals(this.estado1) && a.estado2.equals(this.estado2);
        }
    }

}
