package com.example.montanhadechomsky.controles.ambientes.ambiente1;

import android.content.Context;

import com.example.montanhadechomsky.dados.ambiente1.DadosAmbiente1;

import java.util.ArrayList;

public class Ambiente1 {

    private String arquivo = "Ambiente1";
    public String getArquivo(){
        return arquivo;
    }

    private ArrayList<ProblemaAmbiente1> lista_problemas = new ArrayList<ProblemaAmbiente1>();
    private ProblemaAmbiente1 questao_selecionada;
    private DadosAmbiente1 repositorio;

    public Ambiente1(Context context){
        repositorio = new DadosAmbiente1(context);
    }

    public ArrayList<ProblemaAmbiente1> getListaProblemas(){
        return lista_problemas;
    }

    public ProblemaAmbiente1 getQuestao_selecionada(){
        return questao_selecionada;
    }

    public void selecionarQuestao(ProblemaAmbiente1 problema){
        questao_selecionada = problema;
    }

    public void carregarQuestoes(String arquivo){
        if(arquivo.equals("Ambiente1"))
            cadastrarQuestoes();
        else
            lista_problemas = repositorio.ler(arquivo);

        this.arquivo = arquivo;
    }

    // colocar imagem tag (*# nome da imagem #*)
    // colocar um texto em negrito, coloca entre & texto &
    // se tiver mais de uma resposta, separe por & as respostas
    private ProblemaAmbiente1 montarQuestao(int numero, String problema, ER er){
        ProblemaAmbiente1 p = repositorio.getProblema(arquivo, numero);
        if(p == null)
            p = new ProblemaAmbiente1(numero, problema, er);
        return p;
    }
    public void cadastrarQuestoes(){

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1, "Descreva a Expressão Regular equivalente ao autômato " +
                "representado pelo diagrama mostrado na imagem: (*#q1_er#*)", new ER("a(aa)*bc*d", new String[]{"a","b","c","d"})));

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1, "problema 2: (*#q1_er#*)",
                new ER("a(aa)*bc*d", new String[]{"a","b","c","d"})));
    }

    public ProblemaAmbiente1 getProblema(int numero){
        try{
            return lista_problemas.get(numero);
        }catch (Exception e){
            return null;
        }
    }

    public void salvarProblema(ProblemaAmbiente1 problema){
        repositorio.salvar(arquivo, problema);
    }

    public void removerProblema(ProblemaAmbiente1 problema){
        repositorio.remover(arquivo, problema);
    }
}
