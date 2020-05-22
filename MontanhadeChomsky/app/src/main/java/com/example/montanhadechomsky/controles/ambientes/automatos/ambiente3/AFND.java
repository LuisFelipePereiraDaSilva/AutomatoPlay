package com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3;

import com.example.montanhadechomsky.controles.ambientes.automatos.Automato;
import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;

import java.io.Serializable;
import java.util.ArrayList;

public class AFND extends Automato implements Serializable {

    public AFND(ArrayList<Estado> estados, String[] alfabeto) {
        super(estados, alfabeto);
    }

    public AFND(String estados, String trasicoes, String[] alfabeto) {
        super(estados, trasicoes, alfabeto);
    }
}
