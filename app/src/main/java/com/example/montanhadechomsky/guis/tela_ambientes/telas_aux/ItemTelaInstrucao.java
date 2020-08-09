package com.example.montanhadechomsky.guis.tela_ambientes.telas_aux;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;

public class ItemTelaInstrucao extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambientes_instrucao);

        TextView t = findViewById(R.id.id_instrucao);

        if(Controler.getControler().getQuestaoSelecionadaAmbiente1() != null) {
            GUI.getGui().getItensMetodosAuxiliares().montagemTextView(Controler.getControler().getInstrucoesAmbiente1(),
                    "Instruções: ", t, this);
        }else{
            GUI.getGui().getItensMetodosAuxiliares().montagemTextView(Controler.getControler().getInstrucoesAmbiente2E3(),
                    "Instruções: ", t, this);
        }

        t.setTextColor(Color.rgb(255, 255, 255));

        ScrollView c = findViewById(R.id.tela_instrucao);
        c.setBackgroundResource(R.color.fundo_itens_ambientes);

    }

    public void onStop() {
        super.onStop();
        finish();
    }
}
