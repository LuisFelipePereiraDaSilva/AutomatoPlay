package com.example.montanhadechomsky.guis.tela_ambientes.automatos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.montanhadechomsky.MainActivity;
import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.ProblemaAmbiente2;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import de.hdodenhof.circleimageview.CircleImageView;

public class TelaInicialAmbiente2 extends AppCompatActivity {

    private boolean bloquear_click = false;
    private LinearLayout ambiente2;
    private float largura;

    private boolean abrir_tela_primeira_vez = false;

    public boolean getAbrirTelaPrimeiraVez(){
        return abrir_tela_primeira_vez;
    }

    public void setarFalsoAbrirTelaPrimeiraVez(){
        abrir_tela_primeira_vez = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_ambiente2);

        GUI.getGui().setTelaInicialAmbiente2(this);

        ambiente2 = findViewById(R.id.layout_questoes_ambiente2);

        ambiente2.getViewTreeObserver().addOnGlobalLayoutListener(new
          ViewTreeObserver.OnGlobalLayoutListener() {
              @Override
              public void onGlobalLayout() {
                  //Remove o listenner para não ser novamente chamado.
                  ambiente2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                  //Coloca a largura igual à altura
                  largura = ambiente2.getWidth();
                  questoesAmbiente2();
              }
          });
    }

    public ConstraintLayout modelarQuestao(final ProblemaAmbiente2 problema){

        LinearLayout.LayoutParams tamanho_container = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT,(int) LinearLayout.LayoutParams.WRAP_CONTENT);
        tamanho_container.width = 150;
        tamanho_container.height = 150;
        tamanho_container.gravity = Gravity.CENTER;

        TextView text = new TextView(this);
        text.setLayoutParams(tamanho_container);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        text.setTextColor(Color.rgb(0,0,0));
        text.setText(problema.getNumero() + "");

        ConstraintLayout c = new ConstraintLayout(this);
        tamanho_container.topMargin = 20;
        tamanho_container.leftMargin = 20;
        c.setLayoutParams(tamanho_container);
        c.setId(problema.getNumero());
        c.addView(text);

        if( problema.getStatus_resposta() == ProblemaAmbientes.StatusResposta.Correta)
            c.setBackgroundResource(R.drawable.botao_questao_certa);
        else
            c.setBackgroundResource(R.drawable.botao_questao_normal);

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
        ArrayList<ConstraintLayout> layouts = new ArrayList<ConstraintLayout>();

        for(int i = 0; i < questoes.size(); i++){
            layouts.add(modelarQuestao(questoes.get(i)));
        }

        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        margin.gravity = Gravity.LEFT;

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(margin);

        LinearLayout.LayoutParams margin2 = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        margin2.gravity = Gravity.CENTER;

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setLayoutParams(margin2);

        float width = 0;
        boolean flag = false;

        for(int i = 0; i < layouts.size(); i++){
            flag = false;
            if(width + 170 < largura) {
                layout.addView(layouts.get(i));
                width+= 170;
            }else {
                l.addView(layout);
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setLayoutParams(margin);
                width = 0;
                flag = true;
                i--;
            }
        }

        if(!flag)
            l.addView(layout);

        ambiente2.addView(l);
    }

    public void atualizarQuestaoAmbiente2(){
        ambiente2.removeAllViews();
        Controler.getControler().selecionarQuestaoAmbiente2(null);
        questoesAmbiente2();
    }

    public void telaAmbiente2(ProblemaAmbiente2 problema) {
        GUI.getGui().setItemTelaDesenhoAutomato(null);
        Controler.getControler().selecionarQuestaoAmbiente2(problema);
        Controler.getControler().selecionarQuestaoAmbiente3(null);
        GUI.getGui().getItemTelaDesenhoAutomato().setListaEstadosAutomato(Controler.getControler().
                getQuestaoSelecionadaAmbiente2().getResposta_secundaria());
        GUI.getGui().getItemTelaDesenhoAutomato().setPorcetagem(Controler.getControler().
                getQuestaoSelecionadaAmbiente2().getPorcentagem());
        abrir_tela_primeira_vez = true;
        Intent in = new Intent(TelaInicialAmbiente2.this, TelaAmbiente2.class);
        startActivity(in);
        bloquear_click = false;
    }
}
