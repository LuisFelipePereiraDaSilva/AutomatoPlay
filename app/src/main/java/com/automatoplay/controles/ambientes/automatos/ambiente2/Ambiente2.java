package com.automatoplay.controles.ambientes.automatos.ambiente2;

import android.content.Context;

import com.automatoplay.dados.automatos.ambiente2.DadosAmbiente2;

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

        //Questoes pares de isaac
//Questoes impares de Guilherme

        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q1
                "Construa o Autômato Determinístico que aceita apenas a cadeia ‘a’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_true & q2_false_false",
                        "q0_a_q1 & q0_b_q2 & q2_a,b_q2 & q1_a,b_q2",new String[]{"a","b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1, //q2
                "Construa o Autômato Determinístico que aceita apenas a cadeia ‘ab’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q1 & q0_b_q2 & q1_a_q2 & q1_b_q3 & q2_a_q2 & q2_b_q2 & q3_a_q2 & q3_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q3
                "Construa o Autômato Determinístico que aceita apenas a cadeia ‘aab’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_a_q1 & q1_a_q2 & q2_b_q3 & q0_b_q4 & q1_b_q4 & q2_a_q4 & q3_a,b_q4 & q4_a,b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1, //q4
                "Construa o Autômato Determinístico que aceita a linguagem {a, ab} sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q1 & q2_b_q3 & q3_a_q1 & q3_b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q5
                "Construa o Autômato Determinístico que aceita a linguagem {a, ab, aab} sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_true & q2_false_true & q3_false_false & q4_false_false",
                        "q0_a_q1 & q1_b_q2 & q1_a_q3 & q3_b_q2 & q0_b_q4 & q2_a,b_q4 & q3_a_q4 & q4_a,b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q6
                "Construa o Autômato Determinístico que aceita apenas a cadeia vazia.",
                new AFD("q0_true_true",
                        "q0_e_q0",new String[]{"a","b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q7
                "Construa o Autômato Determinístico com linguagem vazia, isto é, não aceita nenhuma cadeia.",
                new AFD("q0_true_false",
                        "q0_e_q0",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q8
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} de comprimento par.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q2 & q1_b_q2 & q2_a_q1 & q2_b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q9
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} de comprimento ímpar.",
                new AFD("q0_true_false & q1_false_true & q2_false_false",
                        "q0_a,b_a1 & q1_a,b_q2 & q2_a,b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//10
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} de comprimento múltiplo de 3.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q2 & q1_b_q2 & q2_a_q3 & q2_b_q3 & q3_a_q1 & q3_b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q11
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} de comprimento múltiplo de 4.",
                new AFD("q0_true_true & q1_false_false & q2_false_false & q3_false_false",
                        "q0_a,b_q1 & q1_a,b_q2 & q2_a,b_q3 & q3_a,b_q0 ",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//12
                "Construa o Autômato Determinístico que aceita todas as cadeias que começam com ‘a’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q2 & q2_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//q13
                "Construa o Autômato Determinístico que aceita todas as cadeias que começam com ‘ab’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_false",
                        "q0_a_q1 & q1_b_q2 & q2_a,b_q2 & q0_b_q3 & q1_a_q3 & q3_a,b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//14
                "Construa o Autômato Determinístico que aceita todas as cadeias que começam com ‘aab’ sobre o alfabeto {a,b}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q3 & q2_b_q1 & q3_a_q1 & q3_b_q4 & q4_a_q4 & q4_b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//15
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que terminam com ‘a’.",
                new AFD("q0_true_false & q1_false_true",
                        "q0_a_q1 & q0_b_q0 & q1_b_q0 & q1_a_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//16
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que terminam com ‘ab’.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q1 & q0_b_q0 & q1_a_q1 & q1_b_q2 & q2_a_q0 & q2_b_q0",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//17
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que terminam com ‘aab’.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_b_q0 & q0_a_q1 & q1_b_q0 & q1_a_q2 & q2_a_q2 & q2_b_q2 & q3_a,b_q0",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//18
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘a’ como subcadeia.",
                new AFD("q0_true_false & q1_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q1 & q1_b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//19
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘ab’ como subcadeia.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_b_q0 & q0_a_q1 & q1_a_q1 & q1_b_q2 & q2_a,b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//20
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘aab’ como subcadeia.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q2 & q1_b_q0 & q2_a_q2 & q2_b_q3 & q3_a_q3 & q3_b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//21
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘a’ como subcadeia mas não como prefixo, isto é não pode começar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_false",
                        "q0_b_q1 & q1_b_q1 & q1_a_q2 & q2_a,b_q2 & q0_a_q3 & q3_a,b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//22
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘ab’ como subcadeia mas não como prefixo, isto é não pode começar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q3 & q1_b_q2 & q2_a_q2 & q2_b_q3 & q3_a_q3 & q3_b_q4 & q4_a_q4 & q4_b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//23
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘aab’ como subcadeia mas não como prefixo, isto é não pode começar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true & q5_false_false",
                        "q0_b_q1 & q1_b_q1 & q1_a_q2 & q2_b_q1 & q2_a_q3 & q3_a_q3 & q3_b_q4 & q4_a,b_q4 & q0_a_q5 & q5_a,b_q5",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//24
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘a’ como subcadeia mas não como sufixo, isto é não pode terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q1 & q0_b_q0 & q1_a_q1 & q1_b_q2 & q2_a_q1 & q2_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//25
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘ab’ como subcadeia mas não como sufixo, isto é não pode terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_b_q0 & q0_a_q1 & q1_a_q1 & q1_b_q2 & q2_a,b_q3 & q3_a,b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//26
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘aab’ como subcadeia mas não como sufixo, isto é não pode terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true & q5_false_true & q6_false_true",
                        "q0_a_q1 & q0_b_q0 & q1_a_q2 & q1_b_q0 & q2_a_q2 & q2_b_q3 & q3_a_q4 & q3_b_q6 & q4_a_q5 & q4_b_q4 & q5_a_q5 & q5_b_q3 & q6_a_q4 & q6_b_q6",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//27
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘a’ como subcadeia mas não como prefixo nem sufixo, isto é não pode nem começar e nem terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_b_q1 & q1_b_q1 & q1_a_q2 & q2_a_q2 & q2_b_q3 & q3_b_q3 & q3_a_q2 & q0_a_q4 & q4_a,b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//28
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘ab’ como subcadeia mas não como prefixo nem sufixo, isto é não pode nem começar e nem terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_true & q6_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q3 & q1_b_q2 & q2_a_q2 & q2_b_q2 & q3_a_q3 & q3_b_q4 & q4_a_q5 & q4_b_q6 & q5_a_q5 & q5_b_q4 & q6_a_q5 & q6_b_q6",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//29
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {a,b} que tem a ‘aab’ como subcadeia mas não como prefixo nem sufixo, isto é não pode nem começar e nem terminar com a subcadeia em questão.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_true & q6_false_false",
                        "q0_b_q1 & q1_b_q1 & q1_a_q2 & q2_a_q3 & q3_a_q3 & q3_b_q4 & q4_a,b_q5 & q4_a,b_q5 & q0_a_q6 & q2_b_q6 & q6_a,b_q6",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//30
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {1,0} em que o número de uns é múltiplo de 2 e o número de zeros é múltiplo de 3.",
                new AFD("q0_true_true & q1_false_true & q2_false_false & q3_false_false & q4_false_false & q5_false_false",
                        "q0_0_q2 & q0_1_q1 & q1_0_q3 & q1_1_q0 & q2_0_q4 & q2_1_q3 & q3_0_q5 & q3_1_q2 & q4_0_q1 & q4_1_q5 & q5_0_q1 & q5_1_q4",new String[]{"0", "1"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//31
                "Construa o Autômato Determinístico que aceita todas as cadeias sobre o alfabeto {1,2,3} em a soma dos símbolos da cadeia seja par.",
                new AFD("q0_true_true & q1_false_false & q2_false_false",
                        "q0_2_q0 & q0_1,3_q1 & q1_2_q2 & q1_1,3_q0 & q2_2_q2 & q2_1,3_q0",new String[]{"1","2","3"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//32
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a).",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q1 & q2_b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//33
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a*).",
                new AFD("q0_true_true & q1_false_false",
                        "q0_a_q0 & q0_b_q1 & q1_a,b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//34
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b).",
                new AFD("q0_true_false & q1_false_true & q2_false_false",
                        "q0_a_q1 & q0_b_q1 & q1_a_q2 & q1_b_q2 & q2_a_q2 & q2_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//35
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*.",
                new AFD("q0_true_true",
                        "q0_a,b_q0",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//36
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular a(a+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q2 & q2_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//37
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular a(a+b*).",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_false",
                        "q0_a_q1 & q1_a,b_q2 & q2_b_q2 & q0_b_q3 & q2_a_q3 & q3_a,b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//38
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular ab(a+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q1 & q1_b_q1 & q2_a_q1 & q2_b_q3 & q3_a_q3 & q3_b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//39
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*a.",
                new AFD("q0_true_true & q1_false_true & q2_false_false",
                        "q0_b_q0 & q0_a_q1 & q1_a_q1 & q1_b_q2 & q2_a,b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//40
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*ab.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_a_q1 & q0_b_q2 & q1_a_q1 & q1_b_q3 & q2_a_q1 & q2_b_q2 & q3_a_q4 & q3_b_q4 & q4_a_q4 & q4_b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//41
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*a(a+b)*.",
                new AFD("q0_true_false & q1_false_true",
                        "q0_b_q0 & q0_a_q1 & q1_a,b_q1",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//42
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*ab(a+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q2 & q1_b_q1 & q2_a_q2 & q2_b_q3 & q3_a_q3 & q3_b_q3",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//43
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)(a+b)*ab(a+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true & q5_false_false",
                        "q0_a,b_q1 & q1_a_q2 & q2_b_q1 & q2_a_q3 & q3_b_q4 & q4_q,b_q4 & q1_b_q5 & q3_a_q5 & q5_a,b_q5",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//44
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)*ab(a+b)*(a+b).",
                new AFD("q0_true_false & q1_false_fasle & q2_false_false & q3_false_false & q4_false_true",
                        "q0_a_q2 & q0_b_q1 & q1_a_q2 & q1_b_q1 & q2_a_q2 & q2_b_q3 & q3_a_q4 & q3_b_q4 & q4_a_q4 & q4_b_q4",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//45
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (a+b)(a+b)*ab(a+b)*(a+b).",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true & q5_false_false",
                        "q0_a,b_q1 & q1_a_q2 & q2_b_q1 & q2_a_q3 & q3_b_q4 & q4_q,b_q4 & q1_b_q5 & q3_a_q5 & q5_a,b_q5 ",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//46
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (aa+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_a_q1 & q0_b_q3 & q1_a_q3 & q1_b_q2 & q2_a_q2 & q2_b_q2 & q3_a_q4 & q3_b_q3 & q4_a_q3 & q4_b_q2",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//47
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (aa+bbb)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_false & q4_false_false & q5_false_false",
                        "q0_a_q1 & q1_a_q2 & q2_a,b_q0 & q0_b_q3 & q3_b_q4 & q4_b_q0 & q1_b_q5 & q3_a_q5 & q4_a_q5 & q5_a,b_q5",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//48
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular (aa+bbb)(a+b)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_true & q3_false_false & q4_false_false & q5_false_false",
                        "q0_a_q1 & q0_b_q3 & q1_a_q2 & q1_b_q5 & q2_a_q2 & q2_b_q2 & q3_a_q5 & q3_b_q4 & q4_a_q5 & q4_b_q2 & q5_a_q5 & q6_b_q6",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//49
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular ((aa+bbb)(a+b)*)*.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_true & q5_false_true & q6_false_false",
                        "q0_a_q1 & q1_a_q4 & q0_b_q2 & q2_b_q3 & q3_b_q4 & q4_a_q5 & q4_b_q0 & q5_a_q0 & q5_b_q4 & q1_b_q6 & q2_a_q6 & q3_a_q6 & q6_a,b_q6",new String[]{"a", "b"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,//50
                "Construa o Autômato Determinístico que aceita todas as cadeias representadas pela Expressão Regular ((aa+bbb)(a+b)*)*bb.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_true & q6_false_false & q7_false_false & q8_false_true",
                        "q0_a_q1 & q0_b_q2 & q1_a_q4 & q1_b_q3 & q2_a_q3 & q2_b_q5 & q3_a_q3 & q3_b_q3 & q4_a_q6 & q4_b_q7 & q5_a_q3 & q5_b_q4 & q6_a_q6 & q6_b_q7 & q7_a_q6 & q7_b_q8 & q8_a_q6 & q8_b_q8",new String[]{"a", "b"})));

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
                "Descreva o diagrama associada ao AFD descrito pela seguinte tabela de transição: (*# afd_img1 #*) A = < {a, b}, {S0, S1, S2, S3}, S0, δ, {S0, S2} >.",
                new AFD("s0_true_true & s1_false_false & s2_false_true & s3_false_false",
                        "s0_a_s1 & s0_b_s3 & s1_a_s2 & s1_b_s0 & s2_a_s3 & s2_b_s1 & s3_a_s0 & s3_b_s2", new String[]{"a", "b"}) ) );

        lista_problemas.add(montarQuestao(lista_problemas.size() + 1,
                "Dado seguinte autômato A = < {0,1}, {S0, S1, S2, S3}, S0, δ, {S3} >  com a tabela de transição mostrada na figura (*# afd_img2 #*), construa o diagrama equivalente desse autômato:",
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

        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Dado o alfabeto ∑ = {a,b}, construa o AFD para a linguagem {a^m b^n | m+n é par}.",
                new AFD("q0_true_true & q1_false_false & q2_false_true & q3_false_false",
                        "q0_a_q1 & q0_b_q3 & q1_a_q0 & q1_b_q2 & q2_b_q3 & q3_b_q2",new String[]{"a", "b"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Dado o alfabeto ∑ = {a,b}, construa o AFD para a linguagem {ab^m ba(ab)^n | m, n ≥0}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_a_q1 & q1_b_q2 & q2_a_q3 & q2_b_q2 & q3_a_q4 & q4_b_q3",new String[]{"a", "b"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa o AFD que comece ou termine com 01, ∑ = {0,1}.",
                new AFD("q1_true_false & q2_false_false & q3_false_true & q4_false_false & q5_false_false & q6_false_true",
                        "q1_0_q2 & q1_1_q4 & q2_1_q3 & q2_0_q5 & q3_0_q3 & q3_1_q3 & q4_0_q5 & q4_1_q4 & q5_0_q5 & q5_1_q6 & q6_0_q5 & q6_1_q4",new String[]{"0", "1"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa o AFD onde L= {w ∈ {a,b}* | w tem número par de a’s e par de b’s}.",
                new AFD("q1_true_true & q2_false_false & q3_false_false & q4_false_false",
                        "q1_a_q3 & q1_b_q2 & q2_a_q4 & q2_b_q1_& q3_a_q1 & q3_b_q4 & q4_a_q2 & q4_b_q3",new String[]{"a", "b"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa um AFD que reconheça a linguagem L={w|o comprimento de w é pelos menos 3 e o terceiro sımbolo é um 0}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true & q4_false_false",
                        "q0_0_q1 & q0_1_q1 & q1_0_q2 & q1_1_q2 & q3_0_q3 & q3_1_q3 & q4_0_q4 & q4_1_q4",new String[]{"0", "1"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa um AFD que reconheça a linguagem L={w|w contém um 0 em cada posição ímpar}.",
                new AFD("q0_true_true & q1_false_true & q2_false_false",
                        "q0_0_q1 & q0_1_q2 & q1_0_q0 & q1_1_q0 & q2_0_q2 & q2_1_q2",new String[]{"0", "1"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa um autômato ﬁnito determinístico que reconheça a linguagem {w|w não contém a sub cadeia 110}, sobre o alfabeto {0,1}.",
                new AFD(" q0_true_true & q1_false_true & q2_false_true & q3_false_false",
                        "q0_0_q0 & q0_1_q1 & q1_0_q0 & q1_1_q2 & q2_0_q3 & q2_1_q2 & q3_0_q3 & q3_1_q3",new String[]{"0", "1"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa um autômato ﬁnito determinístico que reconheça a linguagem {w|w contém uma quantidade ímpar de 1s ou exatamente dois 0s}, sobre o alfabeto {0,1}.",
                new AFD(" q0_true_false & q1_false_false & q2_false_true & q3_false_false & q4_false_true & q5_false_true & q6_false_true & q7_false_true",
                        "q0_0_q1 & q0_1_q4 & q1_0_q2 & q1_1_q5 & q2_0_q3 & q2_1_q6 & q3_0_q3 & q3_1_q7 & q4_0_q5 & q4_1_q0 & q5_0_q6 & q5_1_q1 & q6_0_q7 & q6_1_q2 & q7_0_q7 & q7_1_q3",new String[]{"1", "0"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Construa um autômato ﬁnito determinístico que reconheça a linguagem {w|w contém pelo menos dois 1s e no máximo dois 0s}, sobre o alfabeto {0,1}.",
                new AFD(" q0_true_false & q1_false_false & q2_false_true & q3_false_false & q4_false_false & q5_false_true & q6_false_false & q7_false_false & q8_false_true q9_false_false & q10_false_false & q11_false_false",
                        "q0_0_q3 & q0_1_q1 & q1_0_q4 & q1_1_q2 & q2_0_q5 & q2_1_q2 & q3_0_q6 & q3_1_q4 & q4_0_q7 & q4_1_q5 & q5_0_q8 & q5_1_q5 & q6_0_q9 & q6_1_q7 & q7_0_q10 & q7_1_q8 & q8_0_q11 & q8_1_q8 & q9_0_q9 & q9_1_q10 & q10_0_q10 & q10_1_q11 & q11_0_q11 & q11_1_q11",new String[]{"0", "1"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c} que reconheça a linguagem L = {w | w possui abc como prefixo}.",
                new AFD(" q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_a_q1 & q1_b_q2 & q2_c_q3 & q3_a_q3 & q3_b_q3 & q3_c_q3",new String[]{"a", "b", "c"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {x, y, z} que reconheça a linguagem L = {w | w possui xyx como subpalavra}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_x_q1 & q0_y_q0 & q0_z_q0 & q1_y_q2 & q1_x_q1 & q1_z_q0 & q2_x_q3 & q2_y_q0 & q2_z_q0 & q3_x_q3 & q3_y_q3 & q3_z_q3",new String[]{"x", "y", "z"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {i, j, k} que reconheça a linguagem L = {w | w possui kik como sufixo}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_true",
                        "q0_k_q1 & q0_i_q0 & q0_j_q0 & q1_i_q2 & q1_j_q0 & q1_k_q1 & q2_k_q3 & q2_i_q0 & q2_j_q0 & q3_i_q2 & q3_j_q0 & q3_k_q1",new String[]{"i", "j", "k"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c} que reconheça a linguagem L = {w | w possui abc como prefixo e bac como subpalavra}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_true",
                        "q0_a_q1 & q1_b_q2 & q2_c_q3 & q3_b_q4 & q3_a_q3 & q3_c_q3 & q4_a_q5 & q4_b_q4 & q4_c_q3 & q5_c_q6 & q5_a_q3 & q5_b_q4 & q6_a_q6 & q6_b_q6 & q6_c_q6",new String[]{"a", "b", "c"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c} que reconheça a linguagem L = {w | w possui bbc como prefixo e aba como sufixo}.",
                new AFD("q0_true_false & q1_false_true & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_true",
                        "q0_b_q1 & q1_b_q2 & q2_c_q3 & q3_a_q4 & q3_b_q3 & q3_c_q3 & q4_b_q5 & q4_a_q4 & q4_c_q3 & q5_a_q6 & q5_b_q3 & q5_c_q3 & q6_a_q4 & q6_b_q5 & q6_c_q3",new String[]{"a", "b", "c"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c} que reconheça a linguagem L = {w | w possui cca como subpalavra e bba como sufixo}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_true",
                        "q0_c_q1 & q0_a_q0 & q0_b_q0 & q1_c_q2 & q1_a_q0 & q1_b_q0 & q2_a_q3 & q2_b_q0 & q2_c_q2 & q3_b_q4 & q3_a_q3 & q3_c_q3 & q4_b_q5 & q4_a_q3 & q4_c_q3 & q5_a_q6 & q5_b_q5 & q5_c_q3 & q6_a_q3 & q6_b_q4 & q6_c_q3",new String[]{"a", "b", "c"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {x, y, z} que reconheça a linguagem L = {w | w possui xyz como prefixo e zyx como subpalavra}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_true",
                        "q0_x_q1 & q1_y_q2 & q2_z_q4 & q3_z_q4 & q3_x_q3 & q3_y_q3 & q4_y_q5 & q4_x_q2 & q4_z_q4 & q5_x_q6 & q5_y_q3 & q5_z_q4 & q6_x_q6 & q6_y_q6 & q6_z_q6",new String[]{"x", "y", "z"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c, d} que reconheça a linguagem L = {w | w possui abc como prefixo, dad como subpalavra e ccd como sufixo}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_true",
                        "q0_a_q1 & q1_b_q2 & q2_c_q3 & q3_d_q4_q3_a_q3 & q3_b_q3 & q3_c_q3 & q4_a_q5 & q4_b_q3 & q4_c_q3 & q4_d_q4 & q5_d_q6 & q5_a_q3 & q5_b_q3 & q5_c_q3 & q6_c_q7 & q6_a_q6 & q6_b_q6 & q6_d_q6 & q7_c_q8 & q7_a_q6 & q7_b_q6 & q7_d_q6 & q8_d_q9 & q8_a_q6 & q8_b_q6 & q8_c_q8 & q9_a_q6 & q9_b_q6 & q9_c_q7 & q9_d_q6",new String[]{"a", "b", "c", "d"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c, d} que reconheça a linguagem L = {w | w possui acdb como prefixo, babc como subpalavra e abab como sufixo}.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_true",
                        "q0_a_q1 & q1_c_q2 & q2_d_q3 & q3_b_q5 & q4_b_q5 & q4_a_q4 & q4_c_q4 & q4_d_q4 & q5_a_q6 & q5_b_q5 & q5_c_q4 & q5_d_q4 & q6_b_q7 & q6_a_q4 & q6_c_q4 & q6_d_q4 & q7_c_q8 & q7_a_q6 & q7_b_q5 & q7_d_q4 & q8_a_q9 & q8_b_8 & q8_c_8 & q8_d_8 & q9_d_q10 & q9_a_q9 & q9_b_q8 & q9_c_q8 & q10_a_q11 & q10_b_q8 & q10_c_q8 & q10_d_q8 & q11_b_q12 & q11_a_q9 & q11_c_q8 & q11_d_q8 & q12_a_q11 & q12_b_q8 & q12_c_q8 & q12_d_q8",new String[]{"a", "b", "c", "d"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Desenvolva um autômato finito determinístico sobre o alfabeto Σ = {a, b, c} que reconheça a linguagem L = {w | w possui [abc como prefixo e bba ou cab como subpalavra] ou [cba como prefixo e acc ou bab como subpalavra] e bac ou bcc como sufixo.",
                new AFD("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_false & q16_false_false & q17_false_false & q18_false_true",
                        "q0_a_q1 & q0_c_q3 & q1_b_q2 & q2_c_q8 & q3_b_q4 & q4_a_q14 & q5_a_q5 & q5_b_q6 & q5_c_q8 & q6_a_q5 & q6_b_q7 & q6_c_q8 & q7_a_q17 & q7_b_q7 & q7_c_q8 & q8_a_q9 & q8_b_q6 & q8_c_q8 & q9_a_q5 & q9_b_q16 & q9_c_q8 & q10_a_q11 & q10_b_q13 & q10_c_q10 & q11_a_q11 & q11_b_q13 & q11_c_q12 & q12_a_q11 & q12_b_qe & q12_c_q15 & q13_a_14 & q13_b_q13 & q13_c_q10 & q14_a_q11 & q14_b_q16 & q14_c_q12 & q15_a_q15 & q15_b_q16 & q15_c_q15 & q16_a_q17 & q16_b_q16 & q16_c_q17 & q17_a_q15 & q17_b_q16 & q17_c_q18 & q18_a_q15 & q18_b_q16 & q18_c_q15",new String[]{"a", "b", "c"})));


        lista_problemas.add(montarQuestao(lista_problemas.size()+1,
                "Dê o diagrama de estados deste AFD. (*#afd_img3#*)",
                new AFD("q1_true_false & q2_false_false & q3_false_true & q4_false_false & q5_false_false",
                        "q1_d_q1 & q1_u_q2 & q2_d_q1 & q2_u_q3 & q3_d_q2 & q3_u_q4 & q4_d_q3 & q4_u_q5 & q5_d_q4 & q5_u_q5",new String[]{"u", "d"})));

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
}
