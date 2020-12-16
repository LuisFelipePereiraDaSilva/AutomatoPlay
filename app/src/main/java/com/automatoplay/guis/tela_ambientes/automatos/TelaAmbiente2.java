package com.automatoplay.guis.tela_ambientes.automatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.automatoplay.R;
import com.automatoplay.controles.ambientes.ProblemaAmbientes;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.controles.ambientes.automatos.Transicao;
import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.DesenharSeta;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaAtualizacaoAutomato;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaAtualizacaoSeta;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaCriacaoAutomato;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaCriacaoSeta;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaRespostaAutomato;
import com.automatoplay.guis.tela_ambientes.automatos.telas_aux.ItemTelaTabela;
import com.automatoplay.guis.tela_ambientes.telas_aux.PopUpCustomizado;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TelaAmbiente2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    private int numero_grande = 600;

    private Handler handler = new Handler();
    private GridLayout g;
    private ConstraintLayout tela_grade;
    private ConstraintLayout tela_grade_simbolos;
    private DesenharSeta view;

    private boolean liberar_spinner = false;
    private ConstraintLayout quadrado_selecionado_grade;

    private float[] cordenadas_seta = new float[4];

    private Estado estado_selecionado;
    public Estado getAutomatoSelecionado(){
        return estado_selecionado;
    }

    private Transicao seta_selecionada;
    public Transicao getTransicaoSelecionada(){
        return seta_selecionada;
    }

    private void salvarRespostaSecundaria(){
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null) {
            Controler.getControler().getQuestaoSelecionadaAmbiente2().setPorcentagem(GUI.
                    getGui().getItemTelaDesenhoAutomato().getPorcetagem());
            Controler.getControler().getQuestaoSelecionadaAmbiente2().setResposta_secundaria(GUI.
                    getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato());
        } else {
            Controler.getControler().getQuestaoSelecionadaAmbiente3().setPorcentagem(GUI.
                    getGui().getItemTelaDesenhoAutomato().getPorcetagem());
            Controler.getControler().getQuestaoSelecionadaAmbiente3().setResposta_secundaria(GUI.
                    getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambiente2);

        g = findViewById(R.id.Grid);
        tela_grade = findViewById(R.id.tela_grade_aux);
        tela_grade_simbolos = findViewById(R.id.tela_grade_simbolos);
        view = findViewById(R.id.view_setas);

        GUI.getGui().setTelaAmbiente2(this);

        //preencherInformacoes();
        prencherNumerosZoom();

        if( (GUI.getGui().getTelaInicialAmbiente2() != null &&
                GUI.getGui().getTelaInicialAmbiente2().getAbrirTelaPrimeiraVez())
        ) {
            criarGradeAux();
            GUI.getGui().getTelaInicialAmbiente2().setarFalsoAbrirTelaPrimeiraVez();
        } else if( (GUI.getGui().getTelaInicialAmbiente3() != null &&
                GUI.getGui().getTelaInicialAmbiente3().getAbrirTelaPrimeiraVez())
        ) {
            criarGradeAux();
            GUI.getGui().getTelaInicialAmbiente3().setarFalsoAbrirTelaPrimeiraVez();
        }
        else
            criarGrade();

        verificarExisteEstados();
        clickProblema();
    }

    private void prencherNumerosZoom(){
        Spinner numeros = findViewById(R.id.id_numeros_zoom);
        ArrayList<String> a = new ArrayList<>();
        a.add("10%"); a.add("20%");a.add("30%");a.add("40%");a.add("50%"); a.add("60%");a.add("70%"); a.add("80%");
        a.add("90%");a.add("100%");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.desing_spinner_zoom, a);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numeros.setAdapter(spinnerAdapter);
        numeros.setSelection(a.indexOf(GUI.getGui().getItemTelaDesenhoAutomato().getPorcetagem() + "%"));
        numeros.setOnItemSelectedListener(this);
    }

    private void isTextoAuxiliarAutomato(boolean b) {
        TextView t = findViewById(R.id.id_texto_auxiliar_automato);
        t.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
    }

    private void verificarExisteEstados() {
        if (GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato().size() == 0) {
            isTextoAuxiliarAutomato(true);
        } else {
            isTextoAuxiliarAutomato(false);
        }
    }

    public void mensagemExibir(String titulo, String mensagem){
        AlertDialog.Builder mens = new AlertDialog.Builder(TelaAmbiente2.this);
        mens.setTitle(titulo);
        mens.setMessage(mensagem);
        mens.setNeutralButton("Ok", null);
        mens.show();
    }

    private void criarEstado(View v) {
        int q1 = (int) v.getId() - 30; // cima
        int q2 = (int) v.getId() + 30; // baixo
        int q3 = (int) v.getId() - 1; // esquerda
        int q4 = (int) v.getId() + 1; // direita
        int q5 = q1 - 1; // diagonal esquerda cima
        int q6 = q1 + 1; // diagonal direita cima
        int q7 = q2 - 1; // diagonal esquerda baixo
        int q8 = q2 + 1; // diagonal direita baixo
        if ((q1 < 0 || (q1 >= 0 && ((ConstraintLayout) g.findViewById(q1)).getChildCount() == 0)) &&
                (q2 < 0 || (q2 >= 0 && ((ConstraintLayout) g.findViewById(q2)).getChildCount() == 0)) &&
                (q3 < 0 || (q3 >= 0 && ((ConstraintLayout) g.findViewById(q3)).getChildCount() == 0)) &&
                (q4 < 0 || (q4 >= 0 && ((ConstraintLayout) g.findViewById(q4)).getChildCount() == 0)) &&
                (q5 < 0 || (q5 >= 0 && ((ConstraintLayout) g.findViewById(q5)).getChildCount() == 0)) &&
                (q6 < 0 || (q6 >= 0 && ((ConstraintLayout) g.findViewById(q6)).getChildCount() == 0)) &&
                (q7 < 0 || (q7 >= 0 && ((ConstraintLayout) g.findViewById(q7)).getChildCount() == 0)) &&
                (q8 < 0 || (q8 >= 0 && ((ConstraintLayout) g.findViewById(q8)).getChildCount() == 0))) {
            if (quadrado_selecionado_grade != null) {
                quadrado_selecionado_grade.setBackgroundResource(R.drawable.borda_grade);
                quadrado_selecionado_grade = null;
            }
            Intent in = new Intent(TelaAmbiente2.this, ItemTelaCriacaoAutomato.class);
            startActivity(in);
            quadrado_selecionado_grade = (ConstraintLayout) v;
        } else {
            Toast.makeText(TelaAmbiente2.this, "Por favor, deixe um espaço maior entre os estados",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void moverEstado(View v){
        int q1 = (int) v.getId() - 30; // cima
        int q2 = (int) v.getId() + 30; // baixo
        int q3 = (int) v.getId() - 1; // esquerda
        int q4 = (int) v.getId() + 1; // direita
        int q5 = q1 - 1; // diagonal esquerda cima
        int q6 = q1 + 1; // diagonal direita cima
        int q7 = q2 - 1; // diagonal esquerda baixo
        int q8 = q2 + 1; // diagonal direita baixo
        if ((q1 == estado_selecionado.getId() || q1 < 0 || (q1 >= 0 && ((ConstraintLayout) g.findViewById(q1)).getChildCount() == 0)) &&
                (q2 == estado_selecionado.getId() || q2 < 0 || (q2 >= 0 && ((ConstraintLayout) g.findViewById(q2)).getChildCount() == 0)) &&
                (q3 == estado_selecionado.getId() || q3 < 0 || (q3 >= 0 && ((ConstraintLayout) g.findViewById(q3)).getChildCount() == 0)) &&
                (q4 == estado_selecionado.getId() || q4 < 0 || (q4 >= 0 && ((ConstraintLayout) g.findViewById(q4)).getChildCount() == 0)) &&
                (q5 == estado_selecionado.getId() || q5 < 0 || (q5 >= 0 && ((ConstraintLayout) g.findViewById(q5)).getChildCount() == 0)) &&
                (q6 == estado_selecionado.getId() || q6 < 0 || (q6 >= 0 && ((ConstraintLayout) g.findViewById(q6)).getChildCount() == 0)) &&
                (q7 == estado_selecionado.getId() || q7 < 0 || (q7 >= 0 && ((ConstraintLayout) g.findViewById(q7)).getChildCount() == 0)) &&
                (q8 == estado_selecionado.getId() || q8 < 0 || (q8 >= 0 && ((ConstraintLayout) g.findViewById(q8)).getChildCount() == 0))) {
            quadrado_selecionado_grade.removeAllViews();
            ConstraintLayout c = (ConstraintLayout) v;
            montarAutomato(c, estado_selecionado);
            Estado estado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(estado_selecionado.getId());
            estado.setId(v.getId());

            estado.atualizarCordenadasMinhaSeta(c.getX(), c.getY());
            estado.setCordenadas(new float[]{c.getX(), c.getY()});
            ArrayList<Estado> automatos = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
            for (int i = 0; i < automatos.size(); i++) {
                estado = automatos.get(i);
                estado.atualizarSeta(quadrado_selecionado_grade.getX(), quadrado_selecionado_grade.getY(), c.getX(), c.getY());
            }
            DesenharSeta view = findViewById(R.id.view_setas);
            view.redesenharSetas();

            estado_selecionado = null;
            quadrado_selecionado_grade = null;
            salvarRespostaSecundaria();
            Toast.makeText(TelaAmbiente2.this, "Estado movido",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(TelaAmbiente2.this, "Por favor, deixe um espaço maior entre os estados",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void moverEstadoAux(View v){
        if (estado_selecionado.getId() != v.getId())
            Toast.makeText(TelaAmbiente2.this, "Já existe um estado nessa posição!",
                    Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(TelaAmbiente2.this, "Estado movido",
                    Toast.LENGTH_LONG).show();
            quadrado_selecionado_grade = null;
            estado_selecionado = null;
            salvarRespostaSecundaria();
        }
    }

    private void atualizarEstado(View v){
        if (((ConstraintLayout) v).getChildCount() > 0) {
            if (quadrado_selecionado_grade != null) {
                quadrado_selecionado_grade.setBackgroundResource(R.drawable.borda_grade);
                quadrado_selecionado_grade = null;
            }
            Intent in = new Intent(TelaAmbiente2.this, ItemTelaAtualizacaoAutomato.class);
            startActivity(in);
            estado_selecionado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(v.getId());
            quadrado_selecionado_grade = (ConstraintLayout) v;
        }
    }

    private void criarGrade(){
        final ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
        tamanho.height = GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();


        for (int j = 0; j < numero_grande; j++) {
            final int i = j;

            ConstraintLayout c = new ConstraintLayout(getBaseContext());
            c.setLayoutParams(tamanho);
            //c.setBackgroundResource(R.drawable.borda_grade2);
            c.setPadding(5, 5, 5, 5);
            c.setId(i);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloquearClick) {
                        bloquearClick = true;
                        if (((ConstraintLayout) v).getChildCount() == 0) {
                            if (estado_selecionado != null) {
                                moverEstado(v);
                            } else {
                                criarEstado(v);
                            }
                        } else {
                            if (estado_selecionado != null)
                                moverEstadoAux(v);
                            else {
                                tracarSetaEntreAutomatos((ConstraintLayout) v);
                            }
                        }
                        bloquearClick = false;
                    }
                    return;
                }
            });
            c.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    atualizarEstado(v);
                    return true;
                }
            });
            Estado estado;
            if ((estado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(c.getId())) != null) {
                montarAutomato(c, estado);
            }

            adicionarElementoGrade(c);
        }

        adicionarElementoCanvas();
        tamanhoTelaGrade();

        handler.post(new Runnable(){
            public void run(){
                g.setBackgroundResource(R.drawable.borda_grade2);
            }
        });
    }

    private void criarGradeAux(){
        new Thread() {
            public void run() {
                criarGrade();
            }
        }.start();
    }

    public void adicionarElementoGrade(final ConstraintLayout c){
        handler.post(new Runnable(){
            public void run(){
                g.addView(c);
            }
        });
    }

    public void adicionarElementoCanvas(){
        handler.post(new Runnable(){
            public void run(){
                ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                        ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
                tamanho.width = 30 * GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
                tamanho.height = 20 * GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();
                view.setLayoutParams(tamanho);
            }
        });
    }

    public void tamanhoTelaGrade(){
        handler.post(new Runnable(){
            public void run(){
                ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                        ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
                tamanho.width = 30 * GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
                tamanho.height = 20 * GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();
                tela_grade.setLayoutParams(tamanho);
                tela_grade_simbolos.setLayoutParams(tamanho);
            }
        });
    }

    public void tracarSetaEntreAutomatos(ConstraintLayout c){
        if(quadrado_selecionado_grade == null){
            quadrado_selecionado_grade = c;
            quadrado_selecionado_grade.setBackgroundColor(Color.rgb(175, 238,238));
        }
        else{
            cordenadas_seta[0] = quadrado_selecionado_grade.getX();
            cordenadas_seta[1] = quadrado_selecionado_grade.getY();
            cordenadas_seta[2] = c.getX();
            cordenadas_seta[3] = c.getY();
            Transicao transicao;
            if((transicao = verificarExisteSeta()) != null){
                seta_selecionada = transicao;
                estado_selecionado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(quadrado_selecionado_grade.getId());
                Intent in = new Intent(TelaAmbiente2.this, ItemTelaAtualizacaoSeta.class);
                startActivity(in);
            }
            else {
                estado_selecionado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(c.getId());
                Intent in = new Intent(TelaAmbiente2.this, ItemTelaCriacaoSeta.class);
                startActivity(in);
            }
            quadrado_selecionado_grade.setBackgroundResource(R.drawable.borda_grade);
        }
    }

    private Transicao verificarExisteSeta(){
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();

        for(int i = 0; i < estados.size(); i++){
            ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
            for(int j = 0; j < transicoes.size(); j++) {
                Transicao transicao = transicoes.get(j);
                if(transicao.getCordenadas_seta()[0] == cordenadas_seta[0] && transicao.getCordenadas_seta()[1] == cordenadas_seta[1] &&
                        transicao.getCordenadas_seta()[2] == cordenadas_seta[2] && transicao.getCordenadas_seta()[3] == cordenadas_seta[3])
                    return transicao;
            }
        }
        return null;
    }

    public void auxTracarSeteEntreAutomatos(String[] simbolos){
        Estado estado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(quadrado_selecionado_grade.getId());
        estado.adicionarTransicao(new Transicao(simbolos, estado_selecionado, cordenadas_seta));
        DesenharSeta view = findViewById(R.id.view_setas);
        view.redesenharSetas();

        quadrado_selecionado_grade = null;
        estado_selecionado = null;
        cordenadas_seta = new float[4];
        salvarRespostaSecundaria();
    }

    public void limparTelaGradeSimbolos(){
        tela_grade_simbolos.removeAllViews();
    }

    public void limparTelaGradeEstados(){
        tela_grade.removeAllViews();
    }

    public void desenharSimbolosSeta(final Estado estado, final Transicao transicao, int[] cor){
        int id = Integer.parseInt(estado.getId() + "" + transicao.getEstadoDestino().getId());

        TextView t = new TextView(TelaAmbiente2.this);
        String simbolos = "";
        for(int i = 0; i < transicao.getSimbolo_alfabeto().length; i++){
            if(i == transicao.getSimbolo_alfabeto().length - 1)
                simbolos += transicao.getSimbolo_alfabeto()[i];
            else
                simbolos += transicao.getSimbolo_alfabeto()[i] + ",";
        }
        t.setText(simbolos);
        t.setId(id);
        t.setX(transicao.getCordenadas_simbolos()[0]);
        t.setY(transicao.getCordenadas_simbolos()[1]);
        t.setPadding(0,0,0,0);
        t.setTextSize(GUI.getGui().getItemTelaDesenhoAutomato().getTamanhoLetra());
        t.setTextColor(Color.rgb(cor[0],cor[1],cor[2]));
        t.setBackgroundResource(R.color.simbolos);
        t.setGravity(Gravity.CENTER);
        t.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                seta_selecionada = transicao;
                estado_selecionado = estado;
                Intent in = new Intent(TelaAmbiente2.this, ItemTelaAtualizacaoSeta.class);
                startActivity(in);
                return true;
            }
        });
        tela_grade_simbolos.addView(t);
    }

    public void limparSetaSelecionada(){
        seta_selecionada = null;
    }

    public void atualizarSeta(){
        limparSetaSelecionada();
        limparGradeAutomato();
        view.redesenharSetas();
        salvarRespostaSecundaria();
    }

    public void removerSeta(){
        estado_selecionado.removerSeta(seta_selecionada.getCordenadas_seta()[2], seta_selecionada.getCordenadas_seta()[3]);
        limparSetaSelecionada();
        limparGradeAutomato();
        view.redesenharSetas();
        salvarRespostaSecundaria();
    }

    private int vericarExisteSetaCordenadas(int id_automato, Transicao t, float c1, float c2, char x_ou_y, int tipo){
        int resultado = 0;
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
        for(int i = 0; i < estados.size(); i++){
            ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
            for(int j = 0; j < transicoes.size(); j++) {
                Transicao transicao = transicoes.get(j);
                if((estados.get(i).getId() == id_automato || transicao.getEstadoDestino().getId() == id_automato) &&
                        ((x_ou_y == 'x' && ((tipo == 1 && (transicao.getCordenadas_seta()[0] < t.getCordenadas_seta()[0] ||
                                transicao.getCordenadas_seta()[2] < t.getCordenadas_seta()[0])) || (tipo == 2 &&
                                (transicao.getCordenadas_seta()[0] > t.getCordenadas_seta()[0] ||
                                        transicao.getCordenadas_seta()[2] > t.getCordenadas_seta()[0]))))
                                || (x_ou_y == 'y' && ((tipo == 1 && (transicao.getCordenadas_seta()[1] < t.getCordenadas_seta()[1] ||
                                transicao.getCordenadas_seta()[3] < t.getCordenadas_seta()[1])) || (tipo == 2 &&
                                (transicao.getCordenadas_seta()[1] > t.getCordenadas_seta()[1] ||
                                        transicao.getCordenadas_seta()[3] > t.getCordenadas_seta()[1]))) ) ) ){

                    if(transicao != t && ((transicao.getCordenadas_seta()[0] >= c1 && transicao.getCordenadas_seta()[0] <= c2) ||
                            (transicao.getCordenadas_seta()[2] >= c1 && transicao.getCordenadas_seta()[2] <= c2) ||
                            (transicao.getCordenadas_seta()[1] >= c1 && transicao.getCordenadas_seta()[1] <= c2) ||
                            (transicao.getCordenadas_seta()[3] >= c1 && transicao.getCordenadas_seta()[3] <= c2)))
                        resultado++;
                }
            }
        }
        return resultado;
    }

    public void desenharSetaMesmoAutomato(Estado estado, Transicao transicao){
        ImageView img = new ImageView(TelaAmbiente2.this);
        img.setBackgroundResource(R.drawable.seta_mesmo_estado);
        LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
        tamanho.height = GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();
        img.setLayoutParams(tamanho);

        boolean flag = false;
        int cima = -1;
        int baixo = -1;
        int esquerda = -1;
        int direita = -1;

        float[] cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()};
        if(cordenadas[1] >= 0 && (cima = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[0] + GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), 'y', 1)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()};
        if(!flag && cordenadas[1] <= g.getHeight() && (baixo = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[0] + GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), 'y', 2)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
            img.setRotation(180);
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0] - GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), transicao.getCordenadas_seta()[1]};
        if(!flag && cordenadas[0] >= 0 && (esquerda = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[1], transicao.getCordenadas_seta()[1] + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht(), 'x', 1)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/5});
            img.setRotation(270);
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0] + GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), transicao.getCordenadas_seta()[1]};
        if(!flag && cordenadas[0] <= g.getWidth() && (direita = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[1], transicao.getCordenadas_seta()[1] + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht(), 'x', 2)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/5});
            img.setRotation(90);
            flag = true;
        }
        if(!flag){
            cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()};
            if(cordenadas[1] >= 0 && ( (baixo == -1 || (cima <= baixo)) && (esquerda == -1 || (cima <= esquerda)) && (direita == -1 || (cima <= direita)) ) ){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()};
            if(!flag && cordenadas[1] <= g.getHeight() && ( (cima == -1 || (baixo <= cima)) && (esquerda == -1 || (baixo <= esquerda)) && (direita == -1 || (baixo <= direita)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
                img.setRotation(180);
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0] - GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), transicao.getCordenadas_seta()[1]};
            if(!flag && cordenadas[0] >= 0 && ( (baixo == -1 || (esquerda <= baixo)) && (cima == -1 || (esquerda <= cima)) && (direita == -1 || (esquerda <= direita)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/5});
                img.setRotation(270);
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0] + GUI.getGui().getItemTelaDesenhoAutomato().getWidth(), transicao.getCordenadas_seta()[1]};
            if(!flag && cordenadas[0] <= g.getWidth() && ( (baixo == -1 || (direita <= baixo)) && (esquerda == -1 || (direita <= esquerda)) && (cima == -1 || (direita <= cima)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/5});
                img.setRotation(90);
            }
        }
        desenharSimbolosSeta(estado, transicao, new int[]{0,0,0});
        tela_grade.addView(img);
    }

    private void redesenharSetasMesmoEstado(){
        tela_grade.removeAllViews();
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
        for (int i = 0; i < estados.size(); i++) {
            Estado a = estados.get(i);
            for(int j = 0; j < a.getTransicoes().size(); j++){
                Transicao transicao = a.getTransicoes().get(j);
                if(transicao.getCordenadas_seta()[0] == transicao.getCordenadas_seta()[2] &&
                        transicao.getCordenadas_seta()[1] == transicao.getCordenadas_seta()[3]) {
                    desenharSetaMesmoAutomato(a, transicao);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (quadrado_selecionado_grade != null) {
            quadrado_selecionado_grade.setBackgroundResource(R.drawable.borda_grade);
            quadrado_selecionado_grade = null;
        } else {
            super.onBackPressed();
            if(!bloquearClick) {
                bloquearClick = true;

                if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
                    GUI.getGui().getTelaInicialAmbiente2().atualizarQuestaoAmbiente2();
                else
                    GUI.getGui().getTelaInicialAmbiente3().atualizarQuestaoAmbiente3();
                finish();
            }
        }
    }

    public void adicionarEstadoAutomato(Estado estado){
        estado.setId(quadrado_selecionado_grade.getId());
        estado.setCordenadas(new float[]{quadrado_selecionado_grade.getX(), quadrado_selecionado_grade.getY()});
        if(estado.getEstado_inicial())
            verificarEstadosIniciais();
        montarAutomato(quadrado_selecionado_grade, estado);
        quadrado_selecionado_grade = null;
        GUI.getGui().getItemTelaDesenhoAutomato().adicionarEstadoAutomato(estado);
        salvarRespostaSecundaria();
        verificarExisteEstados();
    }

    public void atualizarEstadoAutomato(Estado automato, boolean mover){
        if(automato != null) {
            quadrado_selecionado_grade.removeAllViews();
            if (automato.getEstado_inicial())
                verificarEstadosIniciais();
            montarAutomato(quadrado_selecionado_grade, automato);
        }
        if(!mover){
            quadrado_selecionado_grade = null;
            estado_selecionado = null;
        }
        else if(automato != null)
            estado_selecionado = automato;
        salvarRespostaSecundaria();
    }

    public void removerEstadoAutomato(){
        quadrado_selecionado_grade.removeAllViews();
        GUI.getGui().getItemTelaDesenhoAutomato().removerEstadoAutomato(estado_selecionado.getId());
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
        for(int i = 0; i < estados.size(); i++){
            estados.get(i).removerSeta(quadrado_selecionado_grade.getX(), quadrado_selecionado_grade.getY());
        }
        DesenharSeta view = findViewById(R.id.view_setas);
        view.redesenharSetas();
        quadrado_selecionado_grade = null;
        estado_selecionado = null;
        salvarRespostaSecundaria();
        verificarExisteEstados();
    }

    public void limparGradeAutomato(){
        quadrado_selecionado_grade = null;
        estado_selecionado = null;
    }

    public void verificarEstadosIniciais(){
        for(int i = 0; i < GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato().size(); i++){
            Estado estado = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato().get(i);
            if((estado_selecionado == null || estado_selecionado.getId() != estado.getId()) &&
                    estado.getEstado_inicial()) {
                GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato().get(i).setEstado_inicial(false);
                GridLayout g = findViewById(R.id.Grid);
                ConstraintLayout c = (ConstraintLayout) g.findViewById(estado.getId());
                c.removeViewAt(0);
                ImageView img = new ImageView(this);
                img.setBackgroundResource(estado.getImg());
                c.addView(img, 0);
                g.removeView(c);
                g.addView(c, c.getId());
                break;
            }
        }
    }

    public void montarAutomato(ConstraintLayout c, Estado estado){
        ImageView img = new ImageView(TelaAmbiente2.this);
        img.setBackgroundResource(estado.getImg());
        c.addView(img);

        LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.gravity = Gravity.CENTER;
        tamanho.width = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
        tamanho.height = GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();
        TextView t = new TextView(TelaAmbiente2.this);
        t.setText(estado.getNome());
        t.setId(0);
        t.setX(0);
        t.setY(0);
        t.setTextSize(GUI.getGui().getItemTelaDesenhoAutomato().getTamanhoLetra());
        t.setTextColor(Color.rgb(0,0,0));
        t.setLayoutParams(tamanho);
        t.setGravity(Gravity.CENTER);
        c.addView(t);
    }

    public void auxClickZoom(){
        tela_grade.removeAllViews();
        ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT,(int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
        tamanho.height = GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();

        LinearLayout.LayoutParams tamanho2 = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho2.width = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
        tamanho2.height = GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();
        tamanho2.gravity = Gravity.CENTER;
        int i = 0;
        for(i = 0; i < numero_grande; i++){
            ConstraintLayout c = (ConstraintLayout) g.findViewById(i);
            if(c.getChildCount() > 0) {
                TextView t = null;
                if(i > 0) {
                    t = c.findViewById(0);
                    c.removeView(t);
                    t.setTextSize(GUI.getGui().getItemTelaDesenhoAutomato().getTamanhoLetra());
                }else{
                    t = new TextView(getBaseContext());
                    t.setText(GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(0).getNome());
                    t.setId(0);
                    t.setTextSize(GUI.getGui().getItemTelaDesenhoAutomato().getTamanhoLetra());
                    t.setTextColor(Color.rgb(0,0,0));
                    t.setGravity(Gravity.CENTER);
                    c.removeViewAt(1);
                }
                c.addView(t, 1, tamanho2);
                atualizarSetasZoom(c.getId(), c.getX(), c.getY());
            }
            g.removeView(c);
            g.addView(c, i, tamanho);
        }
        view.redesenharSetas();
        adicionarElementoCanvas();
        tamanhoTelaGrade();
    }

    public void redesenharSetas(){
        handler.post(new Runnable() {
            public void run() {
                view.redesenharSetas();
            }
        });
    }

    private float tamanho_antigo = 0;
    public void atualizarSetasZoom(int id, float x, float y){
        try {
            float ax = (x / tamanho_antigo) * GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
            float ay = (y / tamanho_antigo) * GUI.getGui().getItemTelaDesenhoAutomato().getHeiht();

            Estado estado = GUI.getGui().getItemTelaDesenhoAutomato().getEstadoAutomato(id);
            ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
            for (int i = 0; i < estados.size(); i++) {
                Estado a = estados.get(i);
                a.atualizarSeta(x, y, ax, ay);
            }
            estado.setCordenadas(new float[]{ax, ay});
            estado.atualizarCordenadasMinhaSeta(ax, ay);
        }
        catch (Exception e){
            Toast.makeText(this, "Desculpe, mas algo deu errado!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean expandirMenu = true;
    public void clickBotaoExpandirOuDiminuirMenu(View view) {
        info.androidhive.fontawesome.FontTextView botaoMenu = findViewById(R.id.botaoMenu);
        final LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        HorizontalScrollView menu = findViewById(R.id.menu);

        if (expandirMenu) {
            expandirMenu = false;
            botaoMenu.setText(R.string.fa_chevron_right_solid);

            tamanho.width = 0;
            menu.setLayoutParams(tamanho);
        } else {
            expandirMenu = true;
            botaoMenu.setText(R.string.fa_chevron_left_solid);

            tamanho.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            menu.setLayoutParams(tamanho);
        }
    }

    public void clickProblema(){
        String texto = "";
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null) {
            texto = ("&Problema:& " + Controler.getControler().getQuestaoSelecionadaAmbiente2().getProblema());
        }
        else if(Controler.getControler().getQuestaoSelecionadaAmbiente3() != null){
            texto = ("&Problema:& " + Controler.getControler().getQuestaoSelecionadaAmbiente3().getProblema());
        }

        PopUpCustomizado pop = new PopUpCustomizado("Problema", texto);
        Intent in = new Intent(TelaAmbiente2.this, pop.getClass());
        startActivity(in);
    }

    public void clickInstrucoes(){
        PopUpCustomizado pop = new PopUpCustomizado("Instruções",
                "&Instruções:& " + Controler.getControler().getInstrucoesAmbiente2E3());
        Intent in = new Intent(TelaAmbiente2.this, pop.getClass());
        startActivity(in);
    }

    public void clickAjuda(){
        PopUpCustomizado pop = new PopUpCustomizado("Ajuda",
                Controler.getControler().getAjudaAmbiente2E3());
        Intent in = new Intent(TelaAmbiente2.this, pop.getClass());
        startActivity(in);
    }

    public void clickResposta(){
        if( (Controler.getControler().getQuestaoSelecionadaAmbiente2() != null &&
                Controler.getControler().getQuestaoSelecionadaAmbiente2().getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta) ||
                (Controler.getControler().getQuestaoSelecionadaAmbiente3() != null &&
                        Controler.getControler().getQuestaoSelecionadaAmbiente3().getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta) ){
            Intent in = new Intent(TelaAmbiente2.this, ItemTelaRespostaAutomato.class);
            startActivity(in);
        }
        else{
            mensagemExibir("Resposta", "Você só pode visualizar a resposta da questão depois de acertá-la");
        }
    }

    private boolean bloquearClick = false;

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    clickBotaoLimparAux();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    public void clickBotaoLimparAux() {
        GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato().clear();
        g.removeAllViews();
        tela_grade.removeAllViews();
        criarGrade();
        salvarRespostaSecundaria();
        verificarExisteEstados();
    }
    public void clickBotaoLimpar(View view){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Tem certeza que deseja realmente desfazer este autômato?").setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();
    }

    public void clickBotaoProblema(View view){
        clickProblema();
    }

    public void clickTabela(){
        Intent in = new Intent(TelaAmbiente2.this, ItemTelaTabela.class);
        startActivity(in);
    }

    public void clickBotaoEnviar(View view){
        if( (Controler.getControler().getQuestaoSelecionadaAmbiente2() != null &&
                Controler.getControler().getQuestaoSelecionadaAmbiente2().getResposta_secundaria().size() > 0) ||
                (Controler.getControler().getQuestaoSelecionadaAmbiente3() != null &&
                        Controler.getControler().getQuestaoSelecionadaAmbiente3().getResposta_secundaria().size() > 0)) {

            boolean resultado = false;
            if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
                resultado = Controler.getControler().getQuestaoSelecionadaAmbiente2().validarRespostaUsuario();
            else if (Controler.getControler().getQuestaoSelecionadaAmbiente3() != null)
                resultado = Controler.getControler().getQuestaoSelecionadaAmbiente3().validarRespostaUsuario();


            if (resultado) {

                ArrayList<Estado> novo = new ArrayList<Estado>();
                ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();

                for(int i = 0; i < estados.size(); i++){
                    novo.add(new Estado(estados.get(i)));
                }

                for(int i = 0; i < estados.size(); i++){
                    ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
                    for(int j = 0; j < transicoes.size(); j++) {
                        Transicao transicao = transicoes.get(j);
                        for(int x = 0; x < novo.size(); x++){
                            if(transicao.getEstadoDestino().getId() == novo.get(x).getId()){
                                novo.get(i).adicionarTransicao(new Transicao(transicao, novo.get(x)));
                            }
                        }
                    }
                }

                if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
                    Controler.getControler().getQuestaoSelecionadaAmbiente2().setResposta_submetida(novo);
                else
                    Controler.getControler().getQuestaoSelecionadaAmbiente3().setResposta_submetida(novo);

                mensagemExibir("Resultado", "Parabéns!! você acertou a resposta.");
            } else {
                if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null) {
                    if (Controler.getControler().getQuestaoSelecionadaAmbiente2().getStatus_resposta() != ProblemaAmbientes.StatusResposta.Correta)
                        Controler.getControler().getQuestaoSelecionadaAmbiente2().setStatus_resposta(ProblemaAmbientes.StatusResposta.Errada);
                }else {
                    if (Controler.getControler().getQuestaoSelecionadaAmbiente3().getStatus_resposta() != ProblemaAmbientes.StatusResposta.Correta)
                        Controler.getControler().getQuestaoSelecionadaAmbiente3().setStatus_resposta(ProblemaAmbientes.StatusResposta.Errada);
                }
                mensagemExibir("Resultado", "Está resposta está incorreta.");
            }
        }else
            mensagemExibir("Resultado", "Resposta não pode ser vazia!");
    }

    public void clickBotaoVoltar(View view) {
        onBackPressed();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_problema) {
            clickProblema();
        } else if (id == R.id.item_menu_instrucoes) {
            clickInstrucoes();
        } else if (id == R.id.item_menu_ajuda) {
            clickAjuda();
        } else if (id == R.id.item_menu_resposta) {
            clickResposta();
        } else if (id == R.id.item_menu_tabela) {
            clickTabela();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }
    public void clickBotaoMaisOpcoes(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate((R.menu.menu_ambiente2));
        popup.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            if(liberar_spinner) {
                Spinner numeros = findViewById(R.id.id_numeros_zoom);
                numeros.setActivated(false);
                numeros.setSelection(position);
                tamanho_antigo = GUI.getGui().getItemTelaDesenhoAutomato().getWidth();
                GUI.getGui().getItemTelaDesenhoAutomato().setPorcetagem((position + 1) * 10);
                GUI.getGui().getItemTelaDesenhoAutomato().setPorcetagem((position + 1) * 10);
                Toast.makeText(this, "Zoom em " + GUI.getGui().getItemTelaDesenhoAutomato().getPorcetagem(), Toast.LENGTH_SHORT).show();
                auxClickZoom();
                numeros.setActivated(true);
                salvarRespostaSecundaria();
            }
            liberar_spinner = true;
        }catch (Exception e){}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean verificarTelaPaisagem(){
        Configuration configuration = getResources().getConfiguration();

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            return true;
        }else{
            return false;
        }
    }

    private boolean tela_aberta = false;
    private float[] cordenadas_grade_automato = new float[2];
    private float[] cordenadas_grade_automato2 = null;
    public void clickAbrirTela(View view){
        ConstraintLayout c = findViewById(R.id.id_grade_automoto);
        float x = c.getWidth();
        float y = c.getHeight();
        ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT,(int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        if(tela_aberta){
            tela_aberta = false;
            cordenadas_grade_automato2 = new float[]{c.getX(), c.getY()};
            tamanho.width = (int)x;
            if(verificarTelaPaisagem())
                tamanho.height = (int) ((y * 73) / 100);
            else
                tamanho.height = (int) ((y * 38) / 100);
            if(cordenadas_grade_automato != null) {
                c.setX(cordenadas_grade_automato[0]);
                c.setY(cordenadas_grade_automato[1]);
            }
            c.setBackgroundResource(0);
        }
        else {
            tela_aberta = true;
            cordenadas_grade_automato = new float[]{c.getX(), c.getY()};
            c.setBackgroundColor(Color.rgb(255,255,255));
            tamanho.width = (int)x;
            if(verificarTelaPaisagem())
                tamanho.height = (int)((y * 100) / 73);
            else
                tamanho.height = (int)((y * 100) / 38);
            if(cordenadas_grade_automato2 != null) {
                c.setX(cordenadas_grade_automato2[0]);
                c.setY(cordenadas_grade_automato2[1]);
            }
        }
        c.setLayoutParams(tamanho);
    }

}
