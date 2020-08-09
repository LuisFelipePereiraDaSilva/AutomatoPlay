package com.example.montanhadechomsky.fachadas;

import android.content.Context;

import com.example.montanhadechomsky.controles.ambientes.Ambientes;
import com.example.montanhadechomsky.controles.ambientes.ambiente1.Ambiente1;
import com.example.montanhadechomsky.controles.ambientes.ambiente1.ProblemaAmbiente1;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.Ambiente2;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.ProblemaAmbiente2;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3.Ambiente3;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3.ProblemaAmbiente3;

import java.util.ArrayList;

public class Controler {

    private static Controler controler;
    public static Controler getControler(){
        if(controler == null)
            controler = new Controler();
        return controler;
    }

    //region Ambientes

    private Ambientes ambientes = new Ambientes();

    public String getInstrucoesAmbiente2E3(){
        return ambientes.getInstrucoesAmbiente2E3();
    }

    public String getAjudaAmbiente2E3(){
        return ambientes.getAjudaAmbiente2E3();
    }

    public String getInstrucoesAmbiente1(){
        return ambientes.getInstrucoesAmbiente1();
    }

    public String getAjudaAmbiente1(){
        return ambientes.getAjudaAmbiente1();
    }

    //endregion

    //region Ambiente1

    private Ambiente1 ambiente1;
    public void inicializarAmbiente1(Context context){
        ambiente1 = new Ambiente1(context);
    }

    public ArrayList<ProblemaAmbiente1> getListaProblemasAmbiente1(){
        return ambiente1.getListaProblemas();
    }

    public ProblemaAmbiente1 getProblemaAmbiente1(int numero){
        return ambiente1.getProblema(numero);
    }

    public void selecionarQuestaoAmbiente1(ProblemaAmbiente1 problema){
        ambiente1.selecionarQuestao(problema);
    }

    public ProblemaAmbiente1 getQuestaoSelecionadaAmbiente1(){
        return ambiente1.getQuestao_selecionada();
    }

    public void carregarQuestoesAmbiente1(String arquivo){
        ambiente1.carregarQuestoes(arquivo);
    }

    public String getArquivoAmbiente1(){
        return ambiente1.getArquivo();
    }

    public void salvarProblemaAmbiente1(ProblemaAmbiente1 problema){
        ambiente1.salvarProblema(problema);
    }

    public void removerProblemaAmbiente1(ProblemaAmbiente1 problema){
        ambiente1.removerProblema(problema);
    }

    //endregion

    //region Ambiente2

    private Ambiente2 ambiente2;
    public void inicializarAmbiente2(Context context){
        ambiente2 = new Ambiente2(context);
    }

    public ArrayList<ProblemaAmbiente2> getListaProblemasAmbiente2(){
        return ambiente2.getListaProblemas();
    }

    public ProblemaAmbiente2 getProblemaAmbiente2(int numero){
        return ambiente2.getProblema(numero);
    }

    public void selecionarQuestaoAmbiente2(ProblemaAmbiente2 problema){
        ambiente2.selecionarQuestao(problema);
    }

    public ProblemaAmbiente2 getQuestaoSelecionadaAmbiente2(){
        return ambiente2.getQuestao_selecionada();
    }

    public void carregarQuestoesAmbiente2(String arquivo){
        ambiente2.carregarQuestoes(arquivo);
    }

    public String getArquivoAmbiente2(){
        return ambiente2.getArquivo();
    }

    public void salvarProblemaAmbiente2(ProblemaAmbiente2 problema){
        ambiente2.salvarProblema(problema);
    }

    public void removerProblemaAmbiente2(ProblemaAmbiente2 problema){
        ambiente2.removerProblema(problema);
    }

    //endregion

    //region Ambiente3

    private Ambiente3 ambiente3;
    public void inicializarAmbiente3(Context context){
        ambiente3 = new Ambiente3(context);
    }

    public ArrayList<ProblemaAmbiente3> getListaProblemasAmbiente3(){
        return ambiente3.getListaProblemas();
    }

    public ProblemaAmbiente3 getProblemaAmbiente3(int numero){
        return ambiente3.getProblema(numero);
    }

    public void selecionarQuestaoAmbiente3(ProblemaAmbiente3 problema){
        ambiente3.selecionarQuestao(problema);
    }

    public ProblemaAmbiente3 getQuestaoSelecionadaAmbiente3(){
        return ambiente3.getQuestao_selecionada();
    }

    public void carregarQuestoesAmbiente3(String arquivo){
        ambiente3.carregarQuestoes(arquivo);
    }

    public String getArquivoAmbiente3(){
        return ambiente3.getArquivo();
    }

    public void salvarProblemaAmbiente3(ProblemaAmbiente3 problema){
        ambiente3.salvarProblema(problema);
    }

    public void removerProblemaAmbiente3(ProblemaAmbiente3 problema){
        ambiente3.removerProblema(problema);
    }

    //endregion
}
