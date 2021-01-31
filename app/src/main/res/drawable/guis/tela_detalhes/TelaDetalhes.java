package com.automatoplay.guis.tela_detalhes;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.automatoplay.R;
import com.automatoplay.fachadas.GUI;

public class TelaDetalhes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_detalhes);

        TextView t = findViewById(R.id.id_detalhes);

        String texto = "    Esse aplicativo trás questões sobre &Expressões Regulares&, &Autômatos Finitos Determinístico& e &Autômatos Finitos" +
                " Não Determinístico& com a finalidade de reforçar e colocar em prática o assunto sobre ambos.\n\n" +

                "   O aplicativo é dividido em três ambientes, o primeiro é o ambiente com questões sobre expressões regulares, o segundo é o ambiente" +
                " com questões sobre autômatos finitos determinísticos e o terceiro é o ambiente com questões sobre autômatos finitos não" +
                " determinísticos.\n\n" +

                "   Cada ambiente tem seu bloco de questões, as questões com cor cinza, são as questões que ainda não foram submetidas nenhuma" +
                " resposta, as questões com cor vermelha, são as questões que foram submetidas respostas erradas e as questões com cor verde, são" +
                " as questões que foram submetidas respostas certas.\n\n";

        GUI.getGui().getItensMetodosAuxiliares().montagemTextView(texto, t, this);

        t.setTextColor(Color.rgb(255, 255, 255));
    }

}
