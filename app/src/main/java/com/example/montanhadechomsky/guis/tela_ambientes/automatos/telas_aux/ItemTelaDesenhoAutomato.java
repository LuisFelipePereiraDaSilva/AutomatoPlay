package com.example.montanhadechomsky.guis.tela_ambientes.automatos.telas_aux;

import android.view.View;

import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ItemTelaDesenhoAutomato extends AppCompatActivity {
    private int porcetagem;
    private int width;
    private int heiht;
    private float tamanho_letra;
    private ArrayList<Estado> automato = new ArrayList<Estado>();

    public void adicionarEstadoAutomato(int id, String nome, boolean estado_incial, boolean estado_aceitacao, float[] cordenadas){
        automato.add(new Estado(id, nome, estado_incial, estado_aceitacao, cordenadas));
    }

    public void adicionarEstadoAutomato(Estado estado){
        automato.add(estado);
    }

    public void removerEstadoAutomato(int id){
        for(int i = 0; i < automato.size(); i++){
            if(automato.get(i).getId() == id) {
                automato.remove(i);
                break;
            }
        }
    }

    public Estado getEstadoAutomato(int id){
        for(int i = 0; i < automato.size(); i++){
            if(automato.get(i).getId() == id)
                return automato.get(i);
        }
        return null;
    }

    public ArrayList<Estado> getListaEstadosAutomato(){
        return automato;
    }

    public void setListaEstadosAutomato(ArrayList<Estado> lista) {
        this.automato = lista;
    }

    public ItemTelaDesenhoAutomato(){ }

    public ItemTelaDesenhoAutomato(int porcetagem){
        setPorcetagem(porcetagem);
    }

    public int getPorcetagem() {
        return porcetagem;
    }

    public void setPorcetagem(int porcetagem) {
        this.porcetagem = porcetagem;
        width = heiht = (porcetagem * 200) / 100;
        if(porcetagem > 30)
            tamanho_letra = (porcetagem * 30) / 100;
        else
            tamanho_letra = (porcetagem * 20) / 100;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeiht() {
        return heiht;
    }

    public void setHeiht(int heiht) {
        this.heiht = heiht;
    }

    public float getTamanhoLetra(){
        return tamanho_letra;
    }
}
