package com.automatoplay.controles.ambientes.automatos.ambiente2;

import com.automatoplay.controles.ambientes.automatos.Automato;
import com.automatoplay.controles.ambientes.automatos.Estado;

import java.io.Serializable;
import java.util.ArrayList;

public class AFD extends Automato implements Serializable {

    public AFD(ArrayList<Estado> estados, String[] alfabeto) {
        super(estados, alfabeto);
    }

    public AFD(String estados, String transicoes, String[] alfabeto) {
        super(estados, transicoes, alfabeto);
    }

    public void setThis(AFD problema){
        super.setThis(problema.getEstados(), problema.getAlfabeto());
    }
}
