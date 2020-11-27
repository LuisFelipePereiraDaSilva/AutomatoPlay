package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3;

import android.content.Context;

import com.example.montanhadechomsky.dados.automatos.ambiente3.DadosAmbiente3;

import java.util.ArrayList;

public class Ambiente3 {

    private String arquivo = "Ambiente3";
    public String getArquivo(){
        return arquivo;
    }

    private ArrayList<ProblemaAmbiente3> lista_problemas = new ArrayList<ProblemaAmbiente3>();
    private ProblemaAmbiente3 questao_selecionada;
    private DadosAmbiente3 repositorio;

    public Ambiente3(Context context){
        repositorio = new DadosAmbiente3(context);
    }

    public ArrayList<ProblemaAmbiente3> getListaProblemas(){
        return lista_problemas;
    }

    public ProblemaAmbiente3 getQuestao_selecionada(){
        return questao_selecionada;
    }

    public void selecionarQuestao(ProblemaAmbiente3 problema){
        questao_selecionada = problema;
    }

    public void carregarQuestoes(String arquivo){
        if(arquivo.equals("Ambiente3"))
            cadastrarQuestoesAfnd();
        else
            lista_problemas = repositorio.ler(arquivo);

        this.arquivo = "Ambiente3";
    }

    // colocar imagem tag (*# nome da imagem #*)
    // estado => separa os estados por | e os atributos do estado por _ Ex: q1_true_false (nome: q1, estado_inicial: true, estado_final: false)
    // transicao => separa as trancisoes por | e os atributos da transicao por _ Ex: q1_0_q2 (nome: q1, estado_inicial: true, estado_final: false)
    private ProblemaAmbiente3 montarQuestao(int numero, String problema, AFND afnd){
        ProblemaAmbiente3 p = repositorio.getProblema(arquivo, numero);
        if(p == null)
            p = new ProblemaAmbiente3(numero, problema, afnd);
        else
            p.setAutomato(afnd);
        return p;
    }
    public void cadastrarQuestoesAfnd(){

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1, "testando problema 1",
                new AFND("q1_true_false & q2_false_true & q3_false_true",
                        "q1_0_q2 & q1_0_q3 & q1_1_q1 & q2_0_q2 & q2_1_q3", new String[]{"0", "1", "£"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1, "testando problema 2",
                new AFND("q1_true_false|q2_false_true",
                        "q1_0_q2 & q1_1_q1 & q2_0_q2 & q2_1_q2", new String[]{"0", "1", "£"}) ) );

    }

    public ProblemaAmbiente3 getProblema(int numero){
        try{
            return lista_problemas.get(numero);
        }catch (Exception e){
            return null;
        }
    }

    public void salvarProblema(ProblemaAmbiente3 problema){
        repositorio.salvar(arquivo, problema);
    }
}
