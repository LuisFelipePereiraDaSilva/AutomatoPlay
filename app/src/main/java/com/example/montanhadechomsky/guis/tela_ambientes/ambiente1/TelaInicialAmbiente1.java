package com.example.montanhadechomsky.guis.tela_ambientes.ambiente1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.controles.ambientes.ambiente1.ProblemaAmbiente1;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TelaInicialAmbiente1 extends AppCompatActivity {

    private boolean bloquear_click = false;
    private LinearLayout ambiente1;
    private float largura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_ambiente1);

        GUI.getGui().setTelaInicialAmbiente1(this);

        ambiente1 = findViewById(R.id.layout_questoes_ambiente1);

        ambiente1.getViewTreeObserver().addOnGlobalLayoutListener(new
            ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //Remove o listenner para não ser novamente chamado.
                    ambiente1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    //Coloca a largura igual à altura
                    largura = ambiente1.getWidth();
                    questoesAmbiente1();
                }
            });
    }

    public ConstraintLayout modelarQuestao(final ProblemaAmbiente1 problema){
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
                    telaAmbiente1(problema);
                }
                return;
            }
        });

        return c;
    }

    public void questoesAmbiente1(){
        ArrayList<ProblemaAmbiente1> questoes = Controler.getControler().getListaProblemasAmbiente1();
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

        ambiente1.addView(l);
    }

    public void atualizarQuestaoAmbiente1(){
        ambiente1.removeAllViews();
        Controler.getControler().selecionarQuestaoAmbiente1(null);
        questoesAmbiente1();
    }

    public void telaAmbiente1(ProblemaAmbiente1 problema) {
        Controler.getControler().selecionarQuestaoAmbiente2(null);
        Controler.getControler().selecionarQuestaoAmbiente3(null);
        Controler.getControler().selecionarQuestaoAmbiente1(problema);
        Intent in = new Intent(TelaInicialAmbiente1.this, TelaAmbiente1.class);
        startActivity(in);
        bloquear_click = false;
    }
}
