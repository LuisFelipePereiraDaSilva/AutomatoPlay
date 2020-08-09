package com.example.montanhadechomsky.guis.tela_ambientes.ambiente1.telas_aux;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.fachadas.Controler;

public class ItemTelaRespostaCorreta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambientes_problema);

        TextView t = findViewById(R.id.id_problema);

        t.setText(Controler.getControler().getQuestaoSelecionadaAmbiente1().getResposta_submetida());

        t.setTextColor(Color.rgb(255, 255, 255));

        ScrollView c = findViewById(R.id.tela_problema);
        c.setBackgroundResource(R.color.fundo_itens_ambientes);
    }

    public void onStop(){
        super.onStop();
        finish();
    }
}
