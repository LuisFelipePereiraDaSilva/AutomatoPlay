package com.example.montanhadechomsky.fachadas;

import com.example.montanhadechomsky.MainActivity;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaAmbiente2;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaInicialAmbiente2;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaInicialAmbiente3;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.telas_aux.ItemTelaDesenhoAutomato;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.telas_aux.ItemTelaRespostaAutomato;
import com.example.montanhadechomsky.guis.tela_ambientes.telas_aux.ItensMetodosAuxiliares;

public class GUI {

    private static GUI gui;
    public static GUI getGui(){
        if(gui == null)
            gui = new GUI();
        return gui;
    }

    private MainActivity mainActivity;
    public void setMainActivity(MainActivity m){
        mainActivity = m;
    }
    public MainActivity getMainActivity(){
        return mainActivity;
    }

    private TelaInicialAmbiente2 telaInicialAmbiente2;
    public void setTelaInicialAmbiente2(TelaInicialAmbiente2 m){
        telaInicialAmbiente2 = m;
    }
    public TelaInicialAmbiente2 getTelaInicialAmbiente2(){
        return telaInicialAmbiente2;
    }

    private TelaInicialAmbiente3 telaInicialAmbiente3;
    public void setTelaInicialAmbiente3(TelaInicialAmbiente3 m){
        telaInicialAmbiente3 = m;
    }
    public TelaInicialAmbiente3 getTelaInicialAmbiente3(){
        return telaInicialAmbiente3;
    }

    private ItensMetodosAuxiliares itensMetodosAuxiliares;
    public ItensMetodosAuxiliares getItensMetodosAuxiliares(){
        if(itensMetodosAuxiliares == null)
            itensMetodosAuxiliares = new ItensMetodosAuxiliares();
        return itensMetodosAuxiliares;
    }

    private ItemTelaDesenhoAutomato itemTelaDesenhoAutomato;
    public ItemTelaDesenhoAutomato getItemTelaDesenhoAutomato(){
        if(itemTelaDesenhoAutomato == null)
            itemTelaDesenhoAutomato = new ItemTelaDesenhoAutomato();
        return itemTelaDesenhoAutomato;
    }
    public void setItemTelaDesenhoAutomato(ItemTelaDesenhoAutomato itemTelaDesenhoAutomato) {
        this.itemTelaDesenhoAutomato = itemTelaDesenhoAutomato;
    }

    private TelaAmbiente2 telaAmbiente2;
    public void setTelaAmbiente2(TelaAmbiente2 tela){
        telaAmbiente2 = tela;
    }
    public TelaAmbiente2 getTelaAmbiente2(){
        return telaAmbiente2;
    }

    private ItemTelaRespostaAutomato itemTelaRespostaAutomato;
    public void setItemTelaRespostaAutomato(ItemTelaRespostaAutomato item){
        itemTelaRespostaAutomato = item;
    }
    public ItemTelaRespostaAutomato getItemTelaRespostaAutomato(){
        return itemTelaRespostaAutomato;
    }
}
