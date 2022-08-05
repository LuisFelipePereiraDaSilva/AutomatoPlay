package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.automatoplay.R;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;

import java.util.ArrayList;

public class ItemTelaCriacaoAutomato extends Activity {

    private Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambiente2_tela_criacao_automato);
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
            estado = new Estado(Controler.getControler().getQuestaoSelecionadaAmbiente2().getResposta_secundaria().size(), false, false);
        else
            estado = new Estado(Controler.getControler().getQuestaoSelecionadaAmbiente3().getResposta_secundaria().size(), false, false);
        degitacaoEditText();
    }

    public void degitacaoEditText() {

        final EditText e = findViewById(R.id.id_criacao_automato_nome);
        final TextView t = findViewById(R.id.id_aux_criacao_automato_nome);

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!e.getText().toString().replace(" ", "").equals("")) {
                    t.setText("");
                } else {
                    t.setText(estado.getNome());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        e.setText(estado.getNome());
    }

    public void clickConfirmacao(View view){
        CheckBox b = findViewById(R.id.id_criacao_automato_estado_aceitacao);
        EditText e = findViewById(R.id.id_criacao_automato_nome);

        if (e.getText().toString().equals("")) {
            Toast.makeText(this, "Adicione uma descrição para o estado", Toast.LENGTH_LONG).show();
            return;
        } else {
            ArrayList<Estado> estados = null;
            if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
                estados = Controler.getControler().getQuestaoSelecionadaAmbiente2().getResposta_secundaria();
            else
                estados = Controler.getControler().getQuestaoSelecionadaAmbiente3().getResposta_secundaria();

            for (int i = 0; i < estados.size(); i++) {
                if (estados.get(i).getNome().equals(e.getText().toString())) {
                    Toast.makeText(this, "Já existe um estado com essa descrição", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        estado.setNome(e.getText().toString());
        CheckBox b3 = findViewById(R.id.id_criacao_automato_estado_inicial);
        estado.setEstado_inicial(b3.isChecked());
        estado.setEstado_aceitacao(b.isChecked());
        GUI.getGui().getTelaAmbiente2().adicionarEstadoAutomato(estado);
        onStop();
    }

    public void clickTelaMeio(View view){ }

    public void clickTela(View view){
        onStop();
    }

    public void onStop(){
        super.onStop();
        GUI.getGui().getTelaAmbiente2().limparGradeAutomato();
        finish();
    }
}
