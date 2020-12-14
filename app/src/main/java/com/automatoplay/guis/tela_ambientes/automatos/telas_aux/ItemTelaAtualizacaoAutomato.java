package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.automatoplay.R;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.fachadas.GUI;

public class ItemTelaAtualizacaoAutomato extends Activity {

    private Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.itens_ambiente2_tela_atualizacao_automato);
        estado = GUI.getGui().getTelaAmbiente2().getAutomatoSelecionado();
        degitacaoEditText();
        preencherInformacoes();
    }

    public void preencherInformacoes(){
        if(estado.getEstado_inicial()){
            CheckBox b = findViewById(R.id.id_atualizacao_automato_estado_inicial);
            b.setChecked(true);
        }

        if(estado.getEstado_aceitacao()){
            CheckBox b = findViewById(R.id.id_atualizacao_automato_estado_aceitacao);
            b.setChecked(true);
        }
    }

    public void degitacaoEditText() {

        final EditText e = findViewById(R.id.id_atualizacao_automato_nome);
        final TextView t = findViewById(R.id.id_aux_atualizacao_automato_nome);

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
        CheckBox b = findViewById(R.id.id_atualizacao_automato_estado_aceitacao);
        EditText e = findViewById(R.id.id_atualizacao_automato_nome);
        CheckBox b3 = findViewById(R.id.id_atualizacao_automato_estado_inicial);

        if( !e.getText().toString().equals(estado.getNome()) || (b.isChecked() && !estado.getEstado_aceitacao())
        || (!b.isChecked() && estado.getEstado_aceitacao()) || (b3.isChecked() != estado.getEstado_inicial())){
            estado.setNome(e.getText().toString());
            estado.setEstado_inicial(b3.isChecked());
            if(b.isChecked())
                estado.setEstado_aceitacao(true);
            else
                estado.setEstado_aceitacao(false);

            GUI.getGui().getTelaAmbiente2().atualizarEstadoAutomato(estado, false);
        }

        onStop();
    }

    public void clickMover(View view){
        GUI.getGui().getTelaAmbiente2().atualizarEstadoAutomato(null, true);
        onStop();
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    GUI.getGui().getTelaAmbiente2().removerEstadoAutomato();
                    onStop();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    public void clickRemover(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza que deseja realmente excluir este estado?").setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();
    }

    public void clickTelaMeio(View view){ }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GUI.getGui().getTelaAmbiente2().limparGradeAutomato();
        onStop();
    }

    public void clickTela(View view){
        GUI.getGui().getTelaAmbiente2().limparGradeAutomato();
        onStop();
    }

    public void onStop(){
        super.onStop();
        finish();
    }
}
