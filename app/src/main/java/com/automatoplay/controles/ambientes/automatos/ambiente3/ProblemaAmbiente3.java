package com.automatoplay.controles.ambientes.automatos.ambiente3;

import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.controles.ambientes.automatos.ProblemaAutomatos;
import com.automatoplay.controles.ambientes.automatos.ambiente2.AFD;
import com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.controler.Conversor;
import com.automatoplay.fachadas.Controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        super.setPorcentagemRespostaCorreta(super.getPorcentagem());
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

    public EntradaAFND converterEntradaAFND(ArrayList<Estado> listaEstados) {
        String estados = "{";
        String estadosFinais = "{";

        ArrayList<String> trasicoesAux = new ArrayList<>();
        for(int i = 0; i < listaEstados.size(); i++) {
            estados += listaEstados.get(i).getNome() + (i < (listaEstados.size() - 1) ? "," : "");
            if (listaEstados.get(i).getEstado_aceitacao())
                estadosFinais += listaEstados.get(i).getNome() + ",";
            for (int j = 0; j < automato.getAlfabeto().length; j++) {
                String transicao = "(" + listaEstados.get(i).getNome() + "," + automato.getAlfabeto()[j] + ")->{";
                boolean existe = false;
                for (int x = 0; x < listaEstados.get(i).getTransicoes().size(); x++) {
                    for (int y = 0; y < listaEstados.get(i).getTransicoes().get(x).getSimbolo_alfabeto().length ; y++) {
                        if (listaEstados.get(i).getTransicoes().get(x).getSimbolo_alfabeto()[y].equals(
                                automato.getAlfabeto()[j])) {
                            transicao += listaEstados.get(i).getTransicoes().get(x).getEstadoDestino().getNome() + ",";
                            existe = true;
                        }
                    }
                }
                if (existe) {
                    transicao += "}";
                    trasicoesAux.add(transicao.replace(",}", "}"));
                }
            }
        }

        estados += "},";
        estadosFinais += "}";
        estadosFinais = estadosFinais.replace(",}", "}");

        List<String> transicoes = new ArrayList<>();
        for (int i = 0; i < trasicoesAux.size(); i++)
            transicoes.add(trasicoesAux.get(i) + (i < (trasicoesAux.size() - 1) ? "," : ""));

        String estadoInicial = null;
        for(int i = 0; i<listaEstados.size();i++){
            if(listaEstados.get(i).getEstado_inicial()==true){
                estadoInicial = listaEstados.get(i).getNome()+",";
                break;
            }
        }

        //String estadoInicial = automato.getEstadoInicial().getNome() + ",";

        String alfabeto = "{";
        for (int i = 0; i < automato.getAlfabeto().length; i++) {
            alfabeto += automato.getAlfabeto()[i] + ((i < automato.getAlfabeto().length - 1) ? "," : "");
        }
        alfabeto += "},";

        EntradaAFND entrada = new EntradaAFND(estados, alfabeto, transicoes, estadoInicial, estadosFinais);
        return entrada;
    }

    public boolean validarRespostaUsuario(){
        boolean estadoIncial = false;
        boolean transicoes = false;

        for (int i = 0; i < getResposta_secundaria().size(); i++) {
            if (getResposta_secundaria().get(i).getEstado_inicial())
                estadoIncial = true;
            if (getResposta_secundaria().get(i).getTransicoes().size() > 0)
                transicoes = true;
            if (estadoIncial && transicoes)
                break;
        }

        if (!estadoIncial || !transicoes)
            return false;

        EntradaAFND afndResposta = converterEntradaAFND(automato.getEstados());
        EntradaAFND afndUsuario = converterEntradaAFND(getResposta_secundaria());

        AFD afdResposta = Conversor.converterAfndParaAfd(afndResposta.estados, afndResposta.alfabeto, afndResposta.transicoes,afndResposta.estadoInicial, afndResposta.estadosFinais);
        AFD afdUsuario = Conversor.converterAfndParaAfd(afndUsuario.estados, afndUsuario.alfabeto, afndUsuario.transicoes,afndUsuario.estadoInicial, afndUsuario.estadosFinais);

        if (afdResposta == null || afdUsuario == null)
            return false;

        boolean resultado = validarRespostaUsuario(afdResposta, afdUsuario.getEstados());
        return resultado;
    }

    private class EntradaAFND {
        public String estados;
        private List<String> transicoes;
        private String estadoInicial;
        private String estadosFinais;
        private String alfabeto;

        public EntradaAFND(String estados, String alfabeto, List<String> transicoes, String estadoInicial, String estadosFinais) {
            this.estados = estados;
            this.transicoes = transicoes;
            this.estadoInicial = estadoInicial;
            this.estadosFinais = estadosFinais;
            this.alfabeto = alfabeto;
        }
    }
}
