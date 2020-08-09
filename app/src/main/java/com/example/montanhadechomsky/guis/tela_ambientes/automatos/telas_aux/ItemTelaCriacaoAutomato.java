package com.example.montanhadechomsky.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;

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
