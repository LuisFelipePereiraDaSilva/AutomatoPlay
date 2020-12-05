package com.automatoplay.guis.tela_opcoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.automatoplay.R;
import com.automatoplay.guis.tela_ajuda.TelaAjuda;
import com.automatoplay.guis.tela_ambientes.telas_aux.PopUpCustomizado;

import androidx.appcompat.app.AppCompatActivity;

public class TelaOpcoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_opcoes);
    }

    public void clickBotaoAjuda(View view) {
        Intent in = new Intent(TelaOpcoes.this, TelaAjuda.class);
        startActivity(in);
    }

    public void clickBotaoDetalhes(View view) {
        String texto = "Esse aplicativo trás questões sobre &Autômatos Finitos Determinístico& e &Autômatos Finitos" +
                " Não Determinístico& com a finalidade de reforçar e colocar em prática o assunto sobre ambos.\n\n" +

                "O aplicativo é dividido em dois ambientes, o primeiro é o ambiente com questões sobre" +
                " autômatos finitos determinísticos e o segundo é o ambiente com questões sobre autômatos finitos não" +
                " determinísticos.\n\n" +

                "Cada ambiente tem seu bloco de questões, as questões de cor branca, são as questões que ainda não foram submetidas nenhuma" +
                " resposta ou submeteram respostas erradas, e as questões com cor verde, são" +
                " as questões que foram submetidas respostas certas.\n\n";

        PopUpCustomizado pop = new PopUpCustomizado("Detalhes", texto);
        Intent in = new Intent(TelaOpcoes.this, pop.getClass());
        startActivity(in);
    }
}
