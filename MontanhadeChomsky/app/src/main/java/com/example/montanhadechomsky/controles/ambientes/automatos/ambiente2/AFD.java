package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2;

import com.example.montanhadechomsky.controles.ambientes.automatos.Automato;
import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
