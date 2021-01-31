package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.automatoplay.R;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ItemTelaTabela extends Activity {

    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambiente2_tabela);

        grid = findViewById(R.id.id_tabela);

        String[] alfabeto;
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
            alfabeto = Controler.getControler().getQuestaoSelecionadaAmbiente2().getAutomato().getAlfabeto();
        else
            alfabeto = Controler.getControler().getQuestaoSelecionadaAmbiente3().getAutomato().getAlfabeto();

        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();

        grid.setColumnCount(alfabeto.length + 1);
        grid.setRowCount(estados.size() + 1);

        final LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = 250;
        tamanho.height = 120;

        for(int i = 0; i < (alfabeto.length + 1) * (estados.size() + 1); i++){
            ConstraintLayout c = new ConstraintLayout(getBaseContext());
            c.setLayoutParams(tamanho);
            if(i > 0)
                c.setBackgroundResource(R.drawable.borda_tabela);
            c.setPadding(5, 5, 5, 5);
            c.setId(i);
            grid.addView(c);
        }

        montarElementoTituloTabela("", 0);

        for(int i = 1, j = 0 ; i < alfabeto.length + 1; i++, j++){
            montarElementoTituloTabela(alfabeto[j], i);
        }

        int size = 0;
        for(int i = 0; i < estados.size(); i++){
            size = (i + 1) * (alfabeto.length + 1);
            montarElementoTituloTabela(estados.get(i).getNome(), size);
            for(int j = size + 1, x = 0; j < size + alfabeto.length + 1; j++, x++) {
                montarElementoCorpoTabela(automatosInterligados(estados.get(i), alfabeto[x]), j);
            }
        }
    }

    private void montarElementoTituloTabela(String texto, int i){
        final LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = 250;
        tamanho.height = 120;
        tamanho.gravity = Gravity.CENTER;

        ConstraintLayout c = grid.findViewById(i);
        c.setBackgroundColor(Color.rgb(0,0,0));
        ScrollView scroll = new ScrollView(getBaseContext());
        TextView text = new TextView(getBaseContext());
        text.setTextColor(Color.rgb(255,255,255));
        text.setTextSize(25);
        text.setText(texto);
        text.setPadding(10,10,10,10);
        text.setGravity(Gravity.CENTER);
        text.setLayoutParams(tamanho);
        scroll.addView(text);
        c.addView(scroll);
    }

    private void montarElementoCorpoTabela(String texto, int i){
        final LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = 250;
        tamanho.height = 120;
        tamanho.gravity = Gravity.CENTER;

        ConstraintLayout c = grid.findViewById(i);
        ScrollView scroll = new ScrollView(getBaseContext());
        TextView text = new TextView(getBaseContext());
        text.setTextColor(new Color().rgb(0,0,0));
        text.setTextSize(20);
        text.setText(texto);
        text.setPadding(10,10,10,10);
        text.setGravity(Gravity.CENTER);
        text.setLayoutParams(tamanho);
        scroll.addView(text);
        c.addView(scroll);
    }

    private String automatosInterligados(Estado estado, String alfabeto){
        String resultado = "";

        for(int i = 0; i < estado.getTransicoes().size(); i++){
            for(int x = 0; x < estado.getTransicoes().get(i).getSimbolo_alfabeto().length; x++) {
                if (estado.getTransicoes().get(i).getSimbolo_alfabeto()[x].equals(alfabeto))
                    resultado += estado.getTransicoes().get(i).getEstadoDestino().getNome() + ", ";
            }
        }

        if(!resultado.equals(""))
            resultado = resultado.substring(0, resultado.length() - 2);

        return resultado;
    }

    public void clickTelaMeio(View view){ }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void clickBotaoClose(View view) {
        finish();
    }

    public void clickMudo(View view) {}
}
