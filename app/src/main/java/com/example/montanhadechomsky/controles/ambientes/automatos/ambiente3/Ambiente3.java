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
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva autômatos que reconheçam a seguinte linguagem: {w ∈ {a, b}* | aaa é subpalavra de w}.",
                new AFND("s0_true_false & s1_false_false & s2_false_false & s3_false_true",
                        "s0_a,b_s0 & s0_a_s1 & s1_a_s2 & s2_a_s3 & s3_a_s3 & s3_b_s3", new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva autômatos que reconheçam a seguinte linguagem: {w ∈ {a, b}* | o sufixo de w é aa}.",
                new AFND("q0_true_false & q1_false_false & q2_false_true",
                        "q0_a,b_q0 & q0_a_q1 & q1_a_q2",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva autômatos que reconheçam a seguinte linguagem: {w ∈{a, b}* | w possui uma quantidade ímpar de a e de b}.",
                new AFND("s0_true_false & s1_false_false & s2_false_false & sf_false_true,",
                        "s0_a_s1 & s0_b_s2 & s1_a_s0 & s1_b_sf & s2_a_sf & s2_b_s0 & sf_a_s2 & sf_b_s1",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva autômatos que reconheçam a seguinte linguagem: {w ∈{a, b}* | w possui uma quantidade par de a e ímpar de b ou uma quantidade ímpar de a e par de b}.",
                new AFND("s0_true_false & s1_false_true & s2_false_true & s3_false_false",
                        "s0_a_s1 & s0_b_s2 & s1_a_s0 & s1_b_s3 & s2_a_s3 & s2_b_s0 & s3_a_s2 & s3_b_s1",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva autômatos que reconheçam a seguinte linguagem: {w ∈{a, b}* | o quinto símbolo da direita para a esquerda de w é a}.",
                new AFND("s0_true_false & s1_false_false & s2_false_false & s3_false_false & s4_false_false & s5_false_true",
                        "s0_q_s0 & s0_b_s0 & s0_a_s1 & s1_a_s2 & s1_b_s2 & s2_a_s3 & s2_b_s3 & s3_a_s4 & s3_b_s4 & s4_a_s5 & s4_b_s5",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Transforme o seguinte AFN em AFD equivalente. Considere o alfabeto Σ = {a,b}. (*#questao6_afnd#*)",
                new AFND("q0_true_true & q1_false_true & q2_false_true",
                        "q0_a_q1  & q0_b_q2  & q1_a_q1 & q1_b_q2 & q2_a_q1 & q2_b_q2",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Transforme o seguinte AFN em AFD equivalente. Considere o alfabeto Σ = {a,b}. (*#questao7_afnd#*)",
                new AFND("q0_true_false & q1_false_true & q2_false_true",
                        "q0_a_q1 & q0_b_q1 & q1_a_q2 & q1_b_q1 & q2_a_q2 & q2_b_q1",new String[]{"a","b", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {a, b, c}, que reconheça a linguagem L = {w | w possui acb ou cac como prefixo, baa ou cba como subpalavra e acb ou bac como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_true",
                        "q0_a_q1 & q0_c_q3 & q1_c_q2 & q2_b_q5 & q2_b_q6 & q2_b_q9 & q3_a_q4 & q4_c_q5 & q4_c_q8 & q5_a_q5 & q5_b_q5 & q5_b_q6 & q5_c_q5 & q5_c_q8 & q6_a_q7 & q7_a_q10 & q7_a_q11 & q8_b_q9 & q9_a_q10 & q9_a_q11 & q9_a_q14 & q10_a_q10 & q10_a_q11 & q10_b_q10 & q10_b_q13 & q10_c_q10 & q11_c_q12 & q12_b_q15 & q13_a_q14 & q14_c_q15",new String[]{"a","b","c","e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {a, b, c}, que reconheça a linguagem L = {w | w possui bbc ou acc como prefixo, cab ou bcb como subpalavra e bba ou abc como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_true",
                        "q0_b_q1 & q0_a_q3 & q1_b_q2 & q2_c_q5 & q2_c_q6 & q2_c_q9 & q3_c_q4 & q4_c_q5 & q4_c_q6 & q5_a_q5 & q5_b_q5 & q5_b_q8 & q5_c_q5 & q5_c_q6 & q6_a_q7 & q7_b_q10 & q7_b_q11 & q7_b_q14 & q8_c_q9 & q9_b_q10 & q9_b_q11 & q10_a_q10 & q10_a_q13 & q10_b_q10 & q10_b_q11 & q10_c_q10 & q11_b_q12 & q12_a_q15 & q13_b_q14 & q14_c_q15",new String[]{"a","b","c","e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {x, y, z, w}, que reconheça a linguagem L = {w | w possui xyw ou wzz como prefixo, wzx ou wyx como subpalavra e xyy ou xwz como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_true",
                        "q0_x_q1 & q0_w_q3 & q1_y_q2 & q2_w_q5 & q2_w_q6 & q2_w_q8 & q3_z_q4 & q4_z_q5 & q5_x_q5 & q5_y_q5 & q5_z_q5 & q5_w_q5 & q5_w_q6 & q5_w_q8 & q6_z_q7 & q7_x_q10 & q7_x_q11 & q7_x_q14 & q8_y_q9 & q9_x_q10 & q9_x_q11 & q9_x_q13 & q10_x_q10 & q10_x_q11 & q10_x_q13 & q10_y_q10 & q10_z_q10 & q10_w_q10 & q11_y_q12 & q12_y_q15 & q13_w_q14 & q14_z_q15",new String[]{"x","y","z","w", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {x, y, z, w}, que reconheça a linguagem L = {w | w possui xyw ou wzw como prefixo, wzx ou ywx como subpalavra e xyy ou xwz como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_true",
                        "q0_x_q1 & q0_w_q3 & q1_y_q2 & q2_w_q5 & q2_w_q6 & q2_w_q9 & q3_z_q4 & q4_w_q5 & q4_w_q6 & q5_x_q5 & q5_y_q5 & q5_y_q8 & q5_z_q5 & q5_w_q5 & q5_w_q6 & q6_z_q7 & q7_x_q10 & q7_x_q11 & q7_x_q13 & q8_w_q9 & q9_x_q10 & q9_x_q11 & q9_x_q13 & q10_x_q10 & q10_x_q11 & q10_x_q13 & q10_y_q10 & q10_z_q10 & q10_w_q10 & q11_y_q12 & q12_y_q15 & q13_w_q14 & q14_z_q15",new String[]{"x","y","z","w", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {x, y, z}, que reconheça a linguagem L = {w | w possui xyz ou zyx ou yzy como prefixo, xyy ou xzx ou xxz como subpalavra e yxy ou yzy ou yxx como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_false & q16_false_false & q17_false_false & q18_false_false & q19_false_false & q20_false_false & q21_false_true",
                        "q0_z_q3 & q0_x_q1 & q0_y_q5 & q1_y_q2 & q2_z_q7 & q3_y_q4 & q4_x_q7 & q4_x_q8 & q4_x_q10 & q4_x_q12 & q5_z_q6 & q6_y_q7 & q7_x_q7 & q7_x_q8 & q7_x_q10 & q7_x_q12q7_y_q7 & q7_z_q7 & q8_y_q9 & q9_y_q14 & q9_y_q15 & q9_y_q17 & q10_z_q11 & q11_x_q14 & q12_x_q13 & q13_z_q14 & q14_x_q14 & q14_y_q14 & q14_y_q15 & q14_y_q17 & q14_y_q19 & q14_z_q14 & q15_x_q16 & q16_y_q21 & q17_z_q18 & q18_y_q21 & q19_x_q20 & q20_x_q21",new String[]{"x","y","z","e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {a, b, c, d}, que reconheça a linguagem L = {w | w possui abc ou bcd ou cdd como prefixo, dba ou cda ou cab como subpalavra e adb ou dad ou bca como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_false & q16_false_false & q17_false_false & q18_false_false & q19_false_false & q20_false_false & q21_false_true",
                        "q0_b_q3 & q0_a_q1 & q0_c_q5 & q1_b_q2 & q2_c_q7 & q2_c_q10 & q2_c_q12 & q3_c_q4 & q4_d_q7 & q4_d_q8 & q4_d_q11 & q5_d_q6 & q6_d_q7 & q6_d_q8 & q7_a_q7 & q7_b_q7 & q7_c_q7 & q7_c_q10 & q7_c_q12 & q7_d_q7 & q7_d_q8 & q8_b_q9 & q9_a_q14 & q9_a_q15 & q10_d_q11 & q11_a_q14 & q11_a_q15 & q11_a_q18 & q12_a_q13 & q13_b_q14 & q13_b_q19 & q14_a_q14 & q14_a_q15 & q14_b_q14 & q14_b_q19 & q14_c_q14 & q14_d_q14 & q14_d_q17 & q15_d_q16 & q16_b_q21 & q17_a_q18 & q18_d_q21 & q19_c_q20 & q20_a_q21",new String[]{"a","b","c","d", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {a, b, c, d}, que reconheça a linguagem L = {w | w possui bab ou dac ou ba como prefixo, abb ou cac ou acbc como subpalavra e bca ou acd ou cda como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_false & q16_false_false & q17_false_false & q18_false_false & q19_false_false & q20_false_false & q21_false_true",
                        "q0_d_q3 & q0_b_q1 & q0_b_q5 & q1_a_q2 & q2_b_q8 & q2_b_q6 & q3_a_q4 & q4_c_q9 & q4_c_q12 & q4_c_q6 & q5_a_q6 & q5_a_q7 & q5_a_q11 & q6_a_q6 & q6_a_q7 & q6_a_q11 & q6_b_q6 & q6_c_q6 & q6_c_q9 & q6_d_q6 & q7_b_q8 & q8_b_q14 & q8_b_q15 & q9_a_q10 & q10_c_q14 & q10_c_q18 & q10_c_q19 & q11_c_q12 & q12_b_q13 & q13_c_q14 & q13_c_q16 & q13_c_q19 & q14_a_q14 & q14_a_q17 & q14_b_q14 & q14_b_q15 & q14_c_q14 & q14_c_q19 & q14_d_q14 & q15_c_q16 & q16_a_q21 & q17_c_q18 & q18_d_q21 & q19_d_q20 & q20_a_q21",new String[]{"a","b","c","d", "e"})));
        lista_problemas.add(montarQuestao(lista_problemas.size()+1,"Desenvolva um Autômato Finito Não-Determinístico (AFN) sobre o alfabeto Σ = {i, j, k}, que reconheça a linguagem L = {w | w possui iik ou jkj ou kji como prefixo, ikki ou jiik ou kjji como subpalavra e ikk ou jii ou kkj como sufixo}.",
                new AFND("q0_true_false & q1_false_false & q2_false_false & q3_false_false & q4_false_false & q5_false_false & q6_false_false & q7_false_false & q8_false_false & q9_false_false & q10_false_false & q11_false_false & q12_false_false & q13_false_false & q14_false_false & q15_false_false & q16_false_false & q17_false_false & q18_false_false & q19_false_false & q20_false_false & q21_false_false & q22_false_false & q23_false_false &  q24_false_true",
                        "q0_j_q3 & q0_i_q1 & q0_k_q5 & q1_i_q2 & q2_k_q7 & q2_k_q9 & q2_k_q14 & q3_k_q4 & q4_j_q7 & q4_j_q11 & q4_j_q15 & q5_j_q6 & q6_i_q7 & q6_i_q8 & q6_i_q12 & q7_i_q7 & q7_i_q8 & q7_j_q7 & q7_j_q11 & q7_k_q7 & q7_k_q14 & q8_k_q9 & q9_k_q10 & q10_i_q17 & q10_i_q18q11_j_q12 & q12_i_q13 & q13_k_q17 & q13_k_q19 & q13_k_q22 & q14_j_q15 & q15_j_q16 & q16_i_q17 & q16_i_q18 & q16_i_q21 & q17_i_q17 & q17_i_q18 & q17_j_q17 & q17_j_q20 & q17_k_q17 & q17_k_q22q18_k_q19 & q19_k_q24 & q20_i_q21 & q21_i_q24 & q22_k_q23 & q23_j_q24",new String[]{"i","j","k", "e"})));

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
