package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.automatoplay.R;
import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;

public class ItemTelaCriacaoSeta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambiente2_tela_criacao_seta);
        degitacaoEditText();
    }

    public void degitacaoEditText() {

        final EditText e = findViewById(R.id.id_criacao_seta_nome);
        final TextView t = findViewById(R.id.id_aux_criacao_seta_nome);

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!e.getText().toString().replace(" ", "").equals("")) {
                    t.setText("");
                } else {
                    t.setText("Símbolo do alfabeto...");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public boolean verificarSimbolosExiste(String[] simbolos){
        String[] alfabeto;
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
            alfabeto = Controler.getControler().getQuestaoSelecionadaAmbiente2().getAutomato().getAlfabeto();
        else
            alfabeto = Controler.getControler().getQuestaoSelecionadaAmbiente3().getAutomato().getAlfabeto();
        boolean flag = false;
        for(int i = 0; i < simbolos.length; i++){
            flag = false;
            for(int j = 0; j < alfabeto.length; j++){
                if(simbolos[i].equals(alfabeto[j])) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                break;
        }
        return flag;
    }

    public void clickConfirmacao(View view){
        EditText e = findViewById(R.id.id_criacao_seta_nome);
        if(!e.getText().toString().replace(" ", "").equals("")){
            String[] simbolos = e.getText().toString().replace(" ", "").trim().split(",");
            if(verificarSimbolosExiste(simbolos)) {
                String s = "";
                for(int i = 0; i < simbolos.length; i++) {
                    if(!s.contains(simbolos[i])) {
                        if (i == simbolos.length - 1)
                            s += simbolos[i];
                        else
                            s += simbolos[i] + ",";
                    }
                }
                GUI.getGui().getTelaAmbiente2().auxTracarSeteEntreAutomatos(s.split(","));
                onStop();
            }
            else
                Toast.makeText(this, "Digite apenas símbolos pertencentes ao alfabeto da questão", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Digite pelo menos um símbolo do alfabeto", Toast.LENGTH_LONG).show();
    }

    public void clickTelaMeio(View view){ }

    public void clickTela(View view){
        onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    public void onStop(){
        super.onStop();
        GUI.getGui().getTelaAmbiente2().limparGradeAutomato();
        finish();
    }
}
