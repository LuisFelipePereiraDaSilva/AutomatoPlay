package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2;

import android.content.Context;

import com.example.montanhadechomsky.dados.automatos.ambiente2.DadosAmbiente2;

import java.util.ArrayList;

public class Ambiente2 {

    private String arquivo = "Ambiente2";
    public String getArquivo(){
        return arquivo;
    }

    private ArrayList<ProblemaAmbiente2> lista_problemas = new ArrayList<ProblemaAmbiente2>();
    private ProblemaAmbiente2 questao_selecionada;
    private DadosAmbiente2 repositorio;

    public Ambiente2(Context context){
        repositorio = new DadosAmbiente2(context);
    }

    public ArrayList<ProblemaAmbiente2> getListaProblemas(){
        return lista_problemas;
    }

    public ProblemaAmbiente2 getQuestao_selecionada(){
        return questao_selecionada;
    }

    public void selecionarQuestao(ProblemaAmbiente2 problema){
        questao_selecionada = problema;
    }

    public void carregarQuestoes(String arquivo){
        if(arquivo.equals("Ambiente2"))
            cadastrarQuestoesAfd();
        else
            lista_problemas = repositorio.ler(arquivo);

        this.arquivo = arquivo;
    }

    // colocar imagem tag (*# nome da imagem #*)
    // estado => separa os estados por & e os atributos do estado por _ Ex: q1_true_false (nome: q1, estado_inicial: true, estado_aceitação: false)
    // transicao => separa as trancisoes por "&" e os atributos da transicao por "_" e os simbolos por "," Ex: q1_0,1_q2 (nome: q1, estado_inicial: true, estado_final: false)
    // Espaços são ignorados nos estados e nas transições
    private ProblemaAmbiente2 montarQuestao(int numero, String problema, AFD afd){
        ProblemaAmbiente2 p = repositorio.getProblema(arquivo, numero);
        if(p == null)
            p = new ProblemaAmbiente2(numero, problema, afd);
        return p;
    }
    public void cadastrarQuestoesAfd(){

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1, "testando problema 2",
                new AFD("q1_true_false & q2_false_true",
                        "q1_0_q2 & q1_1_q2 & q2_0_q2 & q2_1_q2", new String[]{"0", "1"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Construa um AFD para a seguinte Expressão Regular: ab(bb)*cc*",
                new AFD("s0_true_false & s1_false_false & s2_false_false & s3_false_false & s4_false_true",
                        "s0_a_s1 & s1_b_s2 & s2_b_s3 & s3_b_s2 & s2_c_s4 & s4_c_s4", new String[]{"a", "b", "c"}) ) );

    }

    public ProblemaAmbiente2 getProblema(int numero){
        try{
            return lista_problemas.get(numero);
        }catch (Exception e){
            return null;
        }
    }

    public void salvarProblema(ProblemaAmbiente2 problema){
        repositorio.salvar(arquivo, problema);
    }

    public void removerProblema(ProblemaAmbiente2 problema){
        repositorio.remover(arquivo, problema);
    }
}
