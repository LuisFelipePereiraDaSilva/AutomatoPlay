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

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Construa um AFD para a seguinte Expressão Regular: ab(bb)*cc*",
                new AFD("s0_true_false & s1_false_false & s2_false_false & s3_false_false & s4_false_true",
                        "s0_a_s1 & s1_b_s2 & s2_b_s3 & s3_b_s2 & s2_c_s4 & s4_c_s4", new String[]{"a", "b", "c"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Construa um AFD para a seguinte Expressão Regular: cc*b*+ab*cc*",
                new AFD("s0_true_false & s1_false_true & s2_false_true & s3_false_false & s4_false_true",
                        "s0_c_s1 & s1_c_s1 & s1_b_s2 & s2_b_s2 & s0_a_s3 & s3_b_s3 & s3_c_s4 & s4_c_s4", new String[]{"a", "b", "c"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Construa um AFD para a seguinte Expressão Regular: bcc*(b+a)*",
                new AFD("s0_true_false & s1_false_false & s2_false_true & s3_false_true",
                        "s0_b_s1 & s1_c_s2 & s2_c_s2 & s2_b,a_s3 & s3_b,a_s3", new String[]{"a", "b", "c"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Descreva o diagrama associada ao AFD descrito pela seguinte tabela de transição: (*# afd_q4 #*) A = < {a, b}, {S0, S1, S2, S3}, S0, δ, {S0, S2} >.",
                new AFD("s0_true_true & s1_false_false & s2_false_true & s3_false_false",
                        "s0_a_s1 & s0_b_s3 & s1_a_s2 & s1_b_s0 & s2_a_s3 & s2_b_s1 & s3_a_s0 & s3_b_s2", new String[]{"a", "b"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Dado seguinte autômato A = < {0,1}, {S0, S1, S2, S3}, S0, δ, {S3} >  com a tabela de transição mostrada na figura (*# afd_q5 #*), construa o diagrama equivalente desse autômato:",
                new AFD("s0_true_false & s01_false_false & s02_false_false & s012_false_false & s0123_false_true & s013_false_true & s023_false_true & s03_false_true",
                        "s0_1_s0 & s0_0_s01 & s01_0_s012 & s01_1_s02 & s012_0_s0123 & s012_1_s02 & s02_1_s0 & s02_0_s013 & s0123_0_s0123 & s0123_1_s023 & s013_0_s0123 & s013_1_s023 & s023_0_s013 & s023_1_s03 & s03_0_s013 & s03_1_s03", new String[]{"0", "1"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Dado o alfabeto ∑ = {a,b}, construa o AFD para a seguinte linguagem: {b(ab)n b | n≥0}",
                new AFD("s0_true_false & s1_false_false & s2_false_false & s3_false_true",
                        "s0_b_s1 & s1_a_s2 & s1_b_s3 & s2_b_s1", new String[]{"a", "b"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Dado o alfabeto ∑ = {a,b}, construa o AFD para a seguinte linguagem: { ban ba | n≥0}",
                new AFD("s0_true_false & s1_false_false & s2_false_false & s3_false_true",
                        "s0_b_s1 & s1_a_s1 & s1_b_s2 & s2_b_s3", new String[]{"a", "b"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Seja ∑ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, construa o AFD para a seguinte linguagem: {x ∈∑ | a sequência descrita por x corresponda a um valor inteiro par}",
                new AFD("s0_true_false & s1_false_true",
                        "s0_1,3,5,7,9_s0 & s0_0,2,4,6,8_s1 & s1_0,2,4,6,8_s1 & s1_1,3,5,7,9_s0", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Seja ∑ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, construa o AFD para a seguinte linguagem: {x ∈∑ | a sequência descrita por x corresponda a um valor inteiro divisível por 5}",
                new AFD("s0_true_false & s1_false_true",
                        "s0_1,2,3,4,6,7,8,9_s0 & s0_0,5_s1 & s1_0,5_s1 & s1_1,2,3,4,6,7,8,9_s0", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Seja ∑ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, construa o AFD para a seguinte linguagem: {x ∈ ∑ | a sequência descrita por x corresponda a um valor inteiro ímpar}",
                new AFD("s0_true_false & s1_false_true",
                        "s0_0,2,4,6,8_s0 & s0_1,3,5,7,9_s1 & s1_1,3,5,7,9_s1 & s1_0,2,4,6,8_s0", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) ) );

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
