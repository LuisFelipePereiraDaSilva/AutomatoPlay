package com.example.montanhadechomsky.controles.ambientes.automatos;

import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.AFD;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.ProblemaAmbiente2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    public boolean validarRespostaUsuario(AFD automatoResposta, ArrayList<Estado> automatoUsuario){

        Queue<ParEstados> fila = new LinkedList<ParEstados>();
        ArrayList<ParEstados> pares = new ArrayList<ParEstados>();

        Estado estado_inicial1 = automatoResposta.getEstadoInicial();
        Estado estado_inicial2 = automatoResposta.getEstadoInicial(automatoUsuario);
        if ( estado_inicial1 != null && estado_inicial2 != null && estado_inicial1.getEstado_aceitacao() == estado_inicial2.getEstado_aceitacao() )  {
            ParEstados par = new ParEstados(automatoResposta.getEstadoInicial(), automatoResposta.getEstadoInicial(automatoUsuario));
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

            for (int i = 0; i < automatoResposta.getAlfabeto().length; i++) {

                auxUserTrans = auxUserFila.buscarTransicao(automatoResposta.getAlfabeto()[i]);
                auxCorrTrans = auxCorrFila.buscarTransicao(automatoResposta.getAlfabeto()[i]);

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
