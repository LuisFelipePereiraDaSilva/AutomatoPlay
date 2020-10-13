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
import com.example.montanhadechomsky.guis.tela_ambientes.automatos.TelaAmbiente2;
import com.example.montanhadechomsky.guis.tela_detalhes.TelaDetalhes;

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

    private GridLayout ambiente1;
    private GridLayout ambiente2;
    private GridLayout ambiente3;

    private boolean bloquear_click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ambiente1 = findViewById(R.id.id_layout_ambiente1);
        ambiente2 = findViewById(R.id.id_layout_ambiente2);
        ambiente3 = findViewById(R.id.id_layout_ambiente3);

        if(GUI.getGui().getMainActivity() == null) {
            inicializarAmbientes();
            carregarQuestoes();
        }

        GUI.getGui().setMainActivity(this);

        questoesAmbientes();

        inicializarTurmas();
    }

    private void inicializarTurmas(){
        Spinner spinner = (Spinner) findViewById(R.id.turmas);

        ArrayList<String> valores = new ArrayList<>();
        valores.add("Questões Padrão");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.desing_spinner, valores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
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

    public void questoesAmbientes(){
        questoesAmbiente1();
        questoesAmbiente2();
        questoesAmbiente3();
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

    private boolean abrir_tela_primeira_vez = false;

    public boolean getAbrirTelaPrimeiraVez(){
        return abrir_tela_primeira_vez;
    }

    public void setarFalsoAbrirTelaPrimeiraVez(){
        abrir_tela_primeira_vez = false;
    }

    //region Ambiente 1

    public ConstraintLayout modelarQuestao(final ProblemaAmbiente1 problema){
        LinearLayout.LayoutParams tamanho_container = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho_container.width = 120;
        tamanho_container.height = 120;
        tamanho_container.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams margin_foto = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        margin_foto.width = 120;
        margin_foto.height = 120;
        margin_foto.topMargin = 10;
        margin_foto.leftMargin = 20;
        margin_foto.bottomMargin = 10;
        margin_foto.rightMargin = 20;

        CircleImageView img = new CircleImageView(this);
        img.setLayoutParams(margin_foto);

        if( problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.NaoRespondida )
            img.setBackgroundResource(R.drawable.botao_questao_normal);
        else if(problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta)
            img.setBackgroundResource(R.drawable.botao_questao_certa);
        else
            img.setBackgroundResource(R.drawable.botao_questao_errada);;

        TextView text = new TextView(this);
        text.setLayoutParams(tamanho_container);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        text.setTextColor(Color.rgb(0,0,0));
        text.setText(problema.getNumero() + "");

        ConstraintLayout c = new ConstraintLayout(this);
        tamanho_container.topMargin = 15;
        tamanho_container.leftMargin = 15;
        c.setLayoutParams(tamanho_container);
        c.setId(problema.getNumero());
        c.addView(img);
        c.addView(text);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bloquear_click) {
                    bloquear_click = true;
                    telaAmbiente1(problema);
                }
                return;
            }
        });

        return c;
    }

    public void questoesAmbiente1(){
        ArrayList<ProblemaAmbiente1> questoes = Controler.getControler().getListaProblemasAmbiente1();
        int cont = 0;
        for(int i = 0; i < questoes.size(); i++){
            if(cont == 10){
                cont = 0;
                ambiente1.setRowCount(ambiente1.getRowCount() + 1);
            }

            ambiente1.addView(modelarQuestao(questoes.get(i)));
            cont++;
        }
    }

    public void atualizarQuestaoAmbiente1(){
        ConstraintLayout novo = modelarQuestao(Controler.getControler().getQuestaoSelecionadaAmbiente1());

        ConstraintLayout c = ambiente1.findViewById(Controler.getControler().getQuestaoSelecionadaAmbiente1().getNumero());
        ambiente1.removeView(c);
        ambiente1.addView(novo, Controler.getControler().getQuestaoSelecionadaAmbiente1().getNumero() - 1);

        Controler.getControler().selecionarQuestaoAmbiente1(null);
    }

    public void telaAmbiente1(ProblemaAmbiente1 problema) {
        Controler.getControler().selecionarQuestaoAmbiente2(null);
        Controler.getControler().selecionarQuestaoAmbiente3(null);
        Controler.getControler().selecionarQuestaoAmbiente1(problema);
        Intent in = new Intent(MainActivity.this, TelaAmbiente1.class);
        startActivity(in);
        bloquear_click = false;
    }

    //endregion

    //region Ambiente 2

    public ConstraintLayout modelarQuestao(final ProblemaAmbiente2 problema){

        LinearLayout.LayoutParams tamanho_container = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho_container.width = 120;
        tamanho_container.height = 120;
        tamanho_container.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams margin_foto = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        margin_foto.width = 120;
        margin_foto.height = 120;
        margin_foto.topMargin = 10;
        margin_foto.leftMargin = 20;
        margin_foto.bottomMargin = 10;
        margin_foto.rightMargin = 20;

        CircleImageView img = new CircleImageView(this);
        img.setLayoutParams(margin_foto);

        if( problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.NaoRespondida )
            img.setBackgroundResource(R.drawable.botao_questao_normal);
        else if(problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta)
            img.setBackgroundResource(R.drawable.botao_questao_certa);
        else
            img.setBackgroundResource(R.drawable.botao_questao_errada);;


        TextView text = new TextView(this);
        text.setLayoutParams(tamanho_container);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        text.setTextColor(Color.rgb(0,0,0));
        text.setText(problema.getNumero() + "");

        ConstraintLayout c = new ConstraintLayout(this);
        tamanho_container.topMargin = 15;
        tamanho_container.leftMargin = 15;
        c.setLayoutParams(tamanho_container);
        c.setId(problema.getNumero());
        c.addView(img);
        c.addView(text);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bloquear_click) {
                    bloquear_click = true;
                    telaAmbiente2(problema);
                }
                return;
            }
        });

        return c;
    }

    public void questoesAmbiente2(){
        ArrayList<ProblemaAmbiente2> questoes = Controler.getControler().getListaProblemasAmbiente2();
        int cont = 0;
        for(int i = 0; i < questoes.size(); i++){
            if(cont == 10){
                cont = 0;
                ambiente2.setRowCount(ambiente2.getRowCount() + 1);
            }

            ambiente2.addView(modelarQuestao(questoes.get(i)));
            cont++;
        }
    }

    public void atualizarQuestaoAmbiente2(){
        ConstraintLayout novo = modelarQuestao(Controler.getControler().getQuestaoSelecionadaAmbiente2());

        ConstraintLayout c = ambiente2.findViewById(Controler.getControler().getQuestaoSelecionadaAmbiente2().getNumero());
        ambiente2.removeView(c);
        ambiente2.addView(novo, Controler.getControler().getQuestaoSelecionadaAmbiente2().getNumero() - 1);

        Controler.getControler().selecionarQuestaoAmbiente2(null);
    }

    public void telaAmbiente2(ProblemaAmbiente2 problema) {
        GUI.getGui().setItemTelaDesenhoAutomato(null);
        Controler.getControler().selecionarQuestaoAmbiente1(null);
        Controler.getControler().selecionarQuestaoAmbiente2(problema);
        Controler.getControler().selecionarQuestaoAmbiente3(null);
        GUI.getGui().getItemTelaDesenhoAutomato().setListaEstadosAutomato(Controler.getControler().
                getQuestaoSelecionadaAmbiente2().getResposta_secundaria());
        GUI.getGui().getItemTelaDesenhoAutomato().setPorcetagem(Controler.getControler().
                getQuestaoSelecionadaAmbiente2().getPorcentagem());
        abrir_tela_primeira_vez = true;
        Intent in = new Intent(MainActivity.this, TelaAmbiente2.class);
        startActivity(in);
        bloquear_click = false;
    }

    //endregion

    //region Ambiente 3

    public ConstraintLayout modelarQuestao(final ProblemaAmbiente3 problema){

        LinearLayout.LayoutParams tamanho_container = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho_container.width = 120;
        tamanho_container.height = 120;
        tamanho_container.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams margin_foto = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        margin_foto.width = 120;
        margin_foto.height = 120;
        margin_foto.topMargin = 10;
        margin_foto.leftMargin = 20;
        margin_foto.bottomMargin = 10;
        margin_foto.rightMargin = 20;

        CircleImageView img = new CircleImageView(this);
        img.setLayoutParams(margin_foto);

        if( problema == null || ( problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.NaoRespondida ) )
            img.setBackgroundResource(R.drawable.botao_questao_normal);
        else if(problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta)
            img.setBackgroundResource(R.drawable.botao_questao_certa);
        else
            img.setBackgroundResource(R.drawable.botao_questao_errada);;


        TextView text = new TextView(this);
        text.setLayoutParams(tamanho_container);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        text.setTextColor(Color.rgb(0,0,0));
        text.setText(problema.getNumero() + "");

        ConstraintLayout c = new ConstraintLayout(this);
        tamanho_container.topMargin = 15;
        tamanho_container.leftMargin = 15;
        c.setLayoutParams(tamanho_container);
        c.setId(problema.getNumero());
        c.addView(img);
        c.addView(text);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bloquear_click) {
                    bloquear_click = true;
                    telaAmbiente3(problema);
                }
                return;
            }
        });

        return c;
    }

    public void questoesAmbiente3(){
        ArrayList<ProblemaAmbiente3> questoes = Controler.getControler().getListaProblemasAmbiente3();
        int cont = 0;
        for(int i = 0; i < questoes.size(); i++){
            if(cont == 10){
                cont = 0;
                ambiente3.setRowCount(ambiente3.getRowCount() + 1);
            }

            ambiente3.addView(modelarQuestao(questoes.get(i)));
            cont++;
        }
    }

    public void atualizarQuestaoAmbiente3(){
        ConstraintLayout novo = modelarQuestao(Controler.getControler().getQuestaoSelecionadaAmbiente3());

        ConstraintLayout c = ambiente3.findViewById(Controler.getControler().getQuestaoSelecionadaAmbiente3().getNumero());
        ambiente3.removeView(c);
        ambiente3.addView(novo, Controler.getControler().getQuestaoSelecionadaAmbiente3().getNumero() - 1);

        Controler.getControler().selecionarQuestaoAmbiente3(null);
    }

    public void telaAmbiente3(ProblemaAmbiente3 problema) {
        GUI.getGui().setItemTelaDesenhoAutomato(null);
        Controler.getControler().selecionarQuestaoAmbiente1(null);
        Controler.getControler().selecionarQuestaoAmbiente2(null);
        Controler.getControler().selecionarQuestaoAmbiente3(problema);
        GUI.getGui().getItemTelaDesenhoAutomato().setListaEstadosAutomato(Controler.getControler().
                getQuestaoSelecionadaAmbiente3().getResposta_secundaria());
        GUI.getGui().getItemTelaDesenhoAutomato().setPorcetagem(Controler.getControler().
                getQuestaoSelecionadaAmbiente3().getPorcentagem());
        abrir_tela_primeira_vez = true;
        Intent in = new Intent(MainActivity.this, TelaAmbiente2.class);
        startActivity(in);
        bloquear_click = false;
    }

    //endregion
}
