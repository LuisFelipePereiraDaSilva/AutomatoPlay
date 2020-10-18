package com.example.montanhadechomsky.guis.tela_opcoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.guis.tela_ajuda.TelaAjuda;
import com.example.montanhadechomsky.guis.tela_detalhes.TelaDetalhes;

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
        Intent in = new Intent(TelaOpcoes.this, TelaDetalhes.class);
        startActivity(in);
    }
}
