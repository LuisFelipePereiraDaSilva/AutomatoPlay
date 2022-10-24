package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.automatoplay.R;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.controles.ambientes.automatos.Transicao;
import com.automatoplay.fachadas.Controler;
import com.automatoplay.fachadas.GUI;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ItemTelaRespostaAutomato extends Activity {

    private GridLayout g;
    private ConstraintLayout tela_grade;
    private ConstraintLayout tela_grade_simbolos;
    private DesenharSetaRespostaAutomato view;
    private boolean liberar_spinner = false;
    private float tamanho_antigo = 0;
    private ItemTelaDesenhoAutomato item;
    private int numero_grande = 600;

    public ItemTelaDesenhoAutomato getItemTelaDesenhoAutomato(){
        return item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambiente2_resposta_correta);

        GUI.getGui().setItemTelaRespostaAutomato(this);

        g = findViewById(R.id.Grid);
        tela_grade = findViewById(R.id.tela_grade_aux);
        tela_grade_simbolos = findViewById(R.id.tela_grade_simbolos);
        view = findViewById(R.id.view_setas);

        item = new ItemTelaDesenhoAutomato(60);
        if(Controler.getControler().getQuestaoSelecionadaAmbiente2() != null)
            item.setListaEstadosAutomato(Controler.getControler().getQuestaoSelecionadaAmbiente2().getResposta_submetida());
        else
            item.setListaEstadosAutomato(Controler.getControler().getQuestaoSelecionadaAmbiente3().getResposta_submetida());

        criarGrade();
    }

    private void criarGrade(){
        final ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = item.getWidth();
        tamanho.height = item.getHeiht();

        for (int i = 0, j = 0; j < item.getListaEstadosAutomato().size() && j < 600; i++) {

            ConstraintLayout c = new ConstraintLayout(getBaseContext());
            c.setLayoutParams(tamanho);
            c.setPadding(5, 5, 5, 5);
            c.setId(i);

            Estado estado;
            if ((estado = item.getEstadoAutomato(c.getId())) != null) {
                montarAutomato(c, estado);
                j++;
            }
            g.addView(c);
        }

        adicionarElementoCanvas();
        tamanhoTelaGrade();
    }

    public void adicionarElementoCanvas(){
        ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = g.getColumnCount() * item.getWidth();
        tamanho.height = g.getRowCount() * item.getHeiht();
        view.setLayoutParams(tamanho);
    }

    public void tamanhoTelaGrade(){
        ConstraintLayout.LayoutParams tamanho = new ConstraintLayout.LayoutParams
                ((int) ConstraintLayout.LayoutParams.WRAP_CONTENT, (int) ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = g.getColumnCount() * item.getWidth();
        tamanho.height = g.getRowCount() * item.getHeiht();
        tela_grade.setLayoutParams(tamanho);
        tela_grade_simbolos.setLayoutParams(tamanho);

    }

    public void montarAutomato(ConstraintLayout c, Estado estado){
        ImageView img = new ImageView(getBaseContext());
        img.setBackgroundResource(estado.getImg());
        c.addView(img);

        LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.gravity = Gravity.CENTER;
        tamanho.width = item.getWidth();
        tamanho.height = item.getHeiht();
        TextView t = new TextView(getBaseContext());
        t.setText(estado.getNome());
        t.setId(0);
        t.setX(0);
        t.setY(0);
        t.setTextSize(item.getTamanhoLetra());
        t.setTextColor(Color.rgb(0,0,0));
        t.setLayoutParams(tamanho);
        t.setGravity(Gravity.CENTER);
        c.addView(t);
    }

    public void atualizarSetasZoom(int id, float x, float y){
        try {
            float ax = (x / tamanho_antigo) * item.getWidth();
            float ay = (y / tamanho_antigo) * item.getHeiht();

            Estado estado = item.getEstadoAutomato(id);
            ArrayList<Estado> estados = item.getListaEstadosAutomato();
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

    public void limparTelaGradeSimbolos(){
        tela_grade_simbolos.removeAllViews();
    }

    public void limparTelaGradeEstados(){
        tela_grade.removeAllViews();
    }

    private int vericarExisteSetaCordenadas(int id_automato, Transicao t, float c1, float c2, char x_ou_y, int tipo){
        int resultado = 0;
        ArrayList<Estado> estados = item.getListaEstadosAutomato();
        for(int i = 0; i < estados.size(); i++){
            ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
            for(int j = 0; j < transicoes.size(); j++) {
                Transicao transicao = transicoes.get(j);
                if((estados.get(i).getId() == id_automato || transicao.getEstadoDestino().getId() == id_automato) && ((x_ou_y == 'x' && ((tipo == 1 &&
                        (transicao.getCordenadas_seta()[0] < t.getCordenadas_seta()[0] || transicao.getCordenadas_seta()[2] < t.getCordenadas_seta()[0]))
                        || (tipo == 2 && (transicao.getCordenadas_seta()[0] > t.getCordenadas_seta()[0] || transicao.getCordenadas_seta()[2] > t.getCordenadas_seta()[0]))))
                        || (x_ou_y == 'y' && ((tipo == 1 && (transicao.getCordenadas_seta()[1] < t.getCordenadas_seta()[1] ||
                        transicao.getCordenadas_seta()[3] < t.getCordenadas_seta()[1])) || (tipo == 2 && (transicao.getCordenadas_seta()[1] > t.getCordenadas_seta()[1] ||
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
        ImageView img = new ImageView(getBaseContext());
        img.setBackgroundResource(R.drawable.seta_mesmo_estado);
        LinearLayout.LayoutParams tamanho = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho.width = item.getWidth();
        tamanho.height = item.getHeiht();
        img.setLayoutParams(tamanho);

        boolean flag = false;
        int cima = -1;
        int baixo = -1;
        int esquerda = -1;
        int direita = -1;

        float[] cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] - item.getHeiht()};
        if(cordenadas[1] >= 0 && (cima = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[0] + item.getWidth(), 'y', 1)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] + item.getHeiht()};
        if(!flag && cordenadas[1] <= g.getHeight() && (baixo = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[0] + item.getWidth(), 'y', 2)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
            img.setRotation(180);
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0] - item.getWidth(), transicao.getCordenadas_seta()[1]};
        if(!flag && cordenadas[0] >= 0 && (esquerda = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[1], transicao.getCordenadas_seta()[1] + item.getHeiht(), 'x', 1)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - item.getHeiht()/5});
            img.setRotation(270);
            flag = true;
        }
        cordenadas = new float[]{transicao.getCordenadas_seta()[0] + item.getWidth(), transicao.getCordenadas_seta()[1]};
        if(!flag && cordenadas[0] <= g.getWidth() && (direita = vericarExisteSetaCordenadas(estado.getId(), transicao, transicao.getCordenadas_seta()[1], transicao.getCordenadas_seta()[1] + item.getHeiht(), 'x', 2)) == 0){
            img.setX(cordenadas[0]); img.setY(cordenadas[1]);
            transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - item.getHeiht()/5});
            img.setRotation(90);
            flag = true;
        }
        if(!flag){
            cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] - item.getHeiht()};
            if(cordenadas[1] >= 0 && ( (baixo == -1 || (cima <= baixo)) && (esquerda == -1 || (cima <= esquerda)) && (direita == -1 || (cima <= direita)) ) ){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1] + item.getHeiht()};
            if(!flag && cordenadas[1] <= g.getHeight() && ( (cima == -1 || (baixo <= cima)) && (esquerda == -1 || (baixo <= esquerda)) && (direita == -1 || (baixo <= direita)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1]});
                img.setRotation(180);
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0] - item.getWidth(), transicao.getCordenadas_seta()[1]};
            if(!flag && cordenadas[0] >= 0 && ( (baixo == -1 || (esquerda <= baixo)) && (cima == -1 || (esquerda <= cima)) && (direita == -1 || (esquerda <= direita)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - item.getHeiht()/5});
                img.setRotation(270);
                flag = true;
            }
            cordenadas = new float[]{transicao.getCordenadas_seta()[0] + item.getWidth(), transicao.getCordenadas_seta()[1]};
            if(!flag && cordenadas[0] <= g.getWidth() && ( (baixo == -1 || (direita <= baixo)) && (esquerda == -1 || (direita <= esquerda)) && (cima == -1 || (direita <= cima)) )){
                img.setX(cordenadas[0]); img.setY(cordenadas[1]);
                transicao.setCordenadas_simbolos(new float[]{cordenadas[0], cordenadas[1] - item.getHeiht()/5});
                img.setRotation(90);
            }
        }
        desenharSimbolosSeta(estado, transicao, new int[]{0,0,255});
        tela_grade.addView(img);
    }

    public void desenharSimbolosSeta(final Estado estado, final Transicao transicao, int[] cor){
        int id = Integer.parseInt(estado.getId() + "" + transicao.getEstadoDestino().getId());

        TextView t = new TextView(getBaseContext());
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
        t.setTextSize(item.getTamanhoLetra());
        t.setTextColor(Color.rgb(cor[0],cor[1],cor[2]));
        t.setBackgroundResource(R.color.simbolos);
        t.setGravity(Gravity.CENTER);
        tela_grade_simbolos.addView(t);
    }

    private boolean bloquearClick = false;
    @Override
    public void onBackPressed() {
        if(!bloquearClick) {
            bloquearClick = true;
            finish();
        }
    }

    public void clickTelaMeio(View view){ }

    public void clickBotaoClose(View view) {
        finish();
    }

    public void clickMudo(View view) {}
}
