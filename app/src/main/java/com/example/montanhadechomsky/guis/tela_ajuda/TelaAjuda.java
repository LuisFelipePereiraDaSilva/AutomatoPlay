package com.example.montanhadechomsky.guis.tela_ajuda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.controles.ambientes.Ambientes;
import com.example.montanhadechomsky.guis.tela_ambientes.telas_aux.PopUpCustomizado;
import com.example.montanhadechomsky.guis.tela_opcoes.TelaOpcoes;

import androidx.appcompat.app.AppCompatActivity;

public class TelaAjuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_ajuda);
    }

    public void clickBotaoAFD(View view) {
        String texto = new Ambientes().getAjudaAmbiente2E3();
        PopUpCustomizado pop = new PopUpCustomizado("Detalhes", texto);
        Intent in = new Intent(TelaAjuda.this, pop.getClass());
        startActivity(in);
    }
}
