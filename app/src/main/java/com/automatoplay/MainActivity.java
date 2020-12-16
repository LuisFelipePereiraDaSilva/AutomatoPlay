package com.automatoplay;

import android.content.Intent;
import android.os.Bundle;

import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;
import com.automatoplay.guis.tela_ambientes.automatos.TelaInicialAmbiente2;
import com.automatoplay.guis.tela_ambientes.automatos.TelaInicialAmbiente3;
import com.automatoplay.guis.tela_opcoes.TelaOpcoes;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GUI.getGui().getMainActivity() == null) {
            inicializarAmbientes();
            carregarQuestoes();
        }

        GUI.getGui().setMainActivity(this);
    }

    public void clickBotaoAutomatosDeterministicos(View view) {
        Intent in = new Intent(MainActivity.this, TelaInicialAmbiente2.class);
        startActivity(in);
    }

    public void clickBotaoAutomatosNaoDeterministicos(View view) {
        Intent in = new Intent(MainActivity.this, TelaInicialAmbiente3.class);
        startActivity(in);
    }

    public void clickBotaoOpcoes(View view){
        Intent in = new Intent(MainActivity.this, TelaOpcoes.class);
        startActivity(in);
    }

    private void inicializarAmbientes(){
        Controler.getControler().inicializarAmbiente2(this.getBaseContext());
        Controler.getControler().inicializarAmbiente3(this.getBaseContext());
    }

    private void carregarQuestoes(){
        Controler.getControler().carregarQuestoesAmbiente2("Ambiente2");
        Controler.getControler().carregarQuestoesAmbiente3("Ambiente3");
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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}