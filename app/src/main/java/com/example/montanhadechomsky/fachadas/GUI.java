package com.example.montanhadechomsky.fachadas;

import com.example.montanhadechomsky.MainActivity;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaAmbiente2;
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
