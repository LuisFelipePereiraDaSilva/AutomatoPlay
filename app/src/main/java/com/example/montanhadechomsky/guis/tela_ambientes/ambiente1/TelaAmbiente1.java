package com.example.montanhadechomsky.guis.tela_ambientes.ambiente1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;
import com.example.montanhadechomsky.guis.tela_detalhes.TelaDetalhes;
import com.example.montanhadechomsky.guis.tela_ambientes.telas_aux.PopUpCustomizado;
import com.example.montanhadechomsky.guis.tela_ambientes.ambiente1.telas_aux.ItemTelaRespostaCorreta;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TelaAmbiente1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Problema " + Controler.getControler().getQuestaoSelecionadaAmbiente1().getNumero() + " - ER");
        setSupportActionBar(toolbar);

        degitacaoEditText();

        preencherInformacoes();
    }

    private void preencherInformacoes(){
        textViewProblema(Controler.getControler().getQuestaoSelecionadaAmbiente1().getProblema());

        textViewInstrucao(Controler.getControler().getInstrucoesAmbiente1());

        EditText e = findViewById(R.id.id_resposta_ambiente1);
        e.setText(Controler.getControler().getQuestaoSelecionadaAmbiente1().getResposta_secundaria());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_detalhes) {
            Intent in = new Intent(TelaAmbiente1.this, TelaDetalhes.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void textViewProblema(final String problema){
        TextView t = findViewById(R.id.id_problema);

        GUI.getGui().getItensMetodosAuxiliares().montagemTextView(problema, "Problema: ", t, this);

        t.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopUpCustomizado pop = new PopUpCustomizado("Problema",
                        "&Problema:& " + problema);
                Intent in = new Intent(TelaAmbiente1.this, pop.getClass());
                startActivity(in);
                return true;
            }
        });
    }

    public void textViewInstrucao(final String instrucao){
        TextView t = findViewById(R.id.id_instrucao);

        GUI.getGui().getItensMetodosAuxiliares().montagemTextView(instrucao, "Instruções: ", t, this);

        t.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopUpCustomizado pop = new PopUpCustomizado("Instruções",
                        "&Instruções:& " + instrucao);
                Intent in = new Intent(TelaAmbiente1.this, pop.getClass());
                startActivity(in);
                return true;
            }
        });
    }

    public void degitacaoEditText() {

        final EditText e = findViewById(R.id.id_resposta_ambiente1);
        final TextView t = findViewById(R.id.id_resposta_ambiente1_aux);

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!e.getText().toString().equals("")) {
                    t.setText("");
                    Controler.getControler().getQuestaoSelecionadaAmbiente1().setResposta_secundaria(e.getText().toString());
                } else {
                    t.setText("Digite sua resposta...");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void minimizarTeclado(){
        EditText e = findViewById(R.id.id_resposta_ambiente1);
        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                e.getWindowToken(), 0);
        System.out.println("");
    }

    @Override
    public void onBackPressed() {
        GUI.getGui().getMainActivity().atualizarQuestaoAmbiente1();
        finish();
    }

    public void clickAjuda(View view){
        PopUpCustomizado pop = new PopUpCustomizado("Ajuda",
                Controler.getControler().getAjudaAmbiente1());
        Intent in = new Intent(TelaAmbiente1.this, pop.getClass());
        startActivity(in);
    }

    public void clickResposta(View view){
        if(Controler.getControler().getQuestaoSelecionadaAmbiente1().getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta){
            Intent in = new Intent(TelaAmbiente1.this, ItemTelaRespostaCorreta.class);
            startActivity(in);
        }
        else{
            mensagemExibir("Resposta", "Você só pode visualizar a resposta da questão depois de acertá-la");
        }
    }

    public void clickAbadonar(View view){
        GUI.getGui().getMainActivity().atualizarQuestaoAmbiente1();
        finish();
    }

    public void clickReiniciar(View view){
        EditText e = findViewById(R.id.id_resposta_ambiente1);
        e.setText("");
    }

    public boolean verificarSimbolosAlfabeto(String string){
        String[] alfabeto1 = Controler.getControler().getQuestaoSelecionadaAmbiente1().getExpressao_relular().getAlfabeto();
        String[] alfabeto2 = {"(", ")", "+", "*"};
        ArrayList<String> alfabeto = new ArrayList();
        for(int i = 0; i < alfabeto1.length; i++){
            alfabeto.add(alfabeto1[i]);
        }
        for(int i = 0; i < alfabeto2.length; i++){
            alfabeto.add(alfabeto2[i]);
        }
        string = string.replace(" ", "");
        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            if(!alfabeto.contains("" + c))
                return false;
        }

        return true;
    }

    public String conversaoConcatenacao(String string){
        string = string.replace(" ", "");
        String[] alfabeto2 = {"(", ")", "+"};
        ArrayList<String> alfabeto = new ArrayList();
        for(int i = 0; i < alfabeto2.length; i++){
            alfabeto.add(alfabeto2[i]);
        }

        String resultado = "";
        String aux = "";

        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            if(alfabeto.contains(""+c)) {
                if (aux.length() > 1) {
                    if(resultado.length() > 0 && resultado.charAt(resultado.length() - 1) == '(' && c == ')')
                        aux += c;
                    else
                        aux = "(" + aux + ")" + c;
                    resultado += aux;
                    aux = "";
                }
                else {
                    if(aux.length() == 1 && c == ')' && resultado.length() > 0 && resultado.charAt(resultado.length() - 1) == '(')
                        resultado = resultado.substring(0, resultado.length() - 1) + aux;
                    else if(aux.length() == 0 && c == ')' && resultado.length() > 0 && resultado.charAt(resultado.length() - 1) == '(')
                        resultado = resultado.substring(0, resultado.length() - 1);
                    else {
                        aux += c;
                        resultado += aux;
                    }
                    aux = "";
                }
            }
            else {
                if (c == '*') {
                    if (aux.length() == 0)
                        resultado += c;
                    else
                        aux += c;
                }
                else
                    aux += c;
            }
        }

        if(aux.length() > 0) {
            if (aux.length() > 1) {
                aux = "(" + aux + ")";
                resultado += aux;
            }
            else {
                resultado += aux;
            }
        }

        return resultado;
    }

    public void clickSubmeter(View view){
        final EditText e = findViewById(R.id.id_resposta_ambiente1);
        if(!e.getText().toString().replace(" ", "").equals("")) {
            if(verificarSimbolosAlfabeto(e.getText().toString())) {

                boolean resultado = false;



                if (resultado) {
                    Controler.getControler().getQuestaoSelecionadaAmbiente1().setResposta_submetida(e.getText().toString());
                    mensagemExibir("Resultado", "Parabéns!! você acertou a resposta.");
                } else {
                    if (Controler.getControler().getQuestaoSelecionadaAmbiente1().getStatus_resposta() != ProblemaAmbientes.StatusResposta.Correta)
                        Controler.getControler().getQuestaoSelecionadaAmbiente1().setStatus_resposta(ProblemaAmbientes.StatusResposta.Errada);
                    mensagemExibir("Resultado", "Está resposta está incorreta.");
                }
            }
            else
                mensagemExibir("Resultado", "Símbolos inválidos.");
        }
        else
            mensagemExibir("Resultado", "Resposta não pode ser vazia!");
    }

    public void mensagemExibir(String titulo, String mensagem){
        AlertDialog.Builder mens = new AlertDialog.Builder(TelaAmbiente1.this);
        mens.setTitle(titulo);
        mens.setMessage(mensagem);
        mens.setNeutralButton("Ok", null);
        mens.show();
    }
}
