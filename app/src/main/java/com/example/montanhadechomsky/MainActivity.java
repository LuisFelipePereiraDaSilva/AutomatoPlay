package com.example.montanhadechomsky;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.controles.ambientes.ambiente1.ProblemaAmbiente1;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.ProblemaAmbiente2;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3.ProblemaAmbiente3;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;
import com.example.montanhadechomsky.guis.tela_ambientes.ambiente1.TelaAmbiente1;
import com.example.montanhadechomsky.guis.tela_ambientes.ambiente1.TelaInicialAmbiente1;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaAmbiente2;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaInicialAmbiente2;
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaInicialAmbiente3;
import com.example.montanhadechomsky.guis.tela_detalhes.TelaDetalhes;
import com.example.montanhadechomsky.guis.tela_opcoes.TelaOpcoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

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

    public void clickBotaoExpressoesRegulares(View view) {
        Intent in = new Intent(MainActivity.this, TelaInicialAmbiente1.class);
        startActivity(in);
    }

    public void clickBotaoOpcoes(View view) {
        Intent in = new Intent(MainActivity.this, TelaOpcoes.class);
        startActivity(in);
    }

    private void inicializarAmbientes(){
        Controler.getControler().inicializarAmbiente1(this.getBaseContext());
        Controler.getControler().inicializarAmbiente2(this.getBaseContext());
        Controler.getControler().inicializarAmbiente3(this.getBaseContext());
    }

    private void carregarQuestoes(){
        Controler.getControler().carregarQuestoesAmbiente1("Ambiente1");
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
            Intent in = new Intent(MainActivity.this, TelaDetalhes.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
